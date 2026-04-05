# 📚 Bibliotech – Sistema de Biblioteca em Java (CLI)

O **Bibliotech** é um sistema de gerenciamento de biblioteca via terminal, desenvolvido em **Java**, com foco em arquitetura limpa, regras de negócio realistas e organização de código profissional.

Este projeto simula o funcionamento de um sistema real, indo além de um CRUD simples, incluindo controle de usuários, empréstimos, validações e automações.

---

## 🎯 Objetivo do Projeto

Demonstrar capacidade de desenvolver um sistema completo com:

* Regras de negócio reais
* Organização profissional de código
* Pensamento escalável
* Experiência de usuário mesmo em CLI

---

## 🚀 Funcionalidades

### 👤 Usuários

* Cadastro com validação (nome, email e senha)
* Login e logout
* Controle de sessão (usuário logado)

### 📖 Livros

* Cadastro de livros
* Listagem geral
* Listagem por status:

  * Disponíveis
  * Alugados

### 🔄 Empréstimos

* Empréstimo de livros
* Devolução de livros
* Histórico completo por usuário
* Controle de status (ativo/devolvido)

---

## ⚙️ Regras de Negócio

* 📌 Limite de livros por usuário (ex: 3 simultâneos)
* 🚫 Bloqueio automático em caso de atraso
* ⛔ Banimento por atraso prolongado
* 🔓 Desbloqueio automático após devolução
* 📅 Prazo automático de devolução (30 dias)
* ⏳ Cálculo de dias restantes
* ⚠️ Identificação de atraso automático

---

## 🔔 Sistema de Notificações

O sistema exibe avisos inteligentes no menu:

* ⏳ “Faltam X dias para vencer”
* ⚠️ “Livro atrasado”
* 🚫 Usuário bloqueado
* ⛔ Usuário banido

Os avisos só aparecem quando necessário, evitando poluição visual.

---

## 🏗 Arquitetura

Projeto organizado em camadas:

```
com.bibliotech
├── model      -> Entidades (Livro, Usuario, Emprestimo)
├── service    -> Regras de negócio
├── util       -> Menu, validações e entrada de dados
├── enums      -> Estados e resultados
└── main       -> Execução do sistema
```

### ✔️ Boas práticas aplicadas:

* Programação Orientada a Objetos (POO)
* Encapsulamento e imutabilidade
* Separação de responsabilidades
* Service Layer
* Uso de Enums
* Código modular e escalável

---

## 💡 Tecnologias

* Java
* CLI (Console)
* Estruturas de dados (List)
* API de datas (`LocalDate`)

---

## ▶️ Como executar

1. Clone o repositório:

```bash
git clone https://github.com/seu-usuario/bibliotech.git
```

2. Compile o projeto:

```bash
javac Main.java
```

3. Execute:

```bash
java Main
```

---

## 📬 Contato

Sinta-se à vontade para contribuir ou dar feedback!
www.linkedin.com/in/vitorduartee

---

## 🏷 Tags

`Java` `POO` `Backend` `CLI` `CleanCode` `Portfolio` `Sistema`
