package br.edu.umfg.controller;

import br.edu.umfg.model.Cliente;
import br.edu.umfg.service.ClienteService;

import java.util.List;

public class ClienteController {

    private final ClienteService clienteService = new ClienteService();

    public Cliente salvar(Cliente cliente) {
        Cliente clienteSalvo = clienteService.salvar(cliente);
        System.out.println("Cliente cadastrado com sucesso! ID gerado: " + clienteSalvo.getId());
        return clienteSalvo;
    }

    public List<Cliente> listar() {
        return clienteService.listar();
    }

    public Cliente buscarPorId(int id) {
        return clienteService.buscarPorId(id);
    }

    public void atualizar(Cliente cliente) {
        clienteService.atualizar(cliente);
        System.out.println("Cliente atualizado com sucesso!");
    }

    public void excluir(int id) {
        clienteService.excluir(id);
        System.out.println("Cliente excluido com sucesso!");
    }
}
