# Cenário 3 - Sistema de Escola de Cursos Livres

Projeto Java com arquitetura MVC, persistência via JDBC e banco PostgreSQL para o cenário 3 do trabalho.

## Integrantes

- Adauto Furich - RA: 2186
- Gabriel Barbosa - RA: 2106
- Francisco Jambers - RA: 2043

## Objetivo do cenário

Controlar alunos, cursos e matrículas de uma escola de cursos livres, permitindo:

- cadastrar alunos;
- cadastrar cursos;
- registrar a matrícula de um aluno em um curso;
- listar todos os alunos matriculados em um curso;
- listar todos os cursos em que um aluno está matriculado.

## Estrutura MVC do projeto

- `model`: entidades `Aluno`, `Curso` e `Matricula`;
- `repository`: CRUD com SQL usando JDBC;
- `service`: validações e regras de negócio;
- `controller`: orquestra chamadas de entrada e saída;
- `util`: conexão com o PostgreSQL;
- `Main`: simulação do fluxo do cenário.

## Tabelas identificadas

### Aluno

Campos mínimos:

- `id`
- `nome`
- `email`
- `telefone`

### Curso

Campos mínimos:

- `id`
- `nome`
- `descricao`
- `carga_horaria`
- `vagas_totais`
- `vagas_disponiveis`

### Matricula

Campos mínimos:

- `id`
- `id_aluno`
- `id_curso`
- `data_matricula`
- `valor`

## Regras de negócio levantadas

1. Um aluno deve ter nome, email e telefone obrigatórios.
2. Um curso deve ter nome, descrição, carga horária e número máximo de vagas obrigatórios.
3. O número de vagas disponíveis não pode ser negativo.
4. O número de vagas disponíveis não pode ser maior que o total de vagas.
5. Não é permitido matricular aluno inexistente.
6. Não é permitido matricular aluno em curso inexistente.
7. A data da matrícula é obrigatória.
8. O valor pago na matrícula não pode ser negativo.
9. Não é permitido matricular o mesmo aluno duas vezes no mesmo curso.
10. Não é permitido matricular aluno em curso sem vagas disponíveis.
11. O sistema deve permitir consultar todos os alunos matriculados em um curso.
12. O sistema deve permitir consultar todos os cursos em que um aluno está matriculado.
13. As validações acima devem ser respeitadas tanto no cadastro quanto na atualização dos registros.

## SQL - CREATE TABLE

```sql
CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    carga_horaria INTEGER NOT NULL,
    vagas_totais INTEGER NOT NULL,
    vagas_disponiveis INTEGER NOT NULL
);

CREATE TABLE matricula (
    id SERIAL PRIMARY KEY,
    id_aluno INTEGER NOT NULL,
    id_curso INTEGER NOT NULL,
    data_matricula DATE NOT NULL,
    valor NUMERIC(10, 2) NOT NULL CHECK (valor >= 0),
    CONSTRAINT fk_matricula_aluno
        FOREIGN KEY (id_aluno)
        REFERENCES aluno (id),
    CONSTRAINT fk_matricula_curso
        FOREIGN KEY (id_curso)
        REFERENCES curso (id),
    CONSTRAINT uq_matricula_aluno_curso UNIQUE (id_aluno, id_curso)
);
```

## Banco de dados utilizado

- SGBD: PostgreSQL
- Banco configurado no projeto: `escola_cursos_livres`

## Fluxo simulado na Main

A classe `Main` executa diretamente a demonstração completa do cenário 3, sem `Scanner` e sem entrada manual:

1. cadastra um aluno;
2. cadastra um curso;
3. registra a matrícula do aluno no curso;
4. tenta registrar uma matrícula inválida duplicada para demonstrar a regra de negócio;
5. lista as matrículas do aluno;
6. lista os alunos matriculados no curso;
7. exibe as listagens gerais das entidades.