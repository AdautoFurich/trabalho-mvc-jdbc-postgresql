package br.edu.umfg.service;

import br.edu.umfg.model.Tutor;
import br.edu.umfg.repository.TutorRepository;

import java.util.List;

public class TutorService {

    private final TutorRepository tutorRepository = new TutorRepository();

    public Tutor salvar(Tutor tutor) {
        if (tutor.getNome() == null || tutor.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do tutor é obrigatório.");
        }

        if (tutor.getEndereco() == null || tutor.getEndereco().isBlank()) {
            throw new IllegalArgumentException("O endereço do tutor é obrigatório.");
        }

        if (tutor.getTelefone() == null || tutor.getTelefone().isBlank()) {
            throw new IllegalArgumentException("O telefone do tutor é obrigatório.");
        }

        return tutorRepository.salvar(tutor);
    }

    public List<Tutor> listar() {
        return tutorRepository.listar();
    }

    public Tutor buscarPorId(int id) {
        return tutorRepository.buscarPorId(id);
    }

    public void atualizar(Tutor tutor) {
        if (tutor.getId() <= 0) {
            throw new IllegalArgumentException("O ID do tutor é obrigatório para atualização.");
        }

        if (tutorRepository.buscarPorId(tutor.getId()) == null) {
            throw new IllegalArgumentException("Tutor não encontrado para atualização.");
        }

        if (tutor.getNome() == null || tutor.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do tutor é obrigatório.");
        }

        if (tutor.getEndereco() == null || tutor.getEndereco().isBlank()) {
            throw new IllegalArgumentException("O endereço do tutor é obrigatório.");
        }

        if (tutor.getTelefone() == null || tutor.getTelefone().isBlank()) {
            throw new IllegalArgumentException("O telefone do tutor é obrigatório.");
        }

        tutorRepository.atualizar(tutor);
    }

    public void excluir(int id) {
        tutorRepository.excluir(id);
    }
}
