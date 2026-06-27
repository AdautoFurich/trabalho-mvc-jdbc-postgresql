package br.edu.umfg.controller;

import br.edu.umfg.model.Consulta;
import br.edu.umfg.service.ConsultaService;

import java.util.List;

public class ConsultaController {

    private final ConsultaService consultaService = new ConsultaService();

    public Consulta salvar(Consulta consulta) {
        Consulta consultaSalva = consultaService.salvar(consulta);
        System.out.println("✓ Consulta cadastrada com sucesso! ID gerado: " + consultaSalva.getId());
        return consultaSalva;
    }

    public List<Consulta> listar() {
        return consultaService.listar();
    }

    public Consulta buscarPorId(int id) {
        return consultaService.buscarPorId(id);
    }

    public List<Consulta> listarPorAnimal(int idAnimal) {
        return consultaService.listarPorAnimal(idAnimal);
    }

    public void atualizar(Consulta consulta) {
        consultaService.atualizar(consulta);
        System.out.println("✓ Consulta atualizada com sucesso!");
    }

    public void excluir(int id) {
        consultaService.excluir(id);
        System.out.println("✓ Consulta excluída com sucesso!");
    }
}