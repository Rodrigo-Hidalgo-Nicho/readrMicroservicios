package com.service.api.gateway.services.saga;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "saga",
        path = "api/v1/publishing/sagas",
        url = "http://localhost:8085"
)
public interface SagaService {
    @GetMapping
    Page<Object> getAllSagas(Pageable pageable);

    @PostMapping
    Object createSaga(@RequestBody Object resource);

    @GetMapping("/{sagaId}")
    Object getSagaById(@PathVariable("sagaId") Long sagaId);
}
