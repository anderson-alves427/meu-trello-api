package com.example.meutrello.usuario.service;

import com.example.meutrello.exception.BadRequestException;
import com.example.meutrello.exception.ResourceNotFoundException;
import com.example.meutrello.usuario.entity.Usuario;
import com.example.meutrello.usuario.record.DadosCadastroUsuario;
import com.example.meutrello.usuario.record.DadosListagemUsuario;
import com.example.meutrello.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public Page<DadosListagemUsuario> listarUsuariosAtivos(Pageable paginacao) {
        Page page = usuarioRepository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new);
        return page;
    }

    public Usuario cadastra(DadosCadastroUsuario dados) {
        var usuario = usuarioRepository.findByUsuario(dados.usuario());
System.out.println("====="+ usuario);
        if (usuario != null) {
            throw new BadRequestException("Usuário já cadastrado. Tente outro.");
        }

        Usuario usuarioParaCadastro = new Usuario(dados);
        usuarioRepository.save(usuarioParaCadastro);
        return usuarioParaCadastro;
    }

}
