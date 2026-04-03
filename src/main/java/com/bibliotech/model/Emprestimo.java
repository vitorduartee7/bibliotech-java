package com.bibliotech.model;

import java.time.LocalDate;

public class Emprestimo {
    private final Usuario usuario;
    private final Livro livro;
    private final LocalDate dataEmprestimo = LocalDate.now();
    private final LocalDate dataDevolucao  = LocalDate.now().plusDays(30);

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
    }

    public Livro getLivro() {
        return livro;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    @Override
    public String toString() {
        return "Emprestimo{" +
                "usuario=" + usuario +
                ", livro=" + livro +
                ", dataEmprestimo=" + dataEmprestimo +
                ", dataDevolucao=" + dataDevolucao +
                '}';
    }
}
