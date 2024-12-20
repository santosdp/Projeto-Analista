package com.backend.ponto_eletronico.dto;

import com.backend.ponto_eletronico.model.Cargo;
import com.backend.ponto_eletronico.model.Usuario;

import java.time.LocalDate;

public record DetalhamentoUsuarioDTO(
    Long id,
    String nome,
    Long matricula,
    String cpf,
    String email,
    String email_recuperacao,
    LocalDate data_nascimento,
    Cargo cargo,
    DetalhamentoDepartamentoDTO departamento) {
  public DetalhamentoUsuarioDTO(Usuario usuario) {
    this(usuario.getId(),usuario.getNome(), usuario.getMatricula(), usuario.getCpf(), usuario.getEmail(),usuario.getEmail_recuperacao(), usuario.getData_nascimento(), usuario.getCargo(), new DetalhamentoDepartamentoDTO(usuario.getDepartamento().getId_departamento(), usuario.getDepartamento().getNomedepartamento(), usuario.getDepartamento().getGerente().getNome()));
  }
}
