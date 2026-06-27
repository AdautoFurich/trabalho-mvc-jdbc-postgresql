# 🚗 Sistema de Gerenciamento de Oficina Mecânica

<p align="center">
Sistema desenvolvido em <strong>Java</strong> utilizando o padrão arquitetural <strong>MVC (Model-View-Controller)</strong>, persistência de dados com <strong>JDBC</strong> e banco de dados <strong>PostgreSQL</strong>.
</p>

<p align="center">

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge\&logo=openjdk)
![Maven](https://img.shields.io/badge/Maven-3.9-blue?style=for-the-badge\&logo=apachemaven)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-336791?style=for-the-badge\&logo=postgresql)
![JDBC](https://img.shields.io/badge/JDBC-Persistence-success?style=for-the-badge)
![MVC](https://img.shields.io/badge/Architecture-MVC-purple?style=for-the-badge)

</p>

---

# 📖 Sobre o Projeto

O **Sistema de Gerenciamento de Oficina Mecânica** foi desenvolvido como atividade acadêmica da disciplina de Programação Orientada a Objetos, com o objetivo de aplicar conceitos fundamentais de desenvolvimento de software utilizando Java.

A aplicação permite gerenciar clientes, veículos e ordens de serviço de uma oficina mecânica através de uma interface em modo console, realizando operações completas de **CRUD (Create, Read, Update e Delete)** com persistência de dados em **PostgreSQL** utilizando **JDBC**.

Durante o desenvolvimento foram aplicados conceitos de Programação Orientada a Objetos, arquitetura MVC, padrão DAO, relacionamento entre entidades e gerenciamento de dependências com Maven.

---

# 🎯 Objetivos

* Aplicar os conceitos de Programação Orientada a Objetos.
* Desenvolver uma aplicação utilizando a arquitetura MVC.
* Implementar a camada de persistência utilizando JDBC.
* Utilizar PostgreSQL como banco de dados relacional.
* Desenvolver operações completas de CRUD.
* Aplicar boas práticas de organização e separação de responsabilidades.

---

# ✨ Funcionalidades

O sistema permite:

* ✅ Cadastro de Clientes
* ✅ Cadastro de Veículos
* ✅ Cadastro de Ordens de Serviço
* ✅ Listagem de Clientes
* ✅ Listagem de Veículos
* ✅ Listagem de Ordens de Serviço
* ✅ Atualização de registros
* ✅ Exclusão de registros
* ✅ Relacionamento entre Clientes e Veículos
* ✅ Relacionamento entre Veículos e Ordens de Serviço
* ✅ Persistência em PostgreSQL
* ✅ Organização utilizando arquitetura MVC

---

# 🏗 Arquitetura

O projeto foi desenvolvido seguindo o padrão **MVC (Model-View-Controller)**.

```text
                Usuário
                   │
                   ▼
             Main (View)
                   │
                   ▼
              Controller
                   │
                   ▼
                 DAO
                   │
                   ▼
             PostgreSQL
```

## Organização das Camadas

### Model

Representa as entidades da aplicação.

* Cliente
* Veículo
* Ordem de Serviço

### Controller

Responsável pelo fluxo da aplicação e pelas regras de negócio.

### DAO

Responsável pelas operações de persistência utilizando JDBC.

### View

Representada pela interface em modo console responsável pela interação com o usuário.

---

# 🛠 Tecnologias Utilizadas

| Tecnologia    | Finalidade                    |
| ------------- | ----------------------------- |
| Java 21       | Linguagem principal           |
| Maven         | Gerenciamento de dependências |
| PostgreSQL    | Banco de dados relacional     |
| JDBC          | Persistência de dados         |
| MVC           | Arquitetura da aplicação      |
| DAO           | Camada de acesso aos dados    |
| IntelliJ IDEA | Ambiente de desenvolvimento   |

---

# 📁 Estrutura do Projeto

```text
src
├── controller/
├── dao/
├── model/
├── util/
├── view/
└── Main.java
```

---

# 🗄 Banco de Dados

O sistema utiliza o **PostgreSQL** como Sistema Gerenciador de Banco de Dados (SGBD), realizando a comunicação com a aplicação através da API JDBC.

O banco foi estruturado para armazenar clientes, veículos e ordens de serviço, garantindo integridade dos dados por meio de chaves primárias, chaves estrangeiras e restrições de validação.

---

# 📝 Script de Criação do Banco de Dados

### Criar o banco

```sql
CREATE DATABASE oficina_mecanica;
```

Após criar o banco, conecte-se a ele e execute as instruções abaixo.

---

### Tabela `cliente`

```sql
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);
```

---

### Tabela `veiculo`

```sql
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
```

---

### Tabela `ordem_servico`

```sql
CREATE TABLE ordem_servico (
    id SERIAL PRIMARY KEY,
    id_veiculo INTEGER NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    valor NUMERIC(10,2) NOT NULL CHECK (valor >= 0),
    status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_ordem_servico_veiculo
        FOREIGN KEY (id_veiculo)
        REFERENCES veiculo (id)
);
```

---

## 🔗 Modelo Relacional

```text
Cliente (1)
     │
     └──────────< Veículo (N)
                          │
                          └──────────< Ordem de Serviço (N)
```

### Relacionamentos

* Um **Cliente** pode possuir um ou vários **Veículos**.
* Cada **Veículo** pertence a apenas um **Cliente**.
* Um **Veículo** pode possuir uma ou várias **Ordens de Serviço**.
* Cada **Ordem de Serviço** está vinculada a apenas um **Veículo**.

---

# 🚀 Como Executar

## 1. Clonar o repositório

```bash
git clone https://github.com/AdautoFurich/trabalho-mvc-jdbc-postgresql.git
```

## 2. Abrir o projeto

Importe o projeto em uma IDE compatível com Maven, como:

* IntelliJ IDEA
* Eclipse
* VS Code

## 3. Configurar o PostgreSQL

Crie o banco de dados PostgreSQL e configure a conexão JDBC informando:

* URL do banco
* Usuário
* Senha

## 4. Executar o Script SQL

Execute os comandos SQL apresentados neste README para criar todas as tabelas necessárias ao funcionamento da aplicação.

## 5. Executar a aplicação

Execute a classe:

```text
Main.java
```

---

# 📚 Conceitos Aplicados

Durante o desenvolvimento deste projeto foram aplicados os seguintes conceitos:

* Programação Orientada a Objetos (POO)
* Encapsulamento
* Associação entre Classes
* CRUD
* JDBC
* PostgreSQL
* DAO (Data Access Object)
* MVC (Model-View-Controller)
* Maven
* Persistência de Dados
* Separação de Responsabilidades
* Relacionamento entre Entidades

---

# ⭐ Considerações

Este projeto foi desenvolvido com fins acadêmicos para consolidar os conhecimentos adquiridos em Programação Orientada a Objetos, arquitetura MVC e persistência de dados utilizando JDBC e PostgreSQL.

Além de atender aos requisitos propostos pela disciplina, o sistema demonstra a aplicação prática de conceitos utilizados no desenvolvimento de sistemas corporativos, servindo como base para futuros projetos utilizando frameworks como Spring Boot e APIs REST.
