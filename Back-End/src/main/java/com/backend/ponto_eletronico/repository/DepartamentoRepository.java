package com.backend.ponto_eletronico.repository;

import com.backend.ponto_eletronico.model.Departamento;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {
  Optional<Departamento> findByNomedepartamento(String nomedepartamento);
}
