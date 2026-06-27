# Cenário 2 - Sistema de Oficina Mecânica

Projeto Java com arquitetura MVC, persistência via JDBC e banco PostgreSQL para o cenário 2 do trabalho.

## Integrantes

- Adauto Furich - RA: 2186
- Gabriel Barbosa - RA: 2106
- Francisco Jambers - RA: 2043

## Objetivo do cenário

Controlar clientes, veículos e ordens de serviço de uma oficina mecânica, permitindo:

- cadastrar clientes;
- cadastrar veículos vinculados a um cliente;
- abrir ordens de serviço para veículos cadastrados;
- listar os veículos de um cliente;
- listar o histórico de ordens de serviço de um veículo.

## Estrutura MVC do projeto

- `model`: entidades `Cliente`, `Veiculo` e `OrdemServico`;
- `repository`: CRUD com SQL usando JDBC;
- `service`: validações e regras de negócio;
- `controller`: orquestra chamadas de entrada e saída;
- `util`: conexão com o PostgreSQL;
- `Main`: simulação do fluxo do cenário.

## Tabelas identificadas

### Cliente

Campos mínimos:

- `id`
- `nome`
- `telefone`

### Veiculo

Campos mínimos:

- `id`
- `placa`
- `modelo`
- `ano`
- `id_cliente`

### OrdemServico

Campos mínimos:

- `id`
- `id_veiculo`
- `descricao`
- `valor`
- `status`

## Regras de negócio levantadas

1. Um cliente deve ter nome e telefone obrigatórios.
2. Um veículo deve ter placa, modelo e ano obrigatórios.
3. Um veículo só pode ser cadastrado se o cliente informado existir.
4. Um cliente pode ter mais de um veículo cadastrado.
5. Uma ordem de serviço só pode ser aberta se o veículo informado existir.
6. A descrição da ordem de serviço é obrigatória.
7. O valor do serviço não pode ser negativo.
8. O status da ordem de serviço é obrigatório.
9. O status da ordem de serviço deve ser `ABERTA` ou `CONCLUIDA`.
10. O sistema deve permitir consultar todo o histórico de ordens de serviço de um veículo específico.
11. As validações acima devem ser respeitadas tanto no cadastro quanto na atualização dos registros.

## SQL - CREATE TABLE

```sql
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);

CREATE TABLE veiculo (
    id SERIAL PRIMARY KEY,
    placa VARCHAR(20) NOT NULL,
    modelo VARCHAR(100) NOT NULL,
    ano INTEGER NOT NULL,
    id_cliente INTEGER NOT NULL,
    CONSTRAINT fk_veiculo_cliente
        FOREIGN KEY (id_cliente)
        REFERENCES cliente (id)
);

CREATE TABLE ordem_servico (
    id SERIAL PRIMARY KEY,
    id_veiculo INTEGER NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    valor NUMERIC(10, 2) NOT NULL CHECK (valor >= 0),
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_ordem_servico_veiculo
        FOREIGN KEY (id_veiculo)
        REFERENCES veiculo (id)
);
```

## Banco de dados utilizado

- SGBD: PostgreSQL
- Banco configurado no projeto: `oficina_mecanica`

## Fluxo simulado na Main

A classe `Main` executa diretamente a demonstração completa do cenário 2, sem `Scanner` e sem entrada manual:

1. cadastra um cliente;
2. cadastra um veículo vinculado a esse cliente;
3. abre uma ordem de serviço para o veículo;
4. lista os veículos do cliente;
5. lista o histórico de ordens de serviço do veículo;
6. exibe as listagens gerais das entidades.