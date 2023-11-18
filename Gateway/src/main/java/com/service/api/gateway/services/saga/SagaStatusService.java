package com.service.api.gateway.services.saga;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        value = "sagaStatus",
        path = "api/v1/publishing/saga-statuses",
        url = "http://localhost:8085"
)
public interface SagaStatusService {
    @GetMapping
    Page<Object> getAllSagaStatuses(Pageable pageable);
}
