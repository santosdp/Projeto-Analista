package com.backend.ponto_eletronico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioLoginDTO(
    @NotBlank
    String login,

    @NotBlank
    String senha) {
}
