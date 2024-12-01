package com.backend.ponto_eletronico.service;

import com.backend.ponto_eletronico.dto.*;
import com.backend.ponto_eletronico.model.Departamento;
import com.backend.ponto_eletronico.model.Usuario;
import com.backend.ponto_eletronico.repository.DepartamentoRepository;
import com.backend.ponto_eletronico.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  @Autowired
  private DepartamentoRepository departamentoRepository;

  public DetalhamentoDepartamentoDTO criarDepartamento(DepartamentoDTO departamentoDTO) {
    Usuario usuario = usuarioRepository.findByMatricula(departamentoDTO.matricula_gerente()).orElseThrow(() -> new EntityNotFoundException("Matricula de gerente não encontrado"));
    Departamento departamento = new Departamento(
        null,
        departamentoDTO.nome(),
        null,
        null
    );
    departamento.addUsuario(usuario);
    departamento.setGerente(usuario);
    departamentoRepository.save(departamento);
    return new DetalhamentoDepartamentoDTO(departamento);
  }

  public DetalhamentoDepartamentoDTO detalharDepartamento(Long id) {
    Departamento departamento = departamentoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Departamento não encontrado"));
    return new DetalhamentoDepartamentoDTO(departamento);
  }

  public List<DetalhamentoDepartamentoDTO> listarDepartamentos() {
    return departamentoRepository.findAll().stream().map(DetalhamentoDepartamentoDTO::new).toList();
  }

  public DetalhamentoDepartamentoDTO atualizarDepartamento(AtualizarDepartamentoDTO atualizarDepartamentoDTO) {
    Departamento departamento = departamentoRepository.findById(atualizarDepartamentoDTO.id()).orElseThrow(() -> new EntityNotFoundException("Departamento não encontrado"));
    if(atualizarDepartamentoDTO.matricula_gerente() != null) {
      Usuario gerente = usuarioRepository.findByMatricula(atualizarDepartamentoDTO.matricula_gerente()).orElseThrow(() -> new EntityNotFoundException("Matricula não corresponde a nenhum usuário"));
      departamento.atualizarDepartamento(atualizarDepartamentoDTO, gerente);
    } else {
      departamento.atualizarDepartamento(atualizarDepartamentoDTO, null);
    }
    departamentoRepository.save(departamento);

    return new DetalhamentoDepartamentoDTO(departamento);
  }


}