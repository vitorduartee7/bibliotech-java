package com.bibliotech.service;

import com.bibliotech.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class LivroService {
    private final List<Livro> livros = new ArrayList<>();
    private int proximoIdLivro = 1;

    public List<Livro> getLivros() {
        return livros;
    }

    public boolean cadastrarLivro(String titulo, String autor) {
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(titulo)
                    && l.getAutor().equalsIgnoreCase(autor)) return false;
        }

        Livro novoLivro = new Livro(proximoIdLivro++, titulo, autor);
        livros.add(novoLivro);

        return true;
    }

    public Livro buscarLivro(int idLivro) {
        for (Livro l : livros) {
            if (l.getId() == idLivro) {
                return l;
            }
        }
        return null;
    }

    public void listarLivros() {
        System.out.println("\n=== LIVROS BIBLIOTECH ===");

        System.out.println("ID | Titulo | Autor | Status");
        System.out.println("----------------------------");

        for (Livro livro : livros) {
            System.out.println(livro);
        }

        System.out.println("=============");
    }

    public void listarLivrosPorStatus(boolean disponiveis) {
        String titulo = disponiveis
                ? "=== LIVROS DISPONÍVEIS BIBLIOTECH ==="
                : "=== LIVROS INDISPONÍVEIS BIBLIOTECH ===";

        String mensagemVazia = disponiveis
                ? "Nenhum livro disponível"
                : "Nenhum livro alugado";

        System.out.println(titulo);

        boolean encontrou = false;

        for (Livro livro : livros) {
            if (livro.isDisponivel() == disponiveis) {
                System.out.println(livro);
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println(mensagemVazia);
        }

        System.out.println("=======================================");
    }

    public void listarLivrosDisponiveis() {
        listarLivrosPorStatus(true);
    }

    public void listarLivrosIndisponiveis() {
        listarLivrosPorStatus(false);
    }
}
