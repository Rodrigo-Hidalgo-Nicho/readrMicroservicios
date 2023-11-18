package com.monaschinas.readr.payment.api.rest;

import com.monaschinas.readr.payment.domain.service.PlanService;
import com.monaschinas.readr.payment.mapping.PlanMapper;
import com.monaschinas.readr.payment.resource.CreatePlanResource;
import com.monaschinas.readr.payment.resource.PlanResource;
import com.monaschinas.readr.payment.resource.UpdatePlanResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/publishing/plans", produces = "application/json")
public class PlanController {
    private final PlanService planService;
    private final PlanMapper mapper;

    public PlanController(PlanService planService, PlanMapper mapper){
        this.planService = planService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<PlanResource> getAllPlans(Pageable pageable){
        return mapper.modelListPage(planService.getAll(), pageable);
    }

    @GetMapping("{planId}")
    public PlanResource getPlanById(@PathVariable Long planId){
        return mapper.toResource(planService.getById(planId));
    }

    @PostMapping
    public PlanResource createPlan(@RequestBody CreatePlanResource resource){
        return mapper.toResource(planService.create(mapper.toModel(resource)));
    }

    @PutMapping("{planId}")
    public PlanResource updatePlan(@PathVariable Long planId, @RequestBody UpdatePlanResource resource){
        return mapper.toResource(planService.update(planId, mapper.toModel(resource)));
    }

    @DeleteMapping("{planId}")
    public ResponseEntity<?> deletePlan(@PathVariable Long planId){
        return planService.delete(planId);
    }
}
