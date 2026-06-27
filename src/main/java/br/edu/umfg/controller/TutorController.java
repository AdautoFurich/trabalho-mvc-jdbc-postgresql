package br.edu.umfg.controller;

import br.edu.umfg.model.Tutor;
import br.edu.umfg.service.TutorService;

import java.util.List;

public class TutorController {

    private final TutorService tutorService = new TutorService();

    public Tutor salvar(Tutor tutor) {
        Tutor tutorSalvo = tutorService.salvar(tutor);
        System.out.println("✓ Tutor cadastrado com sucesso! ID gerado: " + tutorSalvo.getId());
        return tutorSalvo;
    }

    public List<Tutor> listar() {
        return tutorService.listar();
    }

    public Tutor buscarPorId(int id) {
        return tutorService.buscarPorId(id);
    }

    public void atualizar(Tutor tutor) {
        tutorService.atualizar(tutor);
        System.out.println("✓ Tutor atualizado com sucesso!");
    }

    public void excluir(int id) {
        tutorService.excluir(id);
        System.out.println("✓ Tutor excluído com sucesso!");
    }
}