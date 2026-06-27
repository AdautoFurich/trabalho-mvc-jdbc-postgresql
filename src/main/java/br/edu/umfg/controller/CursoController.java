package br.edu.umfg.controller;

import br.edu.umfg.model.Curso;
import br.edu.umfg.service.CursoService;

import java.util.List;

public class CursoController {

    private final CursoService cursoService = new CursoService();

    public Curso salvar(Curso curso) {
        Curso cursoSalvo = cursoService.salvar(curso);
        System.out.println("Curso cadastrado com sucesso! ID gerado: " + cursoSalvo.getId());
        return cursoSalvo;
    }

    public List<Curso> listar() {
        return cursoService.listar();
    }

    public Curso buscarPorId(int id) {
        return cursoService.buscarPorId(id);
    }

    public void atualizar(Curso curso) {
        cursoService.atualizar(curso);
        System.out.println("Curso atualizado com sucesso!");
    }

    public void excluir(int id) {
        cursoService.excluir(id);
        System.out.println("Curso excluido com sucesso!");
    }
}
