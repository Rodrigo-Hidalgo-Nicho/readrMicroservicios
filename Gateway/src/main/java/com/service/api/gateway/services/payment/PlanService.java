package com.service.api.gateway.services.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "plan",
        path = "api/v1/publishing/plans",
        url = "http://localhost:8084"
)
public interface PlanService {
    @GetMapping
    Page<Object> getAllPlans(Pageable pageable);

    @PostMapping
    Object createPlan(@RequestBody Object resource);
}
