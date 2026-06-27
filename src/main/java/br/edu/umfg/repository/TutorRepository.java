package br.edu.umfg.repository;

import br.edu.umfg.model.Tutor;
import br.edu.umfg.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TutorRepository {

    public Tutor salvar(Tutor tutor) {
        String sql = "INSERT INTO tutor (nome, endereco, telefone) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getEndereco());
            stmt.setString(3, tutor.getTelefone());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    tutor.setId(rs.getInt(1));
                }
            }

            return tutor;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar tutor.", e);
        }
    }

    public List<Tutor> listar() {
        List<Tutor> tutores = new ArrayList<>();
        String sql = "SELECT * FROM tutor ORDER BY id";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Tutor tutor = new Tutor(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("endereco"),
                        rs.getString("telefone")
                );

                tutores.add(tutor);
            }

            return tutores;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar tutores.", e);
        }
    }

    public Tutor buscarPorId(int id) {
        String sql = "SELECT * FROM tutor WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Tutor(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("endereco"),
                            rs.getString("telefone")
                    );
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar tutor por ID.", e);
        }
    }

    public void atualizar(Tutor tutor) {
        String sql = "UPDATE tutor SET nome=?, endereco=?, telefone=? WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, tutor.getNome());
            stmt.setString(2, tutor.getEndereco());
            stmt.setString(3, tutor.getTelefone());
            stmt.setInt(4, tutor.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum tutor encontrado para atualizar.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tutor.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM tutor WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum tutor encontrado para excluir.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir tutor.", e);
        }
    }
}