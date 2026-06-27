package br.edu.umfg.service;

import br.edu.umfg.model.Curso;
import br.edu.umfg.model.Matricula;
import br.edu.umfg.repository.AlunoRepository;
import br.edu.umfg.repository.CursoRepository;
import br.edu.umfg.repository.MatriculaRepository;

import java.math.BigDecimal;
import java.util.List;

public class MatriculaService {

    private final MatriculaRepository matriculaRepository = new MatriculaRepository();
    private final AlunoRepository alunoRepository = new AlunoRepository();
    private final CursoRepository cursoRepository = new CursoRepository();

    public Matricula salvar(Matricula matricula) {
        validarMatriculaNova(matricula);

        Matricula matriculaSalva = matriculaRepository.salvar(matricula);
        Curso curso = cursoRepository.buscarPorId(matricula.getIdCurso());
        cursoRepository.atualizarVagasDisponiveis(curso.getId(), curso.getVagasDisponiveis() - 1);

        return matriculaSalva;
    }

    public List<Matricula> listar() {
        return matriculaRepository.listar();
    }

    public Matricula buscarPorId(int id) {
        return matriculaRepository.buscarPorId(id);
    }

    public List<Matricula> listarPorAluno(int idAluno) {
        return matriculaRepository.listarPorAluno(idAluno);
    }

    public List<Matricula> listarPorCurso(int idCurso) {
        return matriculaRepository.listarPorCurso(idCurso);
    }

    public void atualizar(Matricula matricula) {
        if (matricula.getId() <= 0) {
            throw new IllegalArgumentException("O ID da matricula é obrigatorio para atualizacao.");
        }

        Matricula matriculaAtual = matriculaRepository.buscarPorId(matricula.getId());
        if (matriculaAtual == null) {
            throw new IllegalArgumentException("Matricula nao encontrada para atualizacao.");
        }

        validarCamposComuns(matricula);

        if (matriculaRepository.existeMatriculaOutroRegistro(matricula.getIdAluno(), matricula.getIdCurso(), matricula.getId())) {
            throw new IllegalArgumentException("O aluno nao pode ser matriculado duas vezes no mesmo curso.");
        }

        if (matriculaAtual.getIdCurso() != matricula.getIdCurso()) {
            Curso cursoDestino = cursoRepository.buscarPorId(matricula.getIdCurso());
            if (cursoDestino.getVagasDisponiveis() <= 0) {
                throw new IllegalArgumentException("Nao é possivel matricular aluno em curso sem vagas disponiveis.");
            }
        }

        matriculaRepository.atualizar(matricula);

        if (matriculaAtual.getIdCurso() != matricula.getIdCurso()) {
            Curso cursoOrigem = cursoRepository.buscarPorId(matriculaAtual.getIdCurso());
            Curso cursoDestino = cursoRepository.buscarPorId(matricula.getIdCurso());
            cursoRepository.atualizarVagasDisponiveis(cursoOrigem.getId(), cursoOrigem.getVagasDisponiveis() + 1);
            cursoRepository.atualizarVagasDisponiveis(cursoDestino.getId(), cursoDestino.getVagasDisponiveis() - 1);
        }
    }

    public void excluir(int id) {
        Matricula matricula = matriculaRepository.buscarPorId(id);
        if (matricula == null) {
            throw new IllegalArgumentException("Matricula nao encontrada para exclusao.");
        }

        matriculaRepository.excluir(id);
        Curso curso = cursoRepository.buscarPorId(matricula.getIdCurso());
        cursoRepository.atualizarVagasDisponiveis(curso.getId(), curso.getVagasDisponiveis() + 1);
    }

    private void validarMatriculaNova(Matricula matricula) {
        validarCamposComuns(matricula);

        if (matriculaRepository.existeMatricula(matricula.getIdAluno(), matricula.getIdCurso())) {
            throw new IllegalArgumentException("O aluno nao pode ser matriculado duas vezes no mesmo curso.");
        }

        Curso curso = cursoRepository.buscarPorId(matricula.getIdCurso());
        if (curso.getVagasDisponiveis() <= 0) {
            throw new IllegalArgumentException("Nao é possivel matricular aluno em curso sem vagas disponiveis.");
        }
    }

    private void validarCamposComuns(Matricula matricula) {
        if (alunoRepository.buscarPorId(matricula.getIdAluno()) == null) {
            throw new IllegalArgumentException("Nao é possivel matricular aluno inexistente.");
        }

        if (cursoRepository.buscarPorId(matricula.getIdCurso()) == null) {
            throw new IllegalArgumentException("Nao é possivel matricular aluno em curso inexistente.");
        }

        if (matricula.getDataMatricula() == null) {
            throw new IllegalArgumentException("A data da matricula é obrigatoria.");
        }

        if (matricula.getValor() == null || matricula.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor pago na matricula nao pode ser negativo.");
        }
    }
}
