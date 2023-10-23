package com.example.meutrello.usuario.record;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroUsuario(
        @NotBlank
        @NotNull
        String usuario,
        @NotBlank
        @NotNull
        String senha

        ) {
}
