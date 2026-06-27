package br.edu.umfg;

import br.edu.umfg.controller.AlunoController;
import br.edu.umfg.controller.CursoController;
import br.edu.umfg.controller.MatriculaController;
import br.edu.umfg.model.Aluno;
import br.edu.umfg.model.Curso;
import br.edu.umfg.model.Matricula;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    private static final String LINHA = "==================================================";

    public static void main(String[] args) {
        AlunoController alunoController = new AlunoController();
        CursoController cursoController = new CursoController();
        MatriculaController matriculaController = new MatriculaController();

        System.out.println(LINHA);
        System.out.println("CENARIO 3 - ESCOLA DE CURSOS LIVRES");
        System.out.println("Simulacao do fluxo: aluno -> curso -> matricula");
        System.out.println(LINHA);

        Aluno aluno = cadastrarAluno(alunoController);
        Curso curso = cadastrarCurso(cursoController);
        Matricula matricula = realizarMatricula(matriculaController, aluno, curso);
        testarMatriculaDuplicada(matriculaController, aluno, curso);

        System.out.println("\nRESUMO DOS REGISTROS GERADOS");
        System.out.println(LINHA);
        System.out.println("Aluno: " + aluno.getId() + " - " + aluno.getNome());
        System.out.println("Curso: " + curso.getId() + " - " + curso.getNome());
        System.out.println("Matricula: " + matricula.getId() + " - " + curso.getNome());

        System.out.println("\nMATRICULAS DO ALUNO");
        System.out.println(LINHA);
        exibirMatriculas(matriculaController.listarPorAluno(aluno.getId()));

        System.out.println("\nALUNOS MATRICULADOS NO CURSO");
        System.out.println(LINHA);
        exibirMatriculas(matriculaController.listarPorCurso(curso.getId()));

        System.out.println("\nLISTAGEM GERAL DE ALUNOS");
        System.out.println(LINHA);
        exibirAlunos(alunoController.listar());

        System.out.println("\nLISTAGEM GERAL DE CURSOS");
        System.out.println(LINHA);
        exibirCursos(cursoController.listar());

        System.out.println("\nLISTAGEM GERAL DE MATRICULAS");
        System.out.println(LINHA);
        exibirMatriculas(matriculaController.listar());

        System.out.println("\nExecucao finalizada.");
    }

    private static Aluno cadastrarAluno(AlunoController alunoController) {
        System.out.println("\n1. Cadastro do aluno");
        Aluno aluno = new Aluno("Adauto Furich", "adauto@email.com", "(44) 99772-0693");
        return alunoController.salvar(aluno);
    }

    private static Curso cadastrarCurso(CursoController cursoController) {
        System.out.println("\n2. Cadastro do curso");
        Curso curso = new Curso("Java Web", "Curso introdutorio de Java com JDBC", 40, 1, 1);
        return cursoController.salvar(curso);
    }

    private static Matricula realizarMatricula(MatriculaController matriculaController, Aluno aluno, Curso curso) {
        System.out.println("\n3. Registro da matricula do aluno no curso");
        Matricula matricula = new Matricula(
                aluno.getId(),
                curso.getId(),
                LocalDate.now(),
                new BigDecimal("499.90")
        );
        return matriculaController.salvar(matricula);
    }

    private static void testarMatriculaDuplicada(MatriculaController matriculaController, Aluno aluno, Curso curso) {
        System.out.println("\n4. Teste de matricula invalida duplicada");

        try {
            matriculaController.salvar(new Matricula(
                    aluno.getId(),
                    curso.getId(),
                    LocalDate.now(),
                    new BigDecimal("499.90")
            ));
        } catch (IllegalArgumentException e) {
            System.out.println("Regra validada com sucesso: " + e.getMessage());
        }
    }

    private static void exibirAlunos(List<Aluno> alunos) {
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno encontrado.");
            return;
        }

        for (Aluno aluno : alunos) {
            System.out.println(
                    "ID: " + aluno.getId()
                            + " | Nome: " + aluno.getNome()
                            + " | Email: " + aluno.getEmail()
                            + " | Telefone: " + aluno.getTelefone()
            );
        }
    }

    private static void exibirCursos(List<Curso> cursos) {
        if (cursos.isEmpty()) {
            System.out.println("Nenhum curso encontrado.");
            return;
        }

        for (Curso curso : cursos) {
            System.out.println(
                    "ID: " + curso.getId()
                            + " | Nome: " + curso.getNome()
                            + " | Carga Horaria: " + curso.getCargaHoraria()
                            + " | Vagas Totais: " + curso.getVagasTotais()
                            + " | Vagas Disponiveis: " + curso.getVagasDisponiveis()
            );
        }
    }

    private static void exibirMatriculas(List<Matricula> matriculas) {
        if (matriculas.isEmpty()) {
            System.out.println("Nenhuma matricula encontrada.");
            return;
        }

        for (Matricula matricula : matriculas) {
            System.out.println(
                    "ID: " + matricula.getId()
                            + " | ID Aluno: " + matricula.getIdAluno()
                            + " | Aluno: " + matricula.getNomeAluno()
                            + " | ID Curso: " + matricula.getIdCurso()
                            + " | Curso: " + matricula.getNomeCurso()
                            + " | Data: " + matricula.getDataMatricula()
                            + " | Valor: R$ " + matricula.getValor()
            );
        }
    }
}
