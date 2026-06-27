# 🐾 Sistema de Clínica Veterinária

<p align="center">
Sistema desenvolvido em <strong>Java</strong> utilizando o padrão <strong>MVC (Model-View-Controller)</strong>, persistência de dados com <strong>JDBC</strong> e banco de dados <strong>PostgreSQL</strong>.
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

O Sistema de Clínica Veterinária foi desenvolvido como atividade acadêmica com o objetivo de aplicar os conceitos de Programação Orientada a Objetos, arquitetura MVC, JDBC e PostgreSQL.

A aplicação permite gerenciar tutores, animais e consultas veterinárias por meio de uma interface em modo console, realizando operações completas de CRUD com persistência em banco de dados relacional.

---

# 🎯 Objetivos

* Aplicar os conceitos de Programação Orientada a Objetos.
* Utilizar a arquitetura MVC.
* Implementar a camada de persistência com JDBC.
* Utilizar PostgreSQL como banco de dados.
* Desenvolver operações CRUD completas.
* Aplicar boas práticas de organização do código.

---

# ✨ Funcionalidades

* ✅ Cadastro de Tutores
* ✅ Cadastro de Animais
* ✅ Registro de Consultas
* ✅ Listagem de Tutores
* ✅ Listagem de Animais
* ✅ Listagem de Consultas
* ✅ Atualização de registros
* ✅ Exclusão de registros
* ✅ Persistência de dados utilizando PostgreSQL

---

# 🏗 Arquitetura

O projeto segue o padrão **MVC (Model-View-Controller)**.

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

### Camadas

**Model**

Representa as entidades do sistema.

**Controller**

Controla o fluxo da aplicação.

**DAO**

Responsável pelas operações de acesso ao banco utilizando JDBC.

**View**

Interface em modo console responsável pela interação com o usuário.

---

# 🛠 Tecnologias Utilizadas

| Tecnologia    | Utilização                    |
| ------------- | ----------------------------- |
| Java 21       | Linguagem principal           |
| Maven         | Gerenciamento de dependências |
| PostgreSQL    | Banco de dados                |
| JDBC          | Persistência                  |
| MVC           | Arquitetura                   |
| DAO           | Camada de acesso aos dados    |
| IntelliJ IDEA | IDE                           |

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

O sistema utiliza o PostgreSQL para armazenamento das informações.

A comunicação entre a aplicação e o banco é realizada utilizando a API JDBC.

---

# 📝 Script de Criação do Banco de Dados

### Criar o banco

```sql
CREATE DATABASE clinica_veterinaria;
```

Após criar o banco, conecte-se a ele e execute os comandos abaixo.

### Tabela Tutor

```sql
CREATE TABLE tutor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20),
    email VARCHAR(100)
);
```

### Tabela Animal

```sql
CREATE TABLE animal (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    especie VARCHAR(50),
    raca VARCHAR(50),
    idade INT,
    tutor_id INT NOT NULL,
    CONSTRAINT fk_animal_tutor
        FOREIGN KEY (tutor_id)
        REFERENCES tutor(id)
);
```

### Tabela Consulta

```sql
CREATE TABLE consulta (
    id SERIAL PRIMARY KEY,
    data_consulta DATE NOT NULL,
    descricao TEXT,
    animal_id INT NOT NULL,
    CONSTRAINT fk_consulta_animal
        FOREIGN KEY (animal_id)
        REFERENCES animal(id)
);
```

### Modelo Relacional

```text
Tutor (1)
   │
   └────────< Animal (N)
                    │
                    └────────< Consulta (N)
```

### Relacionamentos

* Um tutor pode possuir vários animais.
* Cada animal pertence a apenas um tutor.
* Um animal pode possuir várias consultas.
* Cada consulta pertence a apenas um animal.

---

# 🚀 Como Executar

### 1. Clonar o repositório

```bash
git clone https://github.com/AdautoFurich/trabalho-mvc-jdbc-postgresql.git
```

### 2. Abrir o projeto

Abra o projeto em uma IDE compatível com Maven.

### 3. Configurar o banco de dados

* Criar o banco PostgreSQL.
* Executar o script SQL.
* Configurar URL, usuário e senha da conexão JDBC.

### 4. Executar a aplicação

Execute a classe `Main.java`.

---

# 📚 Conceitos Aplicados

* Programação Orientada a Objetos
* Encapsulamento
* Associação entre Classes
* CRUD
* JDBC
* PostgreSQL
* DAO
* MVC
* Maven
* Persistência de Dados

---

# ⭐ Considerações

Este projeto foi desenvolvido para consolidar conhecimentos em desenvolvimento Java, arquitetura MVC e persistência de dados com JDBC e PostgreSQL, aplicando boas práticas de organização e separação de responsabilidades.
