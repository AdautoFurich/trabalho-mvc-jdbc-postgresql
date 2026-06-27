package br.edu.umfg.controller;

import br.edu.umfg.model.Matricula;
import br.edu.umfg.service.MatriculaService;

import java.util.List;

public class MatriculaController {

    private final MatriculaService matriculaService = new MatriculaService();

    public Matricula salvar(Matricula matricula) {
        Matricula matriculaSalva = matriculaService.salvar(matricula);
        System.out.println("Matricula cadastrada com sucesso! ID gerado: " + matriculaSalva.getId());
        return matriculaSalva;
    }

    public List<Matricula> listar() {
        return matriculaService.listar();
    }

    public Matricula buscarPorId(int id) {
        return matriculaService.buscarPorId(id);
    }

    public List<Matricula> listarPorAluno(int idAluno) {
        return matriculaService.listarPorAluno(idAluno);
    }

    public List<Matricula> listarPorCurso(int idCurso) {
        return matriculaService.listarPorCurso(idCurso);
    }

    public void atualizar(Matricula matricula) {
        matriculaService.atualizar(matricula);
        System.out.println("Matricula atualizada com sucesso!");
    }

    public void excluir(int id) {
        matriculaService.excluir(id);
        System.out.println("Matricula excluida com sucesso!");
    }
}
