package com.monaschinas.readr.payment.domain.persistence;

import com.monaschinas.readr.payment.domain.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
