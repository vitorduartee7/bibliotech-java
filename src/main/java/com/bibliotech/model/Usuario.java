package com.bibliotech.model;

public class Usuario {
    private final int idUsuario;
    private final String nome;
    private final String email;
    private final String senha;

    public Usuario(int id, String nome, String email, String senha) {
        this.idUsuario = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public String toString() {
        return "ID: " + idUsuario + " | Nome: " + nome;
    }
}
