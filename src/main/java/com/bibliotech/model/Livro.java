package com.bibliotech.model;

public class Livro {
    private final int idLivro;
    private final String titulo;
    private final String autor;
    private boolean disponivel;

    public Livro(int id, String titulo, String autor) {
        this.idLivro = id;
        this.titulo = titulo;
        this.autor = autor;
        this.disponivel = true;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getId() {
        return idLivro;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "ID: " + idLivro + " | Titulo: " + titulo + " | Autor: " + autor + " | Disponivel: " + (disponivel ? "Sim" : "Nao");
    }
}
