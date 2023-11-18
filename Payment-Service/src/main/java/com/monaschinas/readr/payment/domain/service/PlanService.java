package com.monaschinas.readr.payment.domain.service;

import com.monaschinas.readr.payment.domain.model.Plan;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PlanService {
    List<Plan> getAll();
    Plan getById(Long planId);
    Plan create(Plan plan);
    Plan update(Long planId, Plan plan);
    ResponseEntity<?> delete(Long planId);
}
