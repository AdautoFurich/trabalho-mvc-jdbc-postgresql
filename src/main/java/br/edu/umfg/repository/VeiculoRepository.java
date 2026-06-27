package br.edu.umfg.repository;

import br.edu.umfg.model.Veiculo;
import br.edu.umfg.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VeiculoRepository {

    public Veiculo salvar(Veiculo veiculo) {
        String sql = "INSERT INTO veiculo (placa, modelo, ano, id_cliente) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setInt(4, veiculo.getIdCliente());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    veiculo.setId(rs.getInt(1));
                }
            }

            return veiculo;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar veiculo.", e);
        }
    }

    public List<Veiculo> listar() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT v.*, c.nome AS nome_cliente " +
                "FROM veiculo v " +
                "JOIN cliente c ON c.id = v.id_cliente " +
                "ORDER BY v.id";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo(
                        rs.getInt("id"),
                        rs.getString("placa"),
                        rs.getString("modelo"),
                        rs.getInt("ano"),
                        rs.getInt("id_cliente")
                );
                veiculo.setNomeCliente(rs.getString("nome_cliente"));
                veiculos.add(veiculo);
            }

            return veiculos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veiculos.", e);
        }
    }

    public Veiculo buscarPorId(int id) {
        String sql = "SELECT v.*, c.nome AS nome_cliente " +
                "FROM veiculo v " +
                "JOIN cliente c ON c.id = v.id_cliente " +
                "WHERE v.id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Veiculo veiculo = new Veiculo(
                            rs.getInt("id"),
                            rs.getString("placa"),
                            rs.getString("modelo"),
                            rs.getInt("ano"),
                            rs.getInt("id_cliente")
                    );
                    veiculo.setNomeCliente(rs.getString("nome_cliente"));
                    return veiculo;
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar veiculo por ID.", e);
        }
    }

    public List<Veiculo> listarPorCliente(int idCliente) {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT v.*, c.nome AS nome_cliente " +
                "FROM veiculo v " +
                "JOIN cliente c ON c.id = v.id_cliente " +
                "WHERE v.id_cliente = ? " +
                "ORDER BY v.id";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Veiculo veiculo = new Veiculo(
                            rs.getInt("id"),
                            rs.getString("placa"),
                            rs.getString("modelo"),
                            rs.getInt("ano"),
                            rs.getInt("id_cliente")
                    );
                    veiculo.setNomeCliente(rs.getString("nome_cliente"));
                    veiculos.add(veiculo);
                }
            }

            return veiculos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar veiculos do cliente.", e);
        }
    }

    public void atualizar(Veiculo veiculo) {
        String sql = "UPDATE veiculo SET placa = ?, modelo = ?, ano = ?, id_cliente = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setInt(3, veiculo.getAno());
            stmt.setInt(4, veiculo.getIdCliente());
            stmt.setInt(5, veiculo.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum veiculo encontrado para atualizar.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar veiculo.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM veiculo WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum veiculo encontrado para excluir.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir veiculo.", e);
        }
    }
}
