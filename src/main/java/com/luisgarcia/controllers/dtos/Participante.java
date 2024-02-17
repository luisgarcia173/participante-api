package com.luisgarcia.controllers.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Participante implements Serializable {

  private long id;
  private String nome;
  private String email;
  private String cpf;
  private String sexo;
  private String estadoCivil;
  private String telefonePrimario;
  private String telefoneSecundario;
  private String dataNascimento;
  private boolean status;

}
