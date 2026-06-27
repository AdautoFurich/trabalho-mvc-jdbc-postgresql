package br.edu.umfg;

import br.edu.umfg.controller.ClienteController;
import br.edu.umfg.controller.OrdemServicoController;
import br.edu.umfg.controller.VeiculoController;
import br.edu.umfg.model.Cliente;
import br.edu.umfg.model.OrdemServico;
import br.edu.umfg.model.Veiculo;

import java.math.BigDecimal;
import java.util.List;

public class Main {

    private static final String LINHA = "==================================================";

    public static void main(String[] args) {
        ClienteController clienteController = new ClienteController();
        VeiculoController veiculoController = new VeiculoController();
        OrdemServicoController ordemServicoController = new OrdemServicoController();

        System.out.println(LINHA);
        System.out.println("CENARIO 2 - OFICINA MECANICA");
        System.out.println("Simulacao do fluxo: cliente -> veiculo -> ordem de servico");
        System.out.println(LINHA);

        Cliente cliente = cadastrarCliente(clienteController);
        Veiculo veiculo = cadastrarVeiculo(veiculoController, cliente);
        OrdemServico ordemServico = abrirOrdemServico(ordemServicoController, veiculo);

        System.out.println("\nRESUMO DOS REGISTROS GERADOS");
        System.out.println(LINHA);
        System.out.println("Cliente: " + cliente.getId() + " - " + cliente.getNome());
        System.out.println("Veiculo: " + veiculo.getId() + " - " + veiculo.getModelo());
        System.out.println("Ordem de servico: " + ordemServico.getId() + " - " + ordemServico.getDescricao());

        System.out.println("\nVEICULOS DO CLIENTE");
        System.out.println(LINHA);
        exibirVeiculos(veiculoController.listarPorCliente(cliente.getId()));

        System.out.println("\nHISTORICO DE ORDENS DE SERVICO DO VEICULO");
        System.out.println(LINHA);
        exibirOrdensServico(ordemServicoController.listarPorVeiculo(veiculo.getId()));

        System.out.println("\nLISTAGEM GERAL DE CLIENTES");
        System.out.println(LINHA);
        exibirClientes(clienteController.listar());

        System.out.println("\nLISTAGEM GERAL DE VEICULOS");
        System.out.println(LINHA);
        exibirVeiculos(veiculoController.listar());

        System.out.println("\nLISTAGEM GERAL DE ORDENS DE SERVICO");
        System.out.println(LINHA);
        exibirOrdensServico(ordemServicoController.listar());

        System.out.println("\nExecucao finalizada.");
    }

    private static Cliente cadastrarCliente(ClienteController clienteController) {
        System.out.println("\n1. Cadastro do cliente");
        Cliente cliente = new Cliente("Adauto Furich", "(44) 99772-0693");
        return clienteController.salvar(cliente);
    }

    private static Veiculo cadastrarVeiculo(VeiculoController veiculoController, Cliente cliente) {
        System.out.println("\n2. Cadastro do veiculo vinculado ao cliente");
        Veiculo veiculo = new Veiculo("ABC1D23", "Honda Civic", 2018, cliente.getId());
        return veiculoController.salvar(veiculo);
    }

    private static OrdemServico abrirOrdemServico(OrdemServicoController ordemServicoController, Veiculo veiculo) {
        System.out.println("\n3. Abertura da ordem de servico para o veiculo");
        OrdemServico ordemServico = new OrdemServico(
                veiculo.getId(),
                "Troca de pastilhas de freio",
                new BigDecimal("350.00"),
                "ABERTA"
        );
        return ordemServicoController.salvar(ordemServico);
    }

    private static void exibirClientes(List<Cliente> clientes) {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente encontrado.");
            return;
        }

        for (Cliente cliente : clientes) {
            System.out.println(
                    "ID: " + cliente.getId()
                            + " | Nome: " + cliente.getNome()
                            + " | Telefone: " + cliente.getTelefone()
            );
        }
    }

    private static void exibirVeiculos(List<Veiculo> veiculos) {
        if (veiculos.isEmpty()) {
            System.out.println("Nenhum veiculo encontrado.");
            return;
        }

        for (Veiculo veiculo : veiculos) {
            System.out.println(
                    "ID: " + veiculo.getId()
                            + " | Placa: " + veiculo.getPlaca()
                            + " | Modelo: " + veiculo.getModelo()
                            + " | Ano: " + veiculo.getAno()
                            + " | ID Cliente: " + veiculo.getIdCliente()
                            + " | Cliente: " + veiculo.getNomeCliente()
            );
        }
    }

    private static void exibirOrdensServico(List<OrdemServico> ordensServico) {
        if (ordensServico.isEmpty()) {
            System.out.println("Nenhuma ordem de servico encontrada.");
            return;
        }

        for (OrdemServico ordemServico : ordensServico) {
            System.out.println(
                    "ID: " + ordemServico.getId()
                            + " | ID Veiculo: " + ordemServico.getIdVeiculo()
                            + " | Descricao: " + ordemServico.getDescricao()
                            + " | Valor: R$ " + ordemServico.getValor()
                            + " | Status: " + ordemServico.getStatus()
            );
        }
    }
}
