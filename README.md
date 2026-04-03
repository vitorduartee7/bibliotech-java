# 📚 Bibliotech CLI

Sistema de gerenciamento de biblioteca via terminal desenvolvido em Java utilizando Maven.

## 🎯 Objetivo

Projeto desenvolvido para prática de Java, lógica de programação e construção de aplicações estruturadas para portfólio.

## 🚀 Funcionalidades

* 🔐 Cadastro e login de usuários
* 📚 Cadastro de livros
* 📖 Listagem de livros
* 🔄 Empréstimo de livros
* 🔁 Devolução de livros
* 📊 Controle de disponibilidade
* 🧠 Validações de dados (nome, email e senha)

## 🧠 Conceitos aplicados

* Encapsulamento
* Separação de responsabilidades
* Clean Code
* Validação de dados
* Fluxo de autenticação
* Regras de negócio

## 🧱 Tecnologias utilizadas

* Java 21
* Maven
* Programação Orientada a Objetos (POO)

## 📁 Estrutura do projeto

```
com.bibliotech
├── main        → Classe principal
├── model       → Entidades (Usuario, Livro, Emprestimo)
├── service     → Regras de negócio
├── util        → Utilitários (menu, validações)
└── enums       → Estados e resultados
```

## ▶️ Como executar

1. Clone o repositório:

```
git clone https://github.com/seu-usuario/bibliotech.git
```

2. Acesse a pasta:

```
cd bibliotech
```

3. Compile o projeto:

```
mvn clean install
```

4. Execute:

```
mvn exec:java
```
---

💻 Desenvolvido por Vitor Duarte
