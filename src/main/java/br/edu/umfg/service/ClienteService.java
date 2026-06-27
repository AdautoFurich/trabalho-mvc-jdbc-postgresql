package br.edu.umfg.service;

import br.edu.umfg.model.Cliente;
import br.edu.umfg.repository.ClienteRepository;

import java.util.List;

public class ClienteService {

    private final ClienteRepository clienteRepository = new ClienteRepository();

    public Cliente salvar(Cliente cliente) {
        validarCliente(cliente);
        return clienteRepository.salvar(cliente);
    }

    public List<Cliente> listar() {
        return clienteRepository.listar();
    }

    public Cliente buscarPorId(int id) {
        return clienteRepository.buscarPorId(id);
    }

    public void atualizar(Cliente cliente) {
        if (cliente.getId() <= 0) {
            throw new IllegalArgumentException("O ID do cliente é obrigatorio para atualizacao.");
        }

        if (clienteRepository.buscarPorId(cliente.getId()) == null) {
            throw new IllegalArgumentException("Cliente nao encontrado para atualizacao.");
        }

        validarCliente(cliente);
        clienteRepository.atualizar(cliente);
    }

    public void excluir(int id) {
        clienteRepository.excluir(id);
    }

    private void validarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do cliente é obrigatorio.");
        }

        if (cliente.getTelefone() == null || cliente.getTelefone().isBlank()) {
            throw new IllegalArgumentException("O telefone do cliente é obrigatorio.");
        }
    }
}
