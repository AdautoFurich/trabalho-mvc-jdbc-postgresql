package br.edu.umfg.service;

import br.edu.umfg.model.Aluno;
import br.edu.umfg.repository.AlunoRepository;

import java.util.List;

public class AlunoService {

    private final AlunoRepository alunoRepository = new AlunoRepository();

    public Aluno salvar(Aluno aluno) {
        validarAluno(aluno);
        return alunoRepository.salvar(aluno);
    }

    public List<Aluno> listar() {
        return alunoRepository.listar();
    }

    public Aluno buscarPorId(int id) {
        return alunoRepository.buscarPorId(id);
    }

    public void atualizar(Aluno aluno) {
        if (aluno.getId() <= 0) {
            throw new IllegalArgumentException("O ID do aluno é obrigatorio para atualizacao.");
        }

        if (alunoRepository.buscarPorId(aluno.getId()) == null) {
            throw new IllegalArgumentException("Aluno nao encontrado para atualizacao.");
        }

        validarAluno(aluno);
        alunoRepository.atualizar(aluno);
    }

    public void excluir(int id) {
        alunoRepository.excluir(id);
    }

    private void validarAluno(Aluno aluno) {
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do aluno é obrigatorio.");
        }

        if (aluno.getEmail() == null || aluno.getEmail().isBlank()) {
            throw new IllegalArgumentException("O email do aluno é obrigatorio.");
        }

        if (aluno.getTelefone() == null || aluno.getTelefone().isBlank()) {
            throw new IllegalArgumentException("O telefone do aluno é obrigatorio.");
        }
    }
}
