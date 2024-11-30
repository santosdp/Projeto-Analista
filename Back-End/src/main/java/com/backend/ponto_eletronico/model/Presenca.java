package com.backend.ponto_eletronico.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

@Table(name = "presencas")
@Entity(name = "presenca")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id_presenca")
public class Presenca {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id_presenca;

  private LocalDate dia;
  private LocalTime hora_entrada;
  private LocalTime hora_intervalo;
  private LocalTime hora_retorno;
  private LocalTime hora_saida;
  private String justificativa;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_usuario", nullable = false)
  private Usuario usuario;

  public Presenca(LocalDate dia) {
    this.dia = dia;
    this.hora_entrada = null;
    this.hora_intervalo = null;
    this.hora_retorno = null;
    this.hora_saida = null;
    this.justificativa = "";
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
    if (!usuario.getPresencas().contains(this)) {
      usuario.getPresencas().add(this);
    }
  }

  public void registrarPresenca() {
    LocalTime hora = LocalTime.now();
    if(this.hora_entrada == null) {
      this.hora_entrada = hora;
    } else if(this.hora_intervalo == null) {
      this.hora_intervalo = hora;
    } else if(this.hora_retorno == null) {
      this.hora_retorno = hora;
    } else if(this.hora_saida == null) {
      this.hora_saida = hora;
    }
  }

  public long calcularAproveitamento() {
    LocalTime agora = LocalTime.now();
    long total = 0L;
    total += calcularDuracao(this.hora_entrada, this.hora_intervalo != null ? this.hora_intervalo  : agora);
    if (this.hora_retorno != null) {
      total += calcularDuracao(this.hora_retorno, this.hora_saida != null ? this.hora_saida : agora);
    }
    return total;
  }

  public void justificarPresenca(String ponto, String justificativa) {
    switch (ponto){
      case "entrada":
        this.hora_entrada = LocalTime.of(8,0,0);
        break;
      case "intervalo":
        this.hora_intervalo = LocalTime.of(12,0,0);
        break;
      case "retorno":
        this.hora_retorno = LocalTime.of(13,0,0);
        break;
      case "saida":
        this.hora_saida = LocalTime.of(17,0,0);
        break;
      case "dia inteiro":
        this.hora_entrada = LocalTime.of(8,0,0);
        this.hora_intervalo = LocalTime.of(12,0,0);
        this.hora_retorno = LocalTime.of(13,0,0);
        this.hora_saida = LocalTime.of(17,0,0);
        break;
    }
    this.justificativa = justificativa;
  }

  public static long calcularDuracao(LocalTime start, LocalTime end) {
    if (start == null || end == null) {
      return 0;
    }
    Duration duration = Duration.between(start, end);
    return duration.toMinutes();
  }
}
