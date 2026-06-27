package br.edu.umfg.repository;

import br.edu.umfg.model.Matricula;
import br.edu.umfg.util.Conexao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MatriculaRepository {

    public Matricula salvar(Matricula matricula) {
        String sql = "INSERT INTO matricula (id_aluno, id_curso, data_matricula, valor) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, matricula.getIdAluno());
            stmt.setInt(2, matricula.getIdCurso());
            stmt.setDate(3, Date.valueOf(matricula.getDataMatricula()));
            stmt.setBigDecimal(4, matricula.getValor());
            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    matricula.setId(rs.getInt(1));
                }
            }

            return matricula;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar matricula.", e);
        }
    }

    public List<Matricula> listar() {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT m.*, a.nome AS nome_aluno, c.nome AS nome_curso " +
                "FROM matricula m " +
                "JOIN aluno a ON a.id = m.id_aluno " +
                "JOIN curso c ON c.id = m.id_curso " +
                "ORDER BY m.id";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                matriculas.add(mapearMatricula(rs));
            }

            return matriculas;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar matriculas.", e);
        }
    }

    public Matricula buscarPorId(int id) {
        String sql = "SELECT m.*, a.nome AS nome_aluno, c.nome AS nome_curso " +
                "FROM matricula m " +
                "JOIN aluno a ON a.id = m.id_aluno " +
                "JOIN curso c ON c.id = m.id_curso " +
                "WHERE m.id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapearMatricula(rs);
                }
            }

            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar matricula por ID.", e);
        }
    }

    public List<Matricula> listarPorAluno(int idAluno) {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT m.*, a.nome AS nome_aluno, c.nome AS nome_curso " +
                "FROM matricula m " +
                "JOIN aluno a ON a.id = m.id_aluno " +
                "JOIN curso c ON c.id = m.id_curso " +
                "WHERE m.id_aluno = ? ORDER BY m.id";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    matriculas.add(mapearMatricula(rs));
                }
            }

            return matriculas;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar matriculas do aluno.", e);
        }
    }

    public List<Matricula> listarPorCurso(int idCurso) {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT m.*, a.nome AS nome_aluno, c.nome AS nome_curso " +
                "FROM matricula m " +
                "JOIN aluno a ON a.id = m.id_aluno " +
                "JOIN curso c ON c.id = m.id_curso " +
                "WHERE m.id_curso = ? ORDER BY m.id";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idCurso);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    matriculas.add(mapearMatricula(rs));
                }
            }

            return matriculas;
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar matriculas do curso.", e);
        }
    }

    public boolean existeMatricula(int idAluno, int idCurso) {
        String sql = "SELECT 1 FROM matricula WHERE id_aluno = ? AND id_curso = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            stmt.setInt(2, idCurso);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar duplicidade de matricula.", e);
        }
    }

    public boolean existeMatriculaOutroRegistro(int idAluno, int idCurso, int idExcluir) {
        String sql = "SELECT 1 FROM matricula WHERE id_aluno = ? AND id_curso = ? AND id <> ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idAluno);
            stmt.setInt(2, idCurso);
            stmt.setInt(3, idExcluir);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao verificar duplicidade de matricula.", e);
        }
    }

    public void atualizar(Matricula matricula) {
        String sql = "UPDATE matricula SET id_aluno = ?, id_curso = ?, data_matricula = ?, valor = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, matricula.getIdAluno());
            stmt.setInt(2, matricula.getIdCurso());
            stmt.setDate(3, Date.valueOf(matricula.getDataMatricula()));
            stmt.setBigDecimal(4, matricula.getValor());
            stmt.setInt(5, matricula.getId());

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhuma matricula encontrada para atualizar.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar matricula.", e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM matricula WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new RuntimeException("Nenhuma matricula encontrada para excluir.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir matricula.", e);
        }
    }

    private Matricula mapearMatricula(ResultSet rs) throws SQLException {
        Matricula matricula = new Matricula(
                rs.getInt("id"),
                rs.getInt("id_aluno"),
                rs.getInt("id_curso"),
                rs.getDate("data_matricula").toLocalDate(),
                rs.getBigDecimal("valor")
        );
        matricula.setNomeAluno(rs.getString("nome_aluno"));
        matricula.setNomeCurso(rs.getString("nome_curso"));
        return matricula;
    }
}
