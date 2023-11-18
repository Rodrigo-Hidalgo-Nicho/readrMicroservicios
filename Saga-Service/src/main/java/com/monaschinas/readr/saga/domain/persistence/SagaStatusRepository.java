package com.monaschinas.readr.saga.domain.persistence;

import com.monaschinas.readr.saga.domain.model.SagaStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SagaStatusRepository extends JpaRepository<SagaStatus, Long> {
    Optional<SagaStatus> findByName(String name);
}
