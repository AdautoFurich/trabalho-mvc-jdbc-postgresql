package br.edu.umfg.service;

import br.edu.umfg.model.Veiculo;
import br.edu.umfg.repository.ClienteRepository;
import br.edu.umfg.repository.VeiculoRepository;

import java.util.List;

public class VeiculoService {

    private final VeiculoRepository veiculoRepository = new VeiculoRepository();
    private final ClienteRepository clienteRepository = new ClienteRepository();

    public Veiculo salvar(Veiculo veiculo) {
        validarVeiculo(veiculo);
        return veiculoRepository.salvar(veiculo);
    }

    public List<Veiculo> listar() {
        return veiculoRepository.listar();
    }

    public Veiculo buscarPorId(int id) {
        return veiculoRepository.buscarPorId(id);
    }

    public List<Veiculo> listarPorCliente(int idCliente) {
        return veiculoRepository.listarPorCliente(idCliente);
    }

    public void atualizar(Veiculo veiculo) {
        if (veiculo.getId() <= 0) {
            throw new IllegalArgumentException("O ID do veiculo é obrigatorio para atualizacao.");
        }

        if (veiculoRepository.buscarPorId(veiculo.getId()) == null) {
            throw new IllegalArgumentException("Veiculo nao encontrado para atualizacao.");
        }

        validarVeiculo(veiculo);
        veiculoRepository.atualizar(veiculo);
    }

    public void excluir(int id) {
        veiculoRepository.excluir(id);
    }

    private void validarVeiculo(Veiculo veiculo) {
        if (veiculo.getPlaca() == null || veiculo.getPlaca().isBlank()) {
            throw new IllegalArgumentException("A placa do veiculo é obrigatoria.");
        }

        if (veiculo.getModelo() == null || veiculo.getModelo().isBlank()) {
            throw new IllegalArgumentException("O modelo do veiculo é obrigatorio.");
        }

        if (veiculo.getAno() <= 0) {
            throw new IllegalArgumentException("O ano do veiculo é obrigatorio.");
        }

        if (clienteRepository.buscarPorId(veiculo.getIdCliente()) == null) {
            throw new IllegalArgumentException("Nao é possivel cadastrar veiculo para cliente inexistente.");
        }
    }
}
