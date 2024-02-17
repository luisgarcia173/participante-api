package com.luisgarcia.services;

import com.luisgarcia.controllers.dtos.Participante;
import com.luisgarcia.repositories.ParticipanteRepository;
import com.luisgarcia.repositories.entities.ParticipanteEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith( SpringExtension.class )
@SpringBootTest
public class ParticipanteServiceTest {

  @Autowired
  private ParticipanteService service;

  @MockBean
  private ParticipanteRepository repository;

  @BeforeEach
  public void setUp() {
    ParticipanteEntity p1 = new ParticipanteEntity();
    p1.setId(1);
    p1.setNome("Luis Garcia");

    when(repository.findById(1L)).thenReturn(Optional.of(p1));
  }

  @Test
  public void testFindById() {
    // Arrange
    Long id = 1L;

    // Act
    Participante found = service.getById(id);

    // Assert
    assertThat(found.getNome()).isEqualTo("Luis Garcia");
  }

}
