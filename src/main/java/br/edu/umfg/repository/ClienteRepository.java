package br.edu.umfg.repository;

import br.edu.umfg.model.Cliente;
import br.edu.umfg.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {

    public Cliente salvar(Cliente cliente) {
        String sql = "INSERT INTO cliente (nome, telefone) VALUES (?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                }
            }

            return cliente;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar cliente.", e);
        }
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente ORDER BY id";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("telefone")
                ));
            }

            return clientes;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar clientes.", e);
        }
    }

    public Cliente buscarPorId(int id) {
        String sql = "SELECT * FROM cliente WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Cliente(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("telefone")
                    );
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar cliente por ID.", e);
        }
    }

    public void atualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, telefone = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTelefone());
            stmt.setInt(3, cliente.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum cliente encontrado para atualizar.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar cliente.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM cliente WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum cliente encontrado para excluir.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir cliente.", e);
        }
    }
}
