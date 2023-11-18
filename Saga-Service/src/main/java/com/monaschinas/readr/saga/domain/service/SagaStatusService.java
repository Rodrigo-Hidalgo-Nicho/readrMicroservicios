package com.monaschinas.readr.saga.domain.service;

import com.monaschinas.readr.saga.domain.model.SagaStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SagaStatusService {
    List<SagaStatus> getAll();
    Page<SagaStatus> getAll(Pageable pageable);
    SagaStatus getById(Long sagaStatusId);
    SagaStatus create(SagaStatus sagaStatus);
    SagaStatus update(Long sagaStatusId, SagaStatus sagaStatus);
    ResponseEntity<?> delete(Long sagaStatusId);
}
