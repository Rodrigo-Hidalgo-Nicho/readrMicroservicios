package com.monaschinas.readr.saga.domain.persistence;

import com.monaschinas.readr.saga.domain.model.Saga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SagaRepository extends JpaRepository<Saga, Long> {
    Optional<Saga> findByTitle(String title);
}
