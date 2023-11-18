package com.monaschinas.readr.payment.service;

import com.monaschinas.readr.payment.domain.model.Plan;
import com.monaschinas.readr.payment.domain.persistence.PlanRepository;
import com.monaschinas.readr.payment.domain.service.PlanService;
import com.monaschinas.readr.shared.exception.ResourceNotFoundException;
import com.monaschinas.readr.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class PlanServiceImpl implements PlanService {
    private static final String ENTITY = "Plan";
    private final PlanRepository planRepository;
    private final Validator validator;

    public PlanServiceImpl(PlanRepository planRepository, Validator validator) {
        this.planRepository = planRepository;
        this.validator = validator;
    }

    @Override
    public List<Plan> getAll() {
        return planRepository.findAll();
    }

    @Override
    public Plan getById(Long planId) {
        return planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, planId));
    }

    @Override
    public Plan create(Plan plan) {
        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return planRepository.save(plan);
    }

    @Override
    public Plan update(Long planId, Plan plan) {
        Set<ConstraintViolation<Plan>> violations = validator.validate(plan);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return planRepository.findById(planId)
                .map(planToUpdate -> {
                    planToUpdate.setName(plan.getName());
                    planToUpdate.setDescription(plan.getDescription());
                    planToUpdate.setAmount(plan.getAmount());
                    return planRepository.save(planToUpdate);
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, planId));
    }

    @Override
    public ResponseEntity<?> delete(Long planId) {
        return planRepository.findById(planId)
                .map(plan -> {
                    planRepository.delete(plan);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, planId));
    }
}
