package com.bibliotech.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Emprestimo {
    private final Usuario usuario;
    private final Livro livro;
    private boolean ativo;
    private final LocalDate dataEmprestimo = LocalDate.now();
    private final LocalDate dataDevolucao = LocalDate.now().plusDays(30);
    private LocalDate dataDevolvido;

    public Emprestimo(Usuario usuario, Livro livro) {
        this.usuario = usuario;
        this.livro = livro;
        this.ativo = true;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public boolean isAtrasado() {
        return ativo && LocalDate.now().isAfter(dataDevolucao);
    }

    public String getStatus() {
        if (!ativo) return "DEVOLVIDO";
        if (isAtrasado()) return "ATRASADO";
        return "EM DIA";
    }

    public long diasRestantes() {
        return ChronoUnit.DAYS.between(LocalDate.now(), dataDevolucao);
    }

    public void devolver() {
        this.ativo = false;
        this.dataDevolvido = LocalDate.now();
    }

    @Override
    public String toString() {
        return "\n-------------------------" +
                "\nLivro: " + livro.getTitulo() +
                "\nUsuario: " + usuario.getNome() +
                "\nEmprestado em: " + dataEmprestimo +
                "\nDevolucao prevista: " + dataDevolucao +
                "\nDias restantes: " + (ativo ? diasRestantes() : "-") +
                "\nAtrasado: " + (isAtrasado() ? "SIM" : "EM DIA") +
                "\nDevolvido em: " + (dataDevolvido != null ? dataDevolvido : "PENDENTE") +
                "\nStatus: " + getStatus() +
                "\n-------------------------";
    }
}
