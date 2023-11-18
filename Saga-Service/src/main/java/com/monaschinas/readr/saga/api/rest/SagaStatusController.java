package com.monaschinas.readr.saga.api.rest;

import com.monaschinas.readr.saga.domain.service.SagaStatusService;
import com.monaschinas.readr.saga.mapping.SagaStatusMapper;
import com.monaschinas.readr.saga.resource.CreateSagaStatusResource;
import com.monaschinas.readr.saga.resource.SagaStatusResource;
import com.monaschinas.readr.saga.resource.UpdateSagaStatusResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/publishing/saga-statuses", produces = "application/json")
public class SagaStatusController {
    private final SagaStatusService sagaStatusService;
    private final SagaStatusMapper mapper;

    public SagaStatusController(SagaStatusService sagaStatusService, SagaStatusMapper mapper) {
        this.sagaStatusService = sagaStatusService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<SagaStatusResource> getAllSagaStatuses(Pageable pageable){
        return mapper.modelListPage(sagaStatusService.getAll(),pageable);
    }

    @GetMapping({"{sagaStatusId}"})
    public SagaStatusResource getSagaStatusById(@PathVariable Long sagaStatusId){
        return mapper.toResource(sagaStatusService.getById(sagaStatusId));
    }

    @PostMapping
    public SagaStatusResource createSagaStatus(@RequestBody CreateSagaStatusResource resource){
        return mapper.toResource(sagaStatusService.create(mapper.toModel(resource)));
    }

    @PutMapping({"{sagaStatusId}"})
    public SagaStatusResource updateSaga(@PathVariable Long sagaStatusId, @RequestBody UpdateSagaStatusResource resource){
        return mapper.toResource(sagaStatusService.update(sagaStatusId, mapper.toModel(resource)));
    }

    @DeleteMapping({"{sagaStatusId}"})
    public ResponseEntity<?> deleteSagaStatus(@PathVariable Long sagaStatusId){ return sagaStatusService.delete(sagaStatusId); }
}
