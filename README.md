# 📝 ToDo List API – Spring Boot

Este projeto é uma API REST desenvolvida com **Java 17** e **Spring Boot**, permitindo o gerenciamento de tarefas com autenticação de usuários. A aplicação oferece endpoints para criação, listagem, atualização e exclusão de tarefas, garantindo que cada usuário acesse apenas suas próprias tarefas.

## 🚀 Funcionalidades

- Cadastro de usuários
- Autenticação via token no header
- Criação, listagem, atualização e exclusão de tarefas
- Associação de tarefas ao usuário autenticado
- Validações e tratamento de erros
- Middleware para autenticação
- Deploy via Docker

## 🛠️ Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database (configurável via `application.properties`)
- Maven
- Docker

## 📦 Como rodar o projeto localmente

### Pré-requisitos

- Java 17 ou superior
- Maven

### Passos

1. Clone o repositório:
 ```
   git clone https://github.com/maxzdosreis/todolist.git
   cd todolist
 ```
2. Execute o projeto:
 ```
   ./mvnw spring-boot:run
 ```
3. Acesse a aplicação em:
 ```
   http://localhost:8080
 ```

## 🐳 Rodando com Docker

### Passos

1. Compile a aplicação:
 ```
   ./mvnw clean package -DskipTests
 ```
2. Construa a imagem Docker:
 ```
   docker build -t todolist-app .
 ```
3. Rode o container:
 ```
   docker run -p 8080:8080 todolist-app
 ```

## 📌 Endpoints da API

### 👤 Usuários

- POST /users – Cadastro de novo usuário
- Obs: A autenticação é feita via token enviado no header Authorization (exemplo: Authorization: <seu-uuid>).

### ✅ Tarefas

- POST /tasks – Criar nova tarefa
- GET /tasks – Listar tarefas do usuário autenticado
- PUT /tasks/{id} – Atualizar tarefa existente
