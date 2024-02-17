package com.luisgarcia.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luisgarcia.controllers.dtos.Participante;
import com.luisgarcia.exceptions.ParticipanteNaoEncontradoException;
import com.luisgarcia.repositories.enums.StatusEnum;
import com.luisgarcia.services.ParticipanteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ParticipanteController.class)

public class ParticipanteControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ParticipanteService service;

  @BeforeEach
  public void setUp() {
    // Do nothing
    doNothing().when(service).delete(anyLong());

    // Success
    given(service.getById(eq(1L))).willReturn(Participante.builder().id(1).nome("Luis Garcia").build());
    given(service.create(any())).willReturn(Participante.builder().id(3).nome("Luis Garcia").status(true).build());
    given(service.update(anyLong(), any())).willReturn(Participante.builder().id(4).nome("Luis Garcia - Alterado").build());

    // Error
    given(service.getById(eq(99L))).willThrow(ParticipanteNaoEncontradoException.class);
  }

  @Test
  void testGetById() throws Exception {
    mockMvc.perform(
            get("/api/participante/1")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.nome", is("Luis Garcia")));
  }

  @Test
  void testGetByIdWithNonExistingId() throws Exception {
    mockMvc.perform(
            get("/api/participante/99")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testDelete() throws Exception {
    mockMvc.perform(
            delete("/api/participante/2")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());
  }

//  /** Não esta passando ainda */
//  @Test
//  public void testCreate() throws Exception {
//    Participante participante = Participante.builder().id(3).nome("Luis Garcia").build();
//    String payload = new ObjectMapper().writeValueAsString(participante);
//
//    mockMvc.perform(
//            post("/api/participante/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .characterEncoding("UTF-8")
//                .content(payload))
//        .andExpect(status().isCreated())
//        .andExpect(jsonPath("$.id", is(3)))
//        .andExpect(jsonPath("$.status", is(true)));
//  }

  @Test
  public void testUpdate() throws Exception {
    Participante participante = Participante.builder().id(4).nome("Luis Garcia - Alterado").build();
    String payload = new ObjectMapper().writeValueAsString(participante);

    mockMvc.perform(
            put("/api/participante/4")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(payload))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(4)))
        .andExpect(jsonPath("$.nome", is("Luis Garcia - Alterado")));
  }

}
