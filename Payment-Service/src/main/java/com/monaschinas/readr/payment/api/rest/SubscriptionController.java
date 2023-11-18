package com.monaschinas.readr.payment.api.rest;

import com.monaschinas.readr.payment.domain.service.SubscriptionService;
import com.monaschinas.readr.payment.mapping.SubscriptionMapper;
import com.monaschinas.readr.payment.resource.CreateSubscriptionResource;
import com.monaschinas.readr.payment.resource.SubscriptionResource;
import com.monaschinas.readr.payment.resource.UpdateSubscriptionResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/publishing/subscriptions", produces = "application/json")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final SubscriptionMapper mapper;

    public SubscriptionController(SubscriptionService subscriptionService, SubscriptionMapper mapper){
        this.subscriptionService = subscriptionService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<SubscriptionResource> getAllSubscriptions(Pageable pageable){
        return mapper.modelListPage(subscriptionService.getAll(), pageable);
    }

    @GetMapping("{subscriptionId}")
    public SubscriptionResource getSubscriptionById(@PathVariable Long subscriptionId){
        return mapper.toResource(subscriptionService.getById(subscriptionId));
    }

    @PostMapping
    public SubscriptionResource createSubscription(@RequestBody CreateSubscriptionResource resource){
        return mapper.toResource(subscriptionService.create(mapper.toModel(resource)));
    }

    @PutMapping("{subscriptionId}")
    public SubscriptionResource updateSubscription(@PathVariable Long subscriptionId, @RequestBody UpdateSubscriptionResource resource){
        return mapper.toResource(subscriptionService.update(subscriptionId, mapper.toModel(resource)));
    }

    @DeleteMapping("{subscriptionId}")
    public ResponseEntity<?> deleteSubscription(@PathVariable Long subscriptionId){
        return subscriptionService.delete(subscriptionId);
    }
}