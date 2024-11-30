package com.backend.ponto_eletronico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record AtualizarUsuarioDTO(

  @NotNull
  Long id,

  @Size(max = 120, message = "Nome ultrapassou 120 caracteres.")
  String nome,

  @Pattern(regexp = "^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}[\\s-]?\\d{4}$\n", message = "Apenas telefones brasileiros são aceitos.")
  String telefone,

  @Pattern(regexp = "^(?!.*(\\d)(?:-?\\1){2})\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$")
  String cpf,

  @Size(max = 100, message = "E-mail ultrapassou 100 caracteres.")
  @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@tjam\\.jus\\.br$\n", message = "Obrigatório utilizar e-mail do TJAM.")
  String email,

  @Size(max = 100, message = "E-mail ultrapassou 100 caracteres.")
  @Pattern(regexp = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$", message = "Email inválido.")
  String email_recuperacao,

  @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,16}$", message = "A senha deve conter letras minúsculas e maiúsculas, números, caracteres especiais e possuir entre 8 à 16 caracteres.")
  String senha,

  @NotBlank(message = "Data de nascimento é obrigatório.")
  LocalDate data_nascimento,


  Long id_departamento
) {
}
