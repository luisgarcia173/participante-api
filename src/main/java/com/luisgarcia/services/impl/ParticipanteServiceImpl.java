package com.luisgarcia.services.impl;

import com.luisgarcia.controllers.converters.ParticipanteConverter;
import com.luisgarcia.controllers.dtos.Participante;
import com.luisgarcia.exceptions.ApplicationException;
import com.luisgarcia.exceptions.ParticipanteNaoEncontradoException;
import com.luisgarcia.repositories.ParticipanteRepository;
import com.luisgarcia.repositories.entities.ParticipanteEntity;
import com.luisgarcia.repositories.enums.StatusEnum;
import com.luisgarcia.services.ParticipanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ParticipanteServiceImpl implements ParticipanteService {

  private final ParticipanteRepository repository;

  public Participante getById(long id) {
    ParticipanteEntity participanteEntity = repository.findById(id).orElseThrow(ParticipanteNaoEncontradoException::new);
    return ParticipanteConverter.convert(participanteEntity);
  }

  public List<Participante> getAll(Pageable pageable) {
    List<ParticipanteEntity> participantes = repository.findAll(pageable).stream().toList();
    if (!participantes.isEmpty()) {
      return participantes.stream().map(ParticipanteConverter::convert).toList();
    }
    throw new ParticipanteNaoEncontradoException();
  }

  public void delete(long id) {
    repository.deleteById(id);
  }

  public Participante create(Participante participanteRequest) {
    try {
      ParticipanteEntity entity = ParticipanteConverter.convert(participanteRequest);
      entity.setDataCriacao(new Date());
      entity.setStatus(StatusEnum.ATIVO);
      repository.save(entity);
      return ParticipanteConverter.convert(entity);
    } catch (ParseException e) {
      throw new ApplicationException(e.getMessage());
    }
  }

  public Participante update(long id, Participante dto) {
    ParticipanteEntity entidade = repository.findById(id).orElseThrow(ParticipanteNaoEncontradoException::new);

    try {
      ParticipanteConverter.merge(entidade, dto);
      entidade.setDataAlteracao(new Date());
      repository.save(entidade);
      return ParticipanteConverter.convert(entidade);
    } catch (ParseException e) {
      throw new ApplicationException(e.getMessage());
    }
  }

}
