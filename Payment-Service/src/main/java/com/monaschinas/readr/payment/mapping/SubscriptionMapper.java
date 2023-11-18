package com.monaschinas.readr.payment.mapping;

import com.monaschinas.readr.payment.domain.model.Subscription;
import com.monaschinas.readr.payment.domain.service.PlanService;
import com.monaschinas.readr.payment.resource.CreateSubscriptionResource;
import com.monaschinas.readr.payment.resource.SubscriptionResource;
import com.monaschinas.readr.payment.resource.UpdateSubscriptionResource;
import com.monaschinas.readr.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SubscriptionMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    @Autowired
    private PlanService planService;

    public SubscriptionResource toResource(Subscription model) { return mapper.map(model, SubscriptionResource.class); }

    public Page<SubscriptionResource> modelListPage(List<Subscription> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, SubscriptionResource.class), pageable, modelList.size());
    }

    public Subscription toModel(CreateSubscriptionResource resource) {
        Subscription subscription = new Subscription();
        subscription.setStartedAt(resource.getStartedAt());
        subscription.setFinishedAt(resource.getFinishedAt());
        subscription.setUserId(resource.getUserId());

        // Asumiendo que el CreateSubscriptionResource tiene un planId para establecer la relación con Plan
        subscription.setPlan(planService.getById(resource.getPlanId()));

        // Continúa mapeando cualquier otra propiedad si es necesario...

        return subscription;
    }
    public Subscription toModel(UpdateSubscriptionResource resource) { return mapper.map(resource, Subscription.class); }
}
