package br.edu.umfg.controller;

import br.edu.umfg.model.Aluno;
import br.edu.umfg.service.AlunoService;

import java.util.List;

public class AlunoController {

    private final AlunoService alunoService = new AlunoService();

    public Aluno salvar(Aluno aluno) {
        Aluno alunoSalvo = alunoService.salvar(aluno);
        System.out.println("Aluno cadastrado com sucesso! ID gerado: " + alunoSalvo.getId());
        return alunoSalvo;
    }

    public List<Aluno> listar() {
        return alunoService.listar();
    }

    public Aluno buscarPorId(int id) {
        return alunoService.buscarPorId(id);
    }

    public void atualizar(Aluno aluno) {
        alunoService.atualizar(aluno);
        System.out.println("Aluno atualizado com sucesso!");
    }

    public void excluir(int id) {
        alunoService.excluir(id);
        System.out.println("Aluno excluido com sucesso!");
    }
}
