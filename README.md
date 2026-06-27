# 🎓 Sistema de Escola de Cursos Livres.

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

O **Sistema de Escola de Cursos Livres** foi desenvolvido como atividade acadêmica da disciplina de Programação Orientada a Objetos, com o objetivo de aplicar conceitos fundamentais de desenvolvimento de software utilizando Java.

A aplicação realiza o gerenciamento de **alunos**, **cursos** e **matrículas**, utilizando arquitetura MVC, persistência de dados com JDBC e banco de dados PostgreSQL.

Além das operações de CRUD, o projeto implementa validações de regras de negócio por meio da camada **Service**, tornando a aplicação mais organizada, reutilizável e próxima de um sistema corporativo.

---

# 🎯 Objetivos

* Aplicar os conceitos de Programação Orientada a Objetos.
* Desenvolver uma aplicação utilizando arquitetura MVC.
* Implementar persistência utilizando JDBC.
* Utilizar PostgreSQL como banco de dados relacional.
* Aplicar regras de negócio utilizando a camada Service.
* Implementar relacionamento muitos-para-muitos utilizando tabela intermediária.
* Desenvolver operações completas de CRUD.

---

# ✨ Funcionalidades

O sistema permite:

* ✅ Cadastro de Alunos
* ✅ Cadastro de Cursos
* ✅ Registro de Matrículas
* ✅ Listagem de Alunos
* ✅ Listagem de Cursos
* ✅ Consulta de alunos matriculados em um curso
* ✅ Consulta dos cursos em que um aluno está matriculado
* ✅ Atualização de registros
* ✅ Exclusão de registros
* ✅ Persistência em PostgreSQL
* ✅ Organização utilizando arquitetura MVC
* ✅ Validação das regras de negócio através da camada Service

---

# 🏗 Arquitetura

O projeto segue o padrão arquitetural **MVC (Model-View-Controller)**.

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
              Service
                   │
                   ▼
             Repository
                   │
                   ▼
             PostgreSQL
```

## Organização das Camadas

### Model

Representa as entidades da aplicação.

* Aluno
* Curso
* Matrícula

### Controller

Responsável pelo fluxo da aplicação.

### Service

Responsável pelas validações e regras de negócio.

### Repository

Responsável pela persistência utilizando JDBC.

### Util

Responsável pela configuração da conexão com o banco de dados.

### View

Representada pela classe `Main`, responsável pela execução do fluxo da aplicação.

---

# 🛠 Tecnologias Utilizadas

| Tecnologia    | Finalidade                    |
| ------------- | ----------------------------- |
| Java 21       | Linguagem principal           |
| Maven         | Gerenciamento de dependências |
| PostgreSQL    | Banco de dados relacional     |
| JDBC          | Persistência de dados         |
| MVC           | Arquitetura                   |
| Repository    | Persistência                  |
| Service       | Regras de negócio             |
| IntelliJ IDEA | Ambiente de desenvolvimento   |

---

# 📁 Estrutura do Projeto

```text
src
├── controller/
├── model/
├── repository/
├── service/
├── util/
└── Main.java
```

---

# 🗄 Banco de Dados

O sistema utiliza o **PostgreSQL** como Sistema Gerenciador de Banco de Dados (SGBD).

A comunicação entre a aplicação e o banco é realizada através da API JDBC.

Banco utilizado:

```text
escola_cursos_livres
```

---

# 📝 Script de Criação do Banco de Dados

### Criar o banco

```sql
CREATE DATABASE escola_cursos_livres;
```

Após criar o banco, conecte-se a ele e execute as instruções abaixo.

### Tabela `aluno`

```sql
CREATE TABLE aluno (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(120) NOT NULL,
    telefone VARCHAR(20) NOT NULL
);
```

### Tabela `curso`

```sql
CREATE TABLE curso (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(200) NOT NULL,
    carga_horaria INTEGER NOT NULL,
    vagas_totais INTEGER NOT NULL,
    vagas_disponiveis INTEGER NOT NULL
);
```

### Tabela `matricula`

```sql
CREATE TABLE matricula (
    id SERIAL PRIMARY KEY,
    id_aluno INTEGER NOT NULL,
    id_curso INTEGER NOT NULL,
    data_matricula DATE NOT NULL,
    valor NUMERIC(10,2) NOT NULL CHECK (valor >= 0),

    CONSTRAINT fk_matricula_aluno
        FOREIGN KEY (id_aluno)
        REFERENCES aluno(id),

    CONSTRAINT fk_matricula_curso
        FOREIGN KEY (id_curso)
        REFERENCES curso(id),

    CONSTRAINT uq_matricula_aluno_curso
        UNIQUE(id_aluno,id_curso)
);
```

---

# 🔗 Modelo Relacional

```text
Aluno (N)
      │
      ├───────────────┐
      │               │
      ▼               ▼
        Matrícula
      ▲               ▲
      │               │
      └───────────────┘
Curso (N)
```

### Relacionamentos

* Um aluno pode estar matriculado em vários cursos.
* Um curso pode possuir vários alunos matriculados.
* A tabela **Matrícula** realiza o relacionamento entre Aluno e Curso.

---

# 📋 Regras de Negócio

* O aluno deve possuir nome, e-mail e telefone obrigatórios.
* O curso deve possuir nome, descrição, carga horária e número de vagas.
* O número de vagas disponíveis não pode ser negativo.
* O número de vagas disponíveis não pode ser maior que o total de vagas.
* Não é permitido matricular aluno inexistente.
* Não é permitido matricular em curso inexistente.
* Não é permitido matrícula duplicada.
* Não é permitido matrícula em curso sem vagas disponíveis.
* O valor da matrícula não pode ser negativo.
* O sistema permite consultar alunos por curso.
* O sistema permite consultar cursos por aluno.

---

# 🚀 Como Executar

## 1. Clonar o repositório

```bash
git clone https://github.com/AdautoFurich/trabalho-mvc-jdbc-postgresql.git
```

## 2. Abrir o projeto

Importe o projeto utilizando uma IDE compatível com Maven.

Exemplos:

* IntelliJ IDEA
* Eclipse
* VS Code

## 3. Configurar o PostgreSQL

Configure a conexão JDBC informando:

* URL
* Usuário
* Senha

## 4. Executar o Script SQL

Execute as instruções SQL apresentadas neste README.

## 5. Executar a aplicação

Execute a classe:

```text
Main.java
```

---

# 📚 Conceitos Aplicados

Durante o desenvolvimento deste projeto foram aplicados:

* Programação Orientada a Objetos (POO)
* Encapsulamento
* Associação entre Classes
* Relacionamento Muitos-para-Muitos (N:N)
* CRUD
* JDBC
* PostgreSQL
* Repository Pattern
* Service Layer
* MVC
* Maven
* Persistência de Dados
* Regras de Negócio

---
