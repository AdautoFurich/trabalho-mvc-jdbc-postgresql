# Cenário 1 - Sistema de Clínica Veterinária

Projeto Java com arquitetura MVC, persistência via JDBC e banco PostgreSQL para o cenário 1 do trabalho.

## Integrantes

- Adauto Furich - RA: 2186
- Gabriel Barbosa - RA: 2106
- Francisco Jambers - RA: 2043

## Objetivo do cenário

Controlar tutores, animais e consultas de uma clínica veterinária, permitindo:

- cadastrar tutores;
- cadastrar animais vinculados a um tutor;
- registrar consultas para animais cadastrados;
- listar os animais de um tutor;
- listar o histórico de consultas de um animal.

## Estrutura MVC do projeto

- `model`: entidades `Tutor`, `Animal` e `Consulta`;
- `repository`: CRUD com SQL usando JDBC;
- `service`: validações e regras de negócio;
- `controller`: orquestra chamadas de entrada e saída;
- `util`: conexão com o PostgreSQL;
- `Main`: simulação do fluxo do cenário e menu de operação.

## Tabelas identificadas

### Tutor

Campos mínimos:

- `id`
- `nome`
- `endereco`
- `telefone`

### Animal

Campos mínimos:

- `id`
- `nome`
- `especie`
- `raca`
- `id_tutor`

### Consulta

Campos mínimos:

- `id`
- `id_animal`
- `data`
- `motivo`
- `valor`

## Regras de negócio levantadas

1. Um tutor deve ter nome, endereço e telefone obrigatórios.
2. Um animal deve ter nome, espécie e raça obrigatórios.
3. Um animal só pode ser cadastrado se o tutor informado existir.
4. Um tutor pode ter mais de um animal cadastrado.
5. Uma consulta só pode ser registrada se o animal informado existir.
6. A consulta deve possuir data obrigatória.
7. A consulta deve possuir motivo obrigatório.
8. O valor da consulta não pode ser negativo.
9. O sistema deve permitir consultar todas as consultas de um animal específico.
10. O sistema deve permitir consultar todos os animais vinculados a um tutor específico.
11. As validações acima devem ser respeitadas tanto no cadastro quanto na atualização dos registros.

## SQL - CREATE TABLE

```sql
CREATE TABLE tutor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    endereco VARCHAR(150) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

CREATE TABLE animal (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(80) NOT NULL,
    raca VARCHAR(80) NOT NULL,
    id_tutor INTEGER NOT NULL,
    CONSTRAINT fk_animal_tutor
        FOREIGN KEY (id_tutor)
        REFERENCES tutor (id)
);

CREATE TABLE consulta (
    id SERIAL PRIMARY KEY,
    id_animal INTEGER NOT NULL,
    data DATE NOT NULL,
    motivo VARCHAR(200) NOT NULL,
    valor NUMERIC(10, 2) NOT NULL CHECK (valor >= 0),
    CONSTRAINT fk_consulta_animal
        FOREIGN KEY (id_animal)
        REFERENCES animal (id)
);
```

## Banco de dados utilizado

- SGBD: PostgreSQL
- Banco configurado no projeto: `clinica_veterinaria`

## Fluxo simulado na Main

A classe `Main` executa diretamente a demonstração completa do cenário 1, sem `Scanner` e sem entrada manual:

1. cadastra um tutor;
2. cadastra um animal vinculado a esse tutor;
3. registra uma consulta para o animal;
4. lista os animais do tutor;
5. lista o histórico de consultas do animal;
6. exibe as listagens gerais das entidades.

## Observações para entrega

- Criar ou usar a branch `cenario1` no repositório GitHub.
- Preencher os nomes e RAs da equipe nesta documentação.
- Garantir que o PostgreSQL local tenha o banco `clinica_veterinaria` criado antes da execução.
