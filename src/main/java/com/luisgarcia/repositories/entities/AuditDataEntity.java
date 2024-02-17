package com.luisgarcia.repositories.entities;

import com.luisgarcia.repositories.enums.StatusEnum;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@MappedSuperclass
@Data
public class AuditDataEntity {

  @Id
  @GeneratedValue
  private long id;

  private Date dataCriacao;
  private Date dataAlteracao;
  private StatusEnum status;

  public AuditDataEntity() {
    this.dataCriacao = new Date();
    this.status = StatusEnum.ATIVO;
  }

}
