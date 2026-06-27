package br.edu.umfg.controller;

import br.edu.umfg.model.OrdemServico;
import br.edu.umfg.service.OrdemServicoService;

import java.util.List;

public class OrdemServicoController {

    private final OrdemServicoService ordemServicoService = new OrdemServicoService();

    public OrdemServico salvar(OrdemServico ordemServico) {
        OrdemServico ordemSalva = ordemServicoService.salvar(ordemServico);
        System.out.println("Ordem de servico cadastrada com sucesso! ID gerado: " + ordemSalva.getId());
        return ordemSalva;
    }

    public List<OrdemServico> listar() {
        return ordemServicoService.listar();
    }

    public OrdemServico buscarPorId(int id) {
        return ordemServicoService.buscarPorId(id);
    }

    public List<OrdemServico> listarPorVeiculo(int idVeiculo) {
        return ordemServicoService.listarPorVeiculo(idVeiculo);
    }

    public void atualizar(OrdemServico ordemServico) {
        ordemServicoService.atualizar(ordemServico);
        System.out.println("Ordem de servico atualizada com sucesso!");
    }

    public void excluir(int id) {
        ordemServicoService.excluir(id);
        System.out.println("Ordem de servico excluida com sucesso!");
    }
}
