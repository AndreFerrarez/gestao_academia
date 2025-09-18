# Gestão Acadêmica API

[![Status](https://img.shields.io/badge/status-concluído-green)]()

## Aplicacao Deploy disponivel em:
Para verificar os alunos ja cadastrados...
https://gestao-academia.onrender.com/api/alunos

> Um serviço de API RESTful construído com Spring Boot para gerenciar alunos, disciplinas, matrículas e notas. O sistema foi projetado para ser operado por um professor, que precisa estar autenticado para realizar qualquer operação.

## 🚀 Funcionalidades

* 🔐 **Autenticação de Professor:** Todas as rotas são protegidas e exigem autenticação.
* 👨‍🎓 **Gerenciamento de Alunos:**
    * Cadastrar alunos com Nome, CPF, e-mail, telefone e endereço.
    * Listar todos os alunos cadastrados.
* 📚 **Gerenciamento de Disciplinas:**
    * Cadastrar disciplinas com Nome e Código.
    * Listar todas as disciplinas cadastradas.
* ✍️ **Operações Acadêmicas:**
    * Matricular um aluno em uma ou mais disciplinas.
    * Atribuir notas aos alunos nas disciplinas matriculadas.
* 📊 **Relatórios:**
    * Listar alunos aprovados em uma disciplina (nota >= 7.0).
    * Listar alunos reprovados em uma disciplina (nota < 7.0).

## 🛠️ Tecnologias Utilizadas

* **Backend:**
    * [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
    * [Spring Boot](https://spring.io/projects/spring-boot)
    * [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
    * [Spring Security](https://spring.io/projects/spring-security)
    * [Hibernate Validator](https://hibernate.org/validator/)
* **Banco de Dados:**
    * [PostgreSQL](https://www.postgresql.org/) (Para produção/desenvolvimento)
    * [H2 Database](https://www.h2database.com/html/main.html) (Para testes unitários)
* **Build e Dependências:**
    * [Maven](https://maven.apache.org/)
    * [Lombok](https://projectlombok.org/)
* **Testes:**
    * [JUnit 5](https://junit.org/junit5/)
    * [Mockito](https://site.mockito.org/)
* **Ambiente de Execução:**
    * [Docker](https://www.docker.com/) & [Docker Compose](https://docs.docker.com/compose/)

## ⚙️ Pré-requisitos

Antes de começar, você vai precisar ter instalado em sua máquina:
* [JDK 17 ou superior](https://www.oracle.com/java/technologies/downloads/)
* [Maven](https://maven.apache.org/download.cgi)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/)

## ▶️ Como Executar o Projeto

Existem duas maneiras de executar a aplicação:

### Opção 1: Usando Docker (Recomendado)

Este método sobe a aplicação e o banco de dados PostgreSQL de forma automatizada.

1.  Clone o repositório:
    ```bash
    git clone https://github.com/AndreFerrarez/gestao_academia.git
    ```

2.  Navegue até a raiz do projeto:
    ```bash
    cd gestao-academica
    ```

3.  Verifique se o arquivo `application.properties` está configurado para o Docker:
    ```properties
    spring.datasource.url=jdbc:postgresql://db:5432/gestao_academica_db
    # ...demais propriedades
    ```

4.  Execute o Docker Compose:
    ```bash
    docker-compose up --build
    ```
A aplicação estará disponível em `http://localhost:8080`.

### Opção 2: Executando Localmente

Este método requer que você tenha o PostgreSQL instalado e rodando na sua máquina.

1.  Clone o repositório.
2.  Inicie seu servidor PostgreSQL e crie um banco de dados chamado `gestao_academica_db`.
3.  Configure o arquivo `src/main/resources/application.properties` com suas credenciais do PostgreSQL:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/gestao_academica_db
    spring.datasource.username=seu_usuario_postgres
    spring.datasource.password=sua_senha_postgres
    ```
4.  Execute a aplicação pelo seu IDE (IntelliJ) ou via Maven:
    ```bash
    mvn spring-boot:run
    ```

## 🔐 Autenticação

Para usar qualquer endpoint da API, é necessário fornecer autenticação via **Basic Auth**.

* **Username:** `professor`
* **Password:** `senha123`

## 📡 Endpoints da API

Aqui estão os endpoints disponíveis para teste via Postman ou similar.

| Funcionalidade | Método HTTP | Endpoint | Payload / Parâmetros |
| :--- | :--- | :--- | :--- |
| **Listar Alunos** | `GET` | `/api/alunos` | Nenhum |
| **Cadastrar Aluno** | `POST` | `/api/alunos` | `{"nome": "...", "cpf": "...", ...}` |
| **Listar Disciplinas**| `GET` | `/api/disciplinas` | Nenhum |
| **Cadastrar Disciplina**| `POST` | `/api/disciplinas`| `{"nome": "...", "codigo": "..."}` |
| **Matricular Aluno** | `POST` | `/api/matriculas`| Parâmetros na URL: `?alunoId=1&disciplinaId=1` |
| **Atribuir Nota** | `PUT` | `/api/matriculas/{id}/nota` | Parâmetro na URL: `?nota=8.5` |
| **Listar Aprovados** | `GET` | `/api/matriculas/disciplina/{id}/aprovados` | Nenhum |
| **Listar Reprovados** | `GET` | `/api/matriculas/disciplina/{id}/reprovados`| Nenhum |

## 🧪 Testes

Para rodar os testes unitários e verificar a cobertura, execute o seguinte comando na raiz do projeto:

```bash
mvn clean test
```

---
