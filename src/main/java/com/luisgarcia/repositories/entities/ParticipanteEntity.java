package com.luisgarcia.repositories.entities;

import com.luisgarcia.repositories.enums.EstadoCivilEnum;
import com.luisgarcia.repositories.enums.SexoEnum;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class ParticipanteEntity extends AuditDataEntity implements Serializable {

  @NotBlank
  private String nome;
  @NotBlank
  private String email;
  @NotBlank
  private String cpf;

  private String telefonePrimario;
  private String telefoneSecundario;
  private Date dataNascimento;

  private EstadoCivilEnum estadoCivil;
  private SexoEnum sexo;

}
