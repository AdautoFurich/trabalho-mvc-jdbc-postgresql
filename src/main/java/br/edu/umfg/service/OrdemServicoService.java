package br.edu.umfg.service;

import br.edu.umfg.model.OrdemServico;
import br.edu.umfg.repository.OrdemServicoRepository;
import br.edu.umfg.repository.VeiculoRepository;

import java.math.BigDecimal;
import java.util.List;

public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository = new OrdemServicoRepository();
    private final VeiculoRepository veiculoRepository = new VeiculoRepository();

    public OrdemServico salvar(OrdemServico ordemServico) {
        validarOrdemServico(ordemServico);
        return ordemServicoRepository.salvar(ordemServico);
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.listar();
    }

    public OrdemServico buscarPorId(int id) {
        return ordemServicoRepository.buscarPorId(id);
    }

    public List<OrdemServico> listarPorVeiculo(int idVeiculo) {
        return ordemServicoRepository.listarPorVeiculo(idVeiculo);
    }

    public void atualizar(OrdemServico ordemServico) {
        if (ordemServico.getId() <= 0) {
            throw new IllegalArgumentException("O ID da ordem de servico é obrigatorio para atualizacao.");
        }

        if (ordemServicoRepository.buscarPorId(ordemServico.getId()) == null) {
            throw new IllegalArgumentException("Ordem de servico nao encontrada para atualizacao.");
        }

        validarOrdemServico(ordemServico);
        ordemServicoRepository.atualizar(ordemServico);
    }

    public void excluir(int id) {
        ordemServicoRepository.excluir(id);
    }

    private void validarOrdemServico(OrdemServico ordemServico) {
        if (veiculoRepository.buscarPorId(ordemServico.getIdVeiculo()) == null) {
            throw new IllegalArgumentException("Nao é possivel abrir ordem de servico para veiculo inexistente.");
        }

        if (ordemServico.getDescricao() == null || ordemServico.getDescricao().isBlank()) {
            throw new IllegalArgumentException("A descricao da ordem de servico é obrigatoria.");
        }

        if (ordemServico.getValor() == null || ordemServico.getValor().compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("O valor do servico nao pode ser negativo.");
        }

        if (ordemServico.getStatus() == null || ordemServico.getStatus().isBlank()) {
            throw new IllegalArgumentException("O status da ordem de servico é obrigatorio.");
        }

        String statusNormalizado = ordemServico.getStatus().trim().toUpperCase();
        if (!statusNormalizado.equals("ABERTA") && !statusNormalizado.equals("CONCLUIDA")) {
            throw new IllegalArgumentException("O status da ordem de servico deve ser ABERTA ou CONCLUIDA.");
        }

        ordemServico.setStatus(statusNormalizado);
    }
}
