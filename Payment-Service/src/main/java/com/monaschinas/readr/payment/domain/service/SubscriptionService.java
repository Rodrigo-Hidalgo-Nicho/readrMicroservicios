package com.monaschinas.readr.payment.domain.service;

import com.monaschinas.readr.payment.domain.model.Subscription;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SubscriptionService {
    List<Subscription> getAll();
    Subscription getById(Long subscriptionId);
    Subscription create(Subscription subscription);
    Subscription update(Long subscriptionId, Subscription subscription);
    ResponseEntity<?> delete(Long subscriptionId);
}
