package com.monaschinas.readr.payment.domain.persistence;

import com.monaschinas.readr.payment.domain.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
}
