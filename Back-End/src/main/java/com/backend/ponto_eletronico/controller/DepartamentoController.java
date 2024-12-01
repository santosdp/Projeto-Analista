package com.backend.ponto_eletronico.controller;

import com.backend.ponto_eletronico.dto.AtualizarDepartamentoDTO;
import com.backend.ponto_eletronico.dto.DepartamentoDTO;
import com.backend.ponto_eletronico.dto.DetalhamentoDepartamentoDTO;
import com.backend.ponto_eletronico.service.DepartamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("departamento")
public class DepartamentoController {

  @Autowired
  DepartamentoService departamentoService;

  @PostMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Transactional
  public ResponseEntity<DetalhamentoDepartamentoDTO> criarDepartamento(@RequestBody @Valid DepartamentoDTO departamentoDTO, UriComponentsBuilder uriBuilder) {
    var departamento = departamentoService.criarDepartamento(departamentoDTO);
    var uri = uriBuilder.path("/departamento/{id}").buildAndExpand(departamento.id_departamento()).toUri();

    return ResponseEntity.created(uri).body(departamento);
  }

  @GetMapping("/listar")
  public ResponseEntity<List<DetalhamentoDepartamentoDTO>> listarDepartamentos() {
    var departamentos = departamentoService.listarDepartamentos();
    return ResponseEntity.ok().body(departamentos);
  }

  @GetMapping("/{id}")
  public ResponseEntity<DetalhamentoDepartamentoDTO> detalharDepartamento(@PathVariable Long id){
    var departamento = departamentoService.detalharDepartamento(id);
    return ResponseEntity.ok().body(departamento);
  }

  @PutMapping
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @Transactional
  public ResponseEntity<DetalhamentoDepartamentoDTO> atualizarDepartamento(@RequestBody @Valid AtualizarDepartamentoDTO atualizarDepartamentoDTO) {
    var departamento = departamentoService.atualizarDepartamento(atualizarDepartamentoDTO);
    return ResponseEntity.ok().body(departamento);
  }
}
