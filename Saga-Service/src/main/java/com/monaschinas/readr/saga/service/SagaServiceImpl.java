package com.monaschinas.readr.saga.service;

import com.monaschinas.readr.shared.exception.ResourceNotFoundException;
import com.monaschinas.readr.shared.exception.ResourceValidationException;
import com.monaschinas.readr.saga.domain.model.Saga;
import com.monaschinas.readr.saga.domain.persistence.SagaRepository;
import com.monaschinas.readr.saga.domain.service.SagaService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SagaServiceImpl implements SagaService {
    private static final String ENTITY = "Saga";

    private final SagaRepository sagaRepository;

    private final Validator validator;

    public SagaServiceImpl(SagaRepository sagaRepository, Validator validator) {
        this.sagaRepository = sagaRepository;
        this.validator = validator;
    }

    @Override
    public List<Saga> getAll() { return sagaRepository.findAll(); }

    @Override
    public Page<Saga> getAll(Pageable pageable) { return sagaRepository.findAll(pageable);}

    @Override
    public Saga getById(Long sagaId) {
        return sagaRepository.findById(sagaId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,sagaId));
    }

    @Override
    public Saga create(Saga saga) {
        Set<ConstraintViolation<Saga>> violations = validator.validate(saga);

        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        if(sagaRepository.findByTitle(saga.getTitle()).isPresent()) {
            throw new ResourceValidationException(ENTITY, "A genre with the same name already exists");
        }

        return sagaRepository.save(saga);
    }

    @Override
    public Saga update(Long sagaId, Saga saga) {
        Set<ConstraintViolation<Saga>> violations = validator.validate(saga);

        if(!violations.isEmpty()){
            throw new ResourceValidationException(ENTITY,violations);
        }
        Optional<Saga> sagaWithTitle = sagaRepository.findByTitle(saga.getTitle());
        if(sagaWithTitle.isPresent() && !sagaWithTitle.get().getId().equals(saga.getId())) {
            throw new ResourceValidationException(ENTITY, "A language with the same name already exists");
        }
        return sagaRepository.findById(sagaId)
                .map(genreToUpdate -> sagaRepository.save(genreToUpdate
                        .withTitle(saga.getTitle())))
                .orElseThrow(()->new ResourceNotFoundException(ENTITY,sagaId));
    }

    @Override
    public ResponseEntity<?> delete(Long sagaId) {
        return sagaRepository.findById(sagaId)
                .map(saga -> {
                    sagaRepository.delete(saga);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY,sagaId));
    }
}
