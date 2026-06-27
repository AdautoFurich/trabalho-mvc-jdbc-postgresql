package br.edu.umfg.repository;

import br.edu.umfg.model.OrdemServico;
import br.edu.umfg.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrdemServicoRepository {

    public OrdemServico salvar(OrdemServico ordemServico) {
        String sql = "INSERT INTO ordem_servico (id_veiculo, descricao, valor, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, ordemServico.getIdVeiculo());
            stmt.setString(2, ordemServico.getDescricao());
            stmt.setBigDecimal(3, ordemServico.getValor());
            stmt.setString(4, ordemServico.getStatus());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    ordemServico.setId(rs.getInt(1));
                }
            }

            return ordemServico;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar ordem de servico.", e);
        }
    }

    public List<OrdemServico> listar() {
        List<OrdemServico> ordens = new ArrayList<>();
        String sql = "SELECT * FROM ordem_servico ORDER BY id";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ordens.add(new OrdemServico(
                        rs.getInt("id"),
                        rs.getInt("id_veiculo"),
                        rs.getString("descricao"),
                        rs.getBigDecimal("valor"),
                        rs.getString("status")
                ));
            }

            return ordens;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar ordens de servico.", e);
        }
    }

    public OrdemServico buscarPorId(int id) {
        String sql = "SELECT * FROM ordem_servico WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new OrdemServico(
                            rs.getInt("id"),
                            rs.getInt("id_veiculo"),
                            rs.getString("descricao"),
                            rs.getBigDecimal("valor"),
                            rs.getString("status")
                    );
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar ordem de servico por ID.", e);
        }
    }

    public List<OrdemServico> listarPorVeiculo(int idVeiculo) {
        List<OrdemServico> ordens = new ArrayList<>();
        String sql = "SELECT * FROM ordem_servico WHERE id_veiculo = ? ORDER BY id";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idVeiculo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ordens.add(new OrdemServico(
                            rs.getInt("id"),
                            rs.getInt("id_veiculo"),
                            rs.getString("descricao"),
                            rs.getBigDecimal("valor"),
                            rs.getString("status")
                    ));
                }
            }

            return ordens;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar ordens de servico do veiculo.", e);
        }
    }

    public void atualizar(OrdemServico ordemServico) {
        String sql = "UPDATE ordem_servico SET id_veiculo = ?, descricao = ?, valor = ?, status = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ordemServico.getIdVeiculo());
            stmt.setString(2, ordemServico.getDescricao());
            stmt.setBigDecimal(3, ordemServico.getValor());
            stmt.setString(4, ordemServico.getStatus());
            stmt.setInt(5, ordemServico.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhuma ordem de servico encontrada para atualizar.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar ordem de servico.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM ordem_servico WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhuma ordem de servico encontrada para excluir.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir ordem de servico.", e);
        }
    }
}
