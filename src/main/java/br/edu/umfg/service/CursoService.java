package br.edu.umfg.service;

import br.edu.umfg.model.Curso;
import br.edu.umfg.repository.CursoRepository;

import java.util.List;

public class CursoService {

    private final CursoRepository cursoRepository = new CursoRepository();

    public Curso salvar(Curso curso) {
        validarCurso(curso);
        return cursoRepository.salvar(curso);
    }

    public List<Curso> listar() {
        return cursoRepository.listar();
    }

    public Curso buscarPorId(int id) {
        return cursoRepository.buscarPorId(id);
    }

    public void atualizar(Curso curso) {
        if (curso.getId() <= 0) {
            throw new IllegalArgumentException("O ID do curso é obrigatorio para atualizacao.");
        }

        if (cursoRepository.buscarPorId(curso.getId()) == null) {
            throw new IllegalArgumentException("Curso nao encontrado para atualizacao.");
        }

        validarCurso(curso);
        cursoRepository.atualizar(curso);
    }

    public void excluir(int id) {
        cursoRepository.excluir(id);
    }

    private void validarCurso(Curso curso) {
        if (curso.getNome() == null || curso.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do curso é obrigatorio.");
        }

        if (curso.getDescricao() == null || curso.getDescricao().isBlank()) {
            throw new IllegalArgumentException("A descricao do curso é obrigatoria.");
        }

        if (curso.getCargaHoraria() <= 0) {
            throw new IllegalArgumentException("A carga horaria do curso deve ser maior que zero.");
        }

        if (curso.getVagasTotais() <= 0) {
            throw new IllegalArgumentException("O numero maximo de vagas deve ser maior que zero.");
        }

        if (curso.getVagasDisponiveis() < 0) {
            throw new IllegalArgumentException("O numero de vagas disponiveis nao pode ser negativo.");
        }

        if (curso.getVagasDisponiveis() > curso.getVagasTotais()) {
            throw new IllegalArgumentException("As vagas disponiveis nao podem ser maiores que as vagas totais.");
        }
    }
}
