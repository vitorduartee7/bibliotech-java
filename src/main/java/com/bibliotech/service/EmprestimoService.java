package com.bibliotech.service;

import com.bibliotech.enums.ResultadoDevolucao;
import com.bibliotech.enums.ResultadoEmprestimo;
import com.bibliotech.model.Emprestimo;
import com.bibliotech.model.Livro;

import java.util.ArrayList;
import java.util.List;

public class EmprestimoService {
    private final LivroService livroService;
    private final AuthService authService;
    private final List<Emprestimo> emprestimos = new ArrayList<>();
    int LIMITE_LIVROS = 3;

    public EmprestimoService(AuthService authService, LivroService livroService) {
        this.livroService = livroService;
        this.authService = authService;
    }

    public int getLimiteLivrosAlugados() {
        return LIMITE_LIVROS;
    }

    public void getEmprestimosUsuario() {
        System.out.println("\n=== BIBLIOTECH HISTORICO ALUGADOS ===");

        boolean encontrou = false;

        for (Emprestimo e : emprestimos){
            if (e.getUsuario().equals(authService.getUsuarioLogado())) {
                encontrou = true;
                System.out.println(e);
            }
        }

        if (!encontrou) {
            System.out.println("============================");
            System.out.println("Nenhum emprestimo encontrado");
            System.out.println("============================");
        }

        System.out.println("==========================================");

    }

    public void getEmprestimosAtivos() {
        System.out.println("\n=== BIBLIOTECH LIVROS ALUGADOS ===");

        boolean encontrou = false;

        for (Emprestimo e : emprestimos) {
            if (e.isAtivo() &&
                    e.getUsuario().equals(authService.getUsuarioLogado())) {
                encontrou = true;
                System.out.println(e);
            }
        }

        if (!encontrou) {
            System.out.println("============================");
            System.out.println("Nenhum emprestimo encontrado");
            System.out.println("============================");
        }

        System.out.println("======================================");
    }

    public void getEmprestimosDevolvidos() {
        System.out.println("\n=== BIBLIOTECH LIVROS DEVOLVIDOS ===");

        boolean encontrou = false;

        for (Emprestimo e : emprestimos) {
            if (!e.isAtivo() &&
                    e.getUsuario().equals(authService.getUsuarioLogado())) {
                encontrou = true;
                System.out.println(e);
            }
        }

        if (!encontrou) {
            System.out.println("==============================");
            System.out.println("Nenhum emprestimo encontrado");
            System.out.println("==============================");
        }

        System.out.println("========================================");
    }

    public int getLivrosAlugados() {
        int total = 0;

        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().equals(authService.getUsuarioLogado())
                    && e.isAtivo()) {
                total++;
            }
        }

        return total;
    }

    public ResultadoEmprestimo emprestarLivro(int idLivro) {
        if (!authService.isUsuarioLogado())
            return ResultadoEmprestimo.USUARIO_NAO_LOGADO;

        Livro livro = livroService.buscarLivro(idLivro);

        if (livro == null)
            return ResultadoEmprestimo.LIVRO_NAO_ENCONTRADO;

        if (!livro.isDisponivel())
            return ResultadoEmprestimo.LIVRO_INDISPONIVEL;

        if (getLivrosAlugados() >= LIMITE_LIVROS)
            return ResultadoEmprestimo.LIMITE_ATINGIDO;

        Emprestimo emprestimo = new Emprestimo(authService.getUsuarioLogado(),livro);

        emprestimos.add(emprestimo);
        livro.setDisponivel(false);

        return ResultadoEmprestimo.SUCESSO;
    }

    public ResultadoDevolucao devolverLivro(int idLivro) {
        if (!authService.isUsuarioLogado())
            return ResultadoDevolucao.USUARIO_NAO_LOGADO;

        Livro livro = livroService.buscarLivro(idLivro);

        if (livro == null)
            return ResultadoDevolucao.LIVRO_NAO_ENCONTRADO;

        Emprestimo encontrado = null;

        for (Emprestimo e : emprestimos) {
            if (e.getLivro().getId() == idLivro
                    && e.isAtivo()
                    && e.getUsuario().equals(authService.getUsuarioLogado())) {
                encontrado = e;
                break;
            }
        }

        if (encontrado == null)
            return ResultadoDevolucao.EMPRESTIMO_NAO_ENCONTRADO;

        encontrado.devolver();
        livro.setDisponivel(true);

        return ResultadoDevolucao.SUCESSO;
    }
}
