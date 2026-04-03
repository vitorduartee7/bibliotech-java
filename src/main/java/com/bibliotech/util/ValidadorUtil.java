package com.bibliotech.util;

public class ValidadorUtil {

    public static boolean validarNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) return false;

        String nomeLimpo = nome.trim();

        if (!nomeLimpo.matches("[a-zA-Zà-úÀ-Ú ]+")) {
            return false;
        }

        return nomeLimpo.length() >= 2;
    }

    public static boolean validarEmail(String email) {
        if (email == null || email.trim().isEmpty()) return false;

        String emailLimpo = email.trim();

        String regex = "^[a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        return emailLimpo.matches(regex);
    }

    public static boolean validarSenha(String senha) {
        if (senha == null) return false;

        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,16}$";

        return senha.matches(regex);
    }
}