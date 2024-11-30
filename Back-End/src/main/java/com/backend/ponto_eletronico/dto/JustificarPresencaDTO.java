package com.backend.ponto_eletronico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record JustificarPresencaDTO (
    @NotNull
    Long matricula_usuario,

    @NotNull
    LocalDate dia,

    @NotBlank
    String ponto,

    @NotBlank
    String justificativa
) {
}
