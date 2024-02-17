package com.luisgarcia.controllers.converters;

import com.luisgarcia.controllers.dtos.Participante;
import com.luisgarcia.repositories.entities.ParticipanteEntity;
import com.luisgarcia.repositories.enums.EstadoCivilEnum;
import com.luisgarcia.repositories.enums.SexoEnum;
import com.luisgarcia.repositories.enums.StatusEnum;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;


public class ParticipanteConverter {

  private ParticipanteConverter() {}

  public static Participante convert(ParticipanteEntity entidade) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    return Participante.builder()
        .id(entidade.getId())
        .nome(entidade.getNome())
        .cpf(entidade.getCpf())
        .email(entidade.getEmail())
        .sexo(!Objects.isNull(entidade.getSexo()) ? entidade.getSexo().toString() : null)
        .dataNascimento(!Objects.isNull(entidade.getDataNascimento()) ? formatter.format(entidade.getDataNascimento()) : null)
        .estadoCivil(!Objects.isNull(entidade.getEstadoCivil()) ? entidade.getEstadoCivil().toString() : null)
        .status(!Objects.isNull(entidade.getStatus()) && entidade.getStatus().equals(StatusEnum.ATIVO))
        .telefonePrimario(entidade.getTelefonePrimario())
        .telefoneSecundario(entidade.getTelefoneSecundario())
        .build();
  }
  public static ParticipanteEntity convert(Participante dto) throws ParseException {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    ParticipanteEntity entity = new ParticipanteEntity();
    entity.setId(dto.getId());
    entity.setNome(dto.getNome());
    entity.setCpf(dto.getCpf());
    entity.setEmail(dto.getEmail());
    entity.setSexo(!Objects.isNull(dto.getSexo()) ? SexoEnum.valueOf(dto.getSexo()) : null);
    entity.setDataNascimento(!Objects.isNull(dto.getDataNascimento()) ? formatter.parse(dto.getDataNascimento()): null);
    entity.setEstadoCivil(!Objects.isNull(dto.getEstadoCivil()) ? EstadoCivilEnum.valueOf(dto.getEstadoCivil()): null);
    entity.setStatus(dto.isStatus() ? StatusEnum.ATIVO : StatusEnum.INATIVO);
    entity.setTelefonePrimario(dto.getTelefonePrimario());
    entity.setTelefoneSecundario(dto.getTelefoneSecundario());
    return entity;
  }

  public static void merge(ParticipanteEntity entity, Participante dto) throws ParseException {
    ParticipanteEntity dtoConvertido = convert(dto);
    entity.setNome(dtoConvertido.getNome());
    entity.setCpf(dtoConvertido.getCpf());
    entity.setEmail(dtoConvertido.getEmail());
    entity.setSexo(dtoConvertido.getSexo());
    entity.setDataNascimento(dtoConvertido.getDataNascimento());
    entity.setEstadoCivil(dtoConvertido.getEstadoCivil());
    entity.setStatus(dtoConvertido.getStatus());
    entity.setTelefonePrimario(dtoConvertido.getTelefonePrimario());
    entity.setTelefoneSecundario(dtoConvertido.getTelefoneSecundario());
  }

}
