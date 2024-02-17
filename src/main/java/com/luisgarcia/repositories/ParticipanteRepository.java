package com.luisgarcia.repositories;

import com.luisgarcia.repositories.entities.ParticipanteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<ParticipanteEntity, Long> {

}
