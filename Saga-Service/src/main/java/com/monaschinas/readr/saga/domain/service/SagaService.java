package com.monaschinas.readr.saga.domain.service;

import com.monaschinas.readr.saga.domain.model.Saga;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SagaService {
    List<Saga> getAll();
    Page<Saga> getAll(Pageable pageable);
    Saga getById(Long sagaId);
    Saga create(Saga saga);
    Saga update(Long sagaId, Saga saga);
    ResponseEntity<?> delete(Long sagaId);
}
