package com.service.api.gateway.api.rest.saga;

import com.service.api.gateway.services.saga.SagaStatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/gateway/saga-statuses", produces = "application/json")
public class SagaStatusController {
    private final SagaStatusService sagaStatusService;

    public SagaStatusController(SagaStatusService sagaStatusService) {
        this.sagaStatusService = sagaStatusService;
    }

    @GetMapping
    public ResponseEntity<Page<Object>> getAllSagaStatuses(Pageable pageable){
        return ResponseEntity.ok(this.sagaStatusService.getAllSagaStatuses(pageable));
    }
}
