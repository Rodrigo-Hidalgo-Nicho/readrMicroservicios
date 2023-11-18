package com.monaschinas.readr.payment.service;

import com.monaschinas.readr.payment.domain.model.Subscription;
import com.monaschinas.readr.payment.domain.persistence.SubscriptionRepository;
import com.monaschinas.readr.payment.domain.service.SubscriptionService;
import com.monaschinas.readr.shared.exception.ResourceNotFoundException;
import com.monaschinas.readr.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    private static final String ENTITY = "Subscription";
    private final SubscriptionRepository subscriptionRepository;
    private final Validator validator;

    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository, Validator validator) {
        this.subscriptionRepository = subscriptionRepository;
        this.validator = validator;
    }

    @Override
    public List<Subscription> getAll() {
        return subscriptionRepository.findAll();
    }

    @Override
    public Subscription getById(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, subscriptionId));
    }

    @Override
    public Subscription create(Subscription subscription) {
        Set<ConstraintViolation<Subscription>> violations = validator.validate(subscription);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return subscriptionRepository.save(subscription);
    }
    @Override
    public Subscription update(Long subscriptionId, Subscription subscription) {
        Set<ConstraintViolation<Subscription>> violations = validator.validate(subscription);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        return subscriptionRepository.findById(subscriptionId)
                .map(subscriptionToUpdate -> {
                    subscriptionToUpdate.setStartedAt(subscription.getStartedAt());
                    subscriptionToUpdate.setFinishedAt(subscription.getFinishedAt());
                    subscriptionToUpdate.setUserId(subscription.getUserId());
                    subscriptionToUpdate.setPlan(subscription.getPlan());

                    return subscriptionRepository.save(subscriptionToUpdate);
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, subscriptionId));
    }

    @Override
    public ResponseEntity<?> delete(Long subscriptionId) {
        return subscriptionRepository.findById(subscriptionId)
                .map(subscription -> {
                    subscriptionRepository.delete(subscription);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, subscriptionId));
    }
}
