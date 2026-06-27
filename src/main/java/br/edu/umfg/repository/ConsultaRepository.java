package br.edu.umfg.repository;

import br.edu.umfg.model.Consulta;
import br.edu.umfg.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaRepository {

    public Consulta salvar(Consulta consulta) {
        String sql = "INSERT INTO consulta (id_animal, data, motivo, valor) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, consulta.getIdAnimal());
            stmt.setDate(2, Date.valueOf(consulta.getData()));
            stmt.setString(3, consulta.getMotivo());
            stmt.setBigDecimal(4, consulta.getValor());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    consulta.setId(rs.getInt(1));
                }
            }

            return consulta;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar consulta.", e);
        }
    }

    public List<Consulta> listar() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consulta ORDER BY id";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Consulta consulta = new Consulta(
                        rs.getInt("id"),
                        rs.getInt("id_animal"),
                        rs.getDate("data").toLocalDate(),
                        rs.getString("motivo"),
                        rs.getBigDecimal("valor")
                );

                consultas.add(consulta);
            }

            return consultas;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas.", e);
        }
    }

    public Consulta buscarPorId(int id) {
        String sql = "SELECT * FROM consulta WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Consulta(
                            rs.getInt("id"),
                            rs.getInt("id_animal"),
                            rs.getDate("data").toLocalDate(),
                            rs.getString("motivo"),
                            rs.getBigDecimal("valor")
                    );
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar consulta por ID.", e);
        }
    }

    public List<Consulta> listarPorAnimal(int idAnimal) {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM consulta WHERE id_animal = ? ORDER BY data";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAnimal);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Consulta consulta = new Consulta(
                            rs.getInt("id"),
                            rs.getInt("id_animal"),
                            rs.getDate("data").toLocalDate(),
                            rs.getString("motivo"),
                            rs.getBigDecimal("valor")
                    );

                    consultas.add(consulta);
                }
            }

            return consultas;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar consultas do animal.", e);
        }
    }

    public void atualizar(Consulta consulta) {
        String sql = "UPDATE consulta SET id_animal=?, data=?, motivo=?, valor=? WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getIdAnimal());
            stmt.setDate(2, Date.valueOf(consulta.getData()));
            stmt.setString(3, consulta.getMotivo());
            stmt.setBigDecimal(4, consulta.getValor());
            stmt.setInt(5, consulta.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhuma consulta encontrada para atualizar.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar consulta.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM consulta WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhuma consulta encontrada para excluir.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir consulta.", e);
        }
    }
}