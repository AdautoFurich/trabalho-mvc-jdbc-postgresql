package br.edu.umfg.controller;

import br.edu.umfg.model.Veiculo;
import br.edu.umfg.service.VeiculoService;

import java.util.List;

public class VeiculoController {

    private final VeiculoService veiculoService = new VeiculoService();

    public Veiculo salvar(Veiculo veiculo) {
        Veiculo veiculoSalvo = veiculoService.salvar(veiculo);
        System.out.println("Veiculo cadastrado com sucesso! ID gerado: " + veiculoSalvo.getId());
        return veiculoSalvo;
    }

    public List<Veiculo> listar() {
        return veiculoService.listar();
    }

    public Veiculo buscarPorId(int id) {
        return veiculoService.buscarPorId(id);
    }

    public List<Veiculo> listarPorCliente(int idCliente) {
        return veiculoService.listarPorCliente(idCliente);
    }

    public void atualizar(Veiculo veiculo) {
        veiculoService.atualizar(veiculo);
        System.out.println("Veiculo atualizado com sucesso!");
    }

    public void excluir(int id) {
        veiculoService.excluir(id);
        System.out.println("Veiculo excluido com sucesso!");
    }
}
