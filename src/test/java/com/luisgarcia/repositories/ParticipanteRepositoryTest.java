package com.luisgarcia.repositories;

import com.luisgarcia.repositories.entities.ParticipanteEntity;
import com.luisgarcia.repositories.enums.StatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith( SpringExtension.class )
@DataJpaTest
public class ParticipanteRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ParticipanteRepository repository;

  @BeforeEach
  public void setUp() {
    ParticipanteEntity p1 = new ParticipanteEntity();
    p1.setStatus(StatusEnum.ATIVO);
    p1.setNome("Luis Garcia");
    entityManager.persist(p1);
  }

  @Test
  public void testFindById() {
    // given
    long id = 1L;

    // when
    Optional<ParticipanteEntity> found = repository.findById(id);

    // then
    assertThat(found.get().getNome()).isEqualTo("Luis Garcia");
    assertThat(found.get().getId()).isEqualTo(1L);
    assertThat(found.get().getStatus()).isEqualTo(StatusEnum.ATIVO);
  }

}
