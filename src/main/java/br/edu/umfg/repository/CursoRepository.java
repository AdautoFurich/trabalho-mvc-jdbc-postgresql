package br.edu.umfg.repository;

import br.edu.umfg.model.Curso;
import br.edu.umfg.util.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CursoRepository {

    public Curso salvar(Curso curso) {
        String sql = "INSERT INTO curso (nome, descricao, carga_horaria, vagas_totais, vagas_disponiveis) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getVagasTotais());
            stmt.setInt(5, curso.getVagasDisponiveis());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    curso.setId(rs.getInt(1));
                }
            }

            return curso;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar curso.", e);
        }
    }

    public List<Curso> listar() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso ORDER BY id";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                cursos.add(mapearCurso(rs));
            }

            return cursos;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar cursos.", e);
        }
    }

    public Curso buscarPorId(int id) {
        String sql = "SELECT * FROM curso WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearCurso(rs);
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar curso por ID.", e);
        }
    }

    public void atualizar(Curso curso) {
        String sql = "UPDATE curso SET nome = ?, descricao = ?, carga_horaria = ?, vagas_totais = ?, vagas_disponiveis = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, curso.getNome());
            stmt.setString(2, curso.getDescricao());
            stmt.setInt(3, curso.getCargaHoraria());
            stmt.setInt(4, curso.getVagasTotais());
            stmt.setInt(5, curso.getVagasDisponiveis());
            stmt.setInt(6, curso.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum curso encontrado para atualizar.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar curso.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM curso WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhum curso encontrado para excluir.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir curso.", e);
        }
    }

    public void atualizarVagasDisponiveis(int idCurso, int vagasDisponiveis) {
        String sql = "UPDATE curso SET vagas_disponiveis = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, vagasDisponiveis);
            stmt.setInt(2, idCurso);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar vagas disponiveis do curso.", e);
        }
    }

    private Curso mapearCurso(ResultSet rs) throws SQLException {
        return new Curso(
                rs.getInt("id"),
                rs.getString("nome"),
                rs.getString("descricao"),
                rs.getInt("carga_horaria"),
                rs.getInt("vagas_totais"),
                rs.getInt("vagas_disponiveis")
        );
    }
}
