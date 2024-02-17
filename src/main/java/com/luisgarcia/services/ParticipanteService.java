package com.luisgarcia.services;

import com.luisgarcia.controllers.dtos.Participante;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ParticipanteService {

  Participante getById(long id);
  List<Participante> getAll(Pageable pageable);
  void delete(long id);
  Participante create(Participante participanteRequest);
  Participante update(long id, Participante participanteRequest);

}
