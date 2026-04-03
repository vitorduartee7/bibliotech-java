package com.bibliotech.service;

import com.bibliotech.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private final List<Usuario> usuarios = new ArrayList<>();
    private int proximoIdUsuario = 1;
    private Usuario usuarioLogado;

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public boolean cadastrarUsuario(String nome, String email, String senha) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equals(email)) {
                return false;
            }
        }

        Usuario novoUsuario = new Usuario(proximoIdUsuario++, nome, email, senha);

        return usuarios.add(novoUsuario);
    }

    public boolean fazerLogin(String email, String senha) {
        usuarioLogado = null;

        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email) && usuario.getSenha().equals(senha)) {
                usuarioLogado = usuario;
                return true;
            }
        }
        return false;
    }

    public boolean isUsuarioLogado() {
        return usuarioLogado != null;
    }

    public boolean fazerLogout() {
        if (usuarioLogado == null) return false;
        else usuarioLogado = null;
        return true;
    }
}
