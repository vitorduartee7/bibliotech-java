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

    public EmprestimoService(AuthService authService, LivroService livroService) {
        this.livroService = livroService;
        this.authService = authService;
    }

    public ResultadoEmprestimo emprestarLivro(int idLivro) {
        if (!authService.isUsuarioLogado())
            return ResultadoEmprestimo.USUARIO_NAO_LOGADO;

        Livro livro = livroService.buscarLivro(idLivro);

        if (livro == null)
            return ResultadoEmprestimo.LIVRO_NAO_ENCONTRADO;

        if (!livro.isDisponivel())
            return ResultadoEmprestimo.LIVRO_INDISPONIVEL;

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
            if (e.getLivro().getId() == idLivro) {
                encontrado = e;
                break;
            }
        }

        if (encontrado == null)
            return ResultadoDevolucao.EMPRESTIMO_NAO_ENCONTRADO;

        emprestimos.remove(encontrado);
        livro.setDisponivel(true);

        return ResultadoDevolucao.SUCESSO;
    }
}
