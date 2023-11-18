package com.monaschinas.readr.payment.mapping;

import com.monaschinas.readr.payment.domain.model.Plan;
import com.monaschinas.readr.payment.resource.CreatePlanResource;
import com.monaschinas.readr.payment.resource.PlanResource;
import com.monaschinas.readr.payment.resource.UpdatePlanResource;
import com.monaschinas.readr.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PlanMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public PlanResource toResource(Plan model){ return mapper.map(model, PlanResource.class); }

    public Plan toModel(CreatePlanResource resource){ return mapper.map(resource, Plan.class); }

    public Plan toModel(UpdatePlanResource resource){ return mapper.map(resource, Plan.class); }

    public Page<PlanResource> modelListPage(List<Plan> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, PlanResource.class), pageable, modelList.size());
    }
}
