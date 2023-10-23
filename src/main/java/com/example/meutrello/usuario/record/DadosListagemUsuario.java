package com.example.meutrello.usuario.record;

import com.example.meutrello.usuario.entity.Usuario;

import java.util.Date;

public record DadosListagemUsuario(Integer id, String usuario, String senha, Date data_criacao, Date data_alteracao) {

    public DadosListagemUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getUsuario(), usuario.getSenha(), usuario.getData_criacao(), usuario.getData_alteracao());
    }
}
