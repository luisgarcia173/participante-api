package com.luisgarcia.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ParticipanteNaoEncontradoException extends RuntimeException {

  public ParticipanteNaoEncontradoException() {
    super("Participante não encontrado");
  }

}
