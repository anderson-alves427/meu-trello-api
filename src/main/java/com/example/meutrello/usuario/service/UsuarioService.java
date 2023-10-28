package com.example.meutrello.usuario.service;

import com.example.meutrello.exception.BadRequestException;
import com.example.meutrello.usuario.entity.Usuario;
import com.example.meutrello.usuario.record.DadosCadastroUsuario;
import com.example.meutrello.usuario.record.DadosListagemUsuario;
import com.example.meutrello.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public Page<DadosListagemUsuario> listarUsuariosAtivos(Pageable paginacao) {
        return usuarioRepository.findAllByAtivoTrue(paginacao).map(DadosListagemUsuario::new);
    }

    public Usuario cadastra(DadosCadastroUsuario dados) {
        var usuario = usuarioRepository.findByUsuario(dados.usuario());
        if (usuario != null) {
            throw new BadRequestException("Usuário já cadastrado. Tente outro.");
        }
        Usuario usuarioParaCadastro = new Usuario(dados);
        var passwordEnconder = bCryptPasswordEncoder.encode(dados.senha());
        usuarioParaCadastro.setSenha(passwordEnconder);
        usuarioRepository.save(usuarioParaCadastro);
        return usuarioParaCadastro;
    }

}
