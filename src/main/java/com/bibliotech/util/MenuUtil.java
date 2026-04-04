package com.bibliotech.util;

import com.bibliotech.service.AuthService;
import com.bibliotech.service.LivroService;
import com.bibliotech.service.EmprestimoService;

import com.bibliotech.enums.ResultadoEmprestimo;
import com.bibliotech.enums.ResultadoDevolucao;

import java.time.LocalDate;
import java.util.Scanner;

public class MenuUtil {
    private static final Scanner input = new Scanner(System.in);
    private static final AuthService authService = new AuthService();
    private static final LivroService livroService = new LivroService();
    private static final EmprestimoService emprestimoService = new EmprestimoService(authService, livroService);

    public static void iniciar() {
        while (true) {
            if (!authService.isUsuarioLogado()) mostrarMenuInicial();
            else mostrarMenuPrincipal();
        }
    }

    public static void mostrarMenuInicial() {
        System.out.println("\n=== BIBLIOTECH ===");
        System.out.println("[1] Fazer Login");
        System.out.println("[2] Criar Conta");
        System.out.println("[0] Sair");
        int opcao = lerInt("Escolha: ");

        switch (opcao) {
            case 1: mostrarMenuFazerLogin(); break;
            case 2: mostrarMenuCriarConta(); break;
            case 0:
                System.out.println("=====================");
                System.out.println("Encerrando sistema...");
                System.out.println("=====================");
                System.exit(0);
                break;
            default:
                System.out.println("===============");
                System.out.println("Opcao invalida!");
                System.out.println("===============");
                break;
        }
    }

    public static void mostrarMenuFazerLogin() {
        System.out.println("\n=== BIBLIOTECH LOGIN ===");
        String email = lerString("Email: ");
        String senha = lerString("Senha: ");

        if (authService.fazerLogin(email, senha)) {
            String nome = authService.getUsuarioLogado().getNome();
            String primeiroNome = nome.split(" ")[0];

            System.out.println("===========================");
            System.out.println("Login efetuado com sucesso!");
            System.out.println("Bem-vindo, " + primeiroNome);
            System.out.println("===========================");
        } else {
            System.out.println("==========================");
            System.out.println("Email ou senha incorretos!");
            System.out.println("==========================");
        }
    }

    public static void mostrarMenuCriarConta() {
        System.out.println("\n=== BIBLIOTECH CRIAR CONTA ===");
        String nome = lerString("Nome: ");
        String email = lerString("Email: ");
        String senha = lerString("Senha: ");
        String senha2 = lerString("Digite sua Senha novamente: ");

        if(!ValidadorUtil.validarNome(nome)) {
            System.out.println("==============");
            System.out.println("Nome inválido!");
            System.out.println("==============");
            return;
        }

        if (!ValidadorUtil.validarEmail(email)) {
            System.out.println("================");
            System.out.println("E-mail invalido!");
            System.out.println("================");
            return;
        }

        if (!ValidadorUtil.validarSenha(senha)) {
            System.out.println("===============");
            System.out.println("""
                    A senha deve ter:
                            - 8+ caracteres
                            - 1 MAIÚSCULA (A-Z)
                            - 1 número (0-9)
                            - 1 especial (@#$%^&+=!?-)
                            - 1 minúscula (a-z)""");
            System.out.println("===============");
            return;
        }

        if (!senha2.equals(senha)) {
            System.out.println("===========================");
            System.out.println("As senhas devem ser iguais!");
            System.out.println("===========================");
        }

        if (authService.cadastrarUsuario(nome, email, senha)) {
            System.out.println("===============================");
            System.out.println("Cadastro realizado com sucesso!");
            System.out.println("===============================");
        } else {
            System.out.println("==================");
            System.out.println("Erro ao cadastrar!");
            System.out.println("==================");
        }
    }

    public static void mostrarMenuPrincipal() {
        System.out.println("\n=== BIBLIOTECH MENU ===");
        System.out.println(authService.getUsuarioLogado());
        System.out.println("=========================");
        System.out.println("[1] Alugar livro");
        System.out.println("[2] Devolver livro");
        System.out.println("[3] Meu Historico");
        System.out.println("[4] Meus Livros Alugados");
        System.out.println("[5] Meus Livros Devolvidos");
        System.out.println("[6] Cadastrar Livro");
        System.out.println("[7] Listar Livros");
        System.out.println("[8] Livros Disponiveis");
        System.out.println("[9] Livros Alugados");
        System.out.println("[10] Logout");
        System.out.println("[0] Sair");
        int opcao = lerInt("Escolha: ");

        switch (opcao) {
            case 1:
                mostrarMenuAlugarLivro(); break;
            case 2:
                mostrarMenuDevolverLivro(); break;
            case 3:
                emprestimoService.getEmprestimosUsuario(); break;
            case 4:
                emprestimoService.getEmprestimosAtivos(); break;
            case 5:
                emprestimoService.getEmprestimosDevolvidos(); break;
            case 6:
                mostrarMenuCadastrarLivro(); break;
            case 7:
                if (livroService.getLivros().isEmpty()) {
                    System.out.println("============");
                    System.out.println("Lista vazia!");
                    System.out.println("============");
                } else livroService.listarLivros();
                break;
            case 8:
                livroService.listarLivrosDisponiveis(); break;
            case 9:
                livroService.listarLivrosIndisponiveis(); break;
            case 10:
                if (authService.fazerLogout()) {
                    System.out.println("=================");
                    System.out.println("Logout realizado!");
                    System.out.println("=================");
                }
                break;
            case 0:
                System.out.println("=====================");
                System.out.println("Encerrando sistema...");
                System.out.println("=====================");
                System.exit(0);
                break;
            default:
                System.out.println("===============");
                System.out.println("Opcao invalida!");
                System.out.println("===============");
                break;
        }
    }

    public static void mostrarMenuCadastrarLivro() {
        System.out.println("\n=== BIBLIOTECH CADASTRAR LIVRO ===");
        System.out.println(authService.getUsuarioLogado());
        System.out.println("====================================");
        String titulo = lerString("Titulo: ");
        String autor = lerString("Autor: ");

        if(titulo.isEmpty()) {
            System.out.println("================");
            System.out.println("Titulo invalido!");
            System.out.println("================");
            return;
        }

        if(!ValidadorUtil.validarNome(autor)) {
            System.out.println("===============");
            System.out.println("Autor inválido!");
            System.out.println("===============");
            return;
        }

        if (livroService.cadastrarLivro(titulo, autor)) {
            System.out.println("=============================");
            System.out.println("Livro cadastrado com sucesso!");
            System.out.println("=============================");
        } else {
            System.out.println("==================");
            System.out.println("Erro ao cadastrar!");
            System.out.println("==================");
        }
    }

    public static void mostrarMenuAlugarLivro() {
        System.out.println("\n=== BIBLIOTECH ALUGAR LIVRO ===");
        System.out.println(authService.getUsuarioLogado());

        livroService.listarLivrosDisponiveis();

        int id = lerInt("Id do livro: ");

        ResultadoEmprestimo resultado = emprestimoService.emprestarLivro(id);

        switch (resultado) {
            case USUARIO_NAO_LOGADO:
                System.out.println("======================");
                System.out.println("Nenhum Usuario Logado!");
                System.out.println("======================");
                break;
            case SUCESSO:
                System.out.println("==========================");
                System.out.println("Livro alugado com sucesso!");
                System.out.println("Voce deve devolver ate " + LocalDate.now().plusDays(30));
                System.out.println("==========================");
                break;
            case LIVRO_NAO_ENCONTRADO:
                System.out.println("=====================");
                System.out.println("Livro nao encontrado!");
                System.out.println("=====================");
                break;
            case LIVRO_INDISPONIVEL:
                System.out.println("======================");
                System.out.println("Livro ja esta alugado!");
                System.out.println("======================");
                break;
        }
    }

    public static void mostrarMenuDevolverLivro() {
        System.out.println("\n=== BIBLIOTECH DEVOLVER LIVRO ===");
        System.out.println(authService.getUsuarioLogado());
        System.out.println("===================================");

        livroService.listarLivrosIndisponiveis();

        int id = lerInt("Id do livro: ");

        ResultadoDevolucao resultado = emprestimoService.devolverLivro(id);

        switch (resultado) {
            case USUARIO_NAO_LOGADO:
                System.out.println("======================");
                System.out.println("Nenhum Usuario Logado!");
                System.out.println("======================");
                break;
            case SUCESSO:
                System.out.println("============================");
                System.out.println("Livro devolvido com sucesso!");
                System.out.println("============================");
                break;
            case LIVRO_NAO_ENCONTRADO:
                System.out.println("=====================");
                System.out.println("Livro nao encontrado!");
                System.out.println("=====================");
                break;
            case EMPRESTIMO_NAO_ENCONTRADO:
                System.out.println("==========================");
                System.out.println("Emprestimo não encontrado!");
                System.out.println("==========================");
                break;
        }
    }

    public static int lerInt(String mensagem) {
        System.out.print(mensagem);
        while(!input.hasNextInt()) {
            System.out.println("\n======================");
            System.out.println("Digite um numero valido!");
            System.out.println("========================");
            input.next();
        }
        int valor = input.nextInt();
        input.nextLine();
        return valor;
    }

    public static String lerString(String mensagem) {
        System.out.print(mensagem);
        return input.nextLine().trim();
    }
}