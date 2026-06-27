package br.edu.umfg.repository;

import br.edu.umfg.model.Animal;
import br.edu.umfg.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AnimalRepository {

    public Animal salvar(Animal animal) {
        String sql = "INSERT INTO animal (nome, especie, raca, id_tutor) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setInt(4, animal.getIdTutor());

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    animal.setId(rs.getInt(1));
                }
            }

            return animal;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar animal.", e);
        }
    }

    public List<Animal> listar() {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT a.*, t.nome AS nome_tutor " +
                "FROM animal a " +
                "JOIN tutor t ON t.id = a.id_tutor " +
                "ORDER BY a.id";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Animal animal = new Animal(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("especie"),
                        rs.getString("raca"),
                        rs.getInt("id_tutor")
                );
                animal.setNomeTutor(rs.getString("nome_tutor"));

                animais.add(animal);
            }

            return animais;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar animais.", e);
        }
    }

    public Animal buscarPorId(int id) {
        String sql = "SELECT a.*, t.nome AS nome_tutor " +
                "FROM animal a " +
                "JOIN tutor t ON t.id = a.id_tutor " +
                "WHERE a.id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Animal animal = new Animal(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("especie"),
                            rs.getString("raca"),
                            rs.getInt("id_tutor")
                    );
                    animal.setNomeTutor(rs.getString("nome_tutor"));
                    return animal;
                }
            }

            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar animal por ID.", e);
        }
    }

    public List<Animal> listarPorTutor(int idTutor) {
        List<Animal> animais = new ArrayList<>();
        String sql = "SELECT a.*, t.nome AS nome_tutor " +
                "FROM animal a " +
                "JOIN tutor t ON t.id = a.id_tutor " +
                "WHERE a.id_tutor = ? " +
                "ORDER BY a.id";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idTutor);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Animal animal = new Animal(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("especie"),
                            rs.getString("raca"),
                            rs.getInt("id_tutor")
                    );
                    animal.setNomeTutor(rs.getString("nome_tutor"));

                    animais.add(animal);
                }
            }

            return animais;

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar animais do tutor.", e);
        }
    }

    public void atualizar(Animal animal) {
        String sql = "UPDATE animal SET nome=?, especie=?, raca=?, id_tutor=? WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, animal.getNome());
            stmt.setString(2, animal.getEspecie());
            stmt.setString(3, animal.getRaca());
            stmt.setInt(4, animal.getIdTutor());
            stmt.setInt(5, animal.getId());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum animal encontrado para atualizar.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar animal.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM animal WHERE id=?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum animal encontrado para excluir.");
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir animal.", e);
        }
    }
}
