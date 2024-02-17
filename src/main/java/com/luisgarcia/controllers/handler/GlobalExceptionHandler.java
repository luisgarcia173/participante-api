package com.luisgarcia.controllers.handler;

import com.luisgarcia.exceptions.ApplicationException;
import com.luisgarcia.exceptions.ParticipanteNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ParticipanteNaoEncontradoException.class)
  ProblemDetail handleParticipanteNaoEncontradoException(ParticipanteNaoEncontradoException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    problemDetail.setProperty("StackTrace", e.getStackTrace());
    problemDetail.setProperty("TimeStamp", Instant.now());
    return problemDetail;
  }

  @ExceptionHandler(ApplicationException.class)
  ProblemDetail handleApplicationException(ApplicationException e) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
    problemDetail.setTitle("Erro interno");
    problemDetail.setProperty("StackTrace", e.getStackTrace());
    problemDetail.setProperty("TimeStamp", Instant.now());
    return problemDetail;
  }

}
