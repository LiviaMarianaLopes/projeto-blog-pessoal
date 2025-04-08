# 📘 Blog Pessoal

Este é um projeto backend desenvolvido com **Spring Boot**, que simula um blog pessoal com funcionalidades de postagens, temas e gerenciamento de usuários. O projeto também utiliza **JWT para autenticação**, **BCrypt para criptografia de senhas** e está integrado com o **SonarQube** para análise de qualidade de código.

---

## 📌 Funcionalidades

- Cadastro, atualização e remoção de usuários
- Login com geração de Token JWT
- Criação, listagem, atualização e remoção de postagens
- Filtros por autor e tema nas postagens
- Cadastro, listagem, atualização e remoção de temas
- Segurança com Spring Security
- Análise de código com SonarQube

---

## 🛠️ Tecnologias Utilizadas

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- Gradle
- SonarQube
- Postman (para testes)

---

## 🧩 Arquitetura do Projeto

- `model`: Entidades (Usuario, Postagem, Tema)
- `repository`: Interfaces JPA
- `service`: Regras de negócio
- `controller`: Endpoints REST
- `security`: Configuração de segurança e JWT
- `dto`: Transferência de dados
- `exception`: Classe para exceções

---

## 🔐 Segurança

- Login com usuario e senha
- Senhas criptografadas com **BCrypt**
- Proteção de rotas com **JWT**
