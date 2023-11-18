package com.service.api.gateway.api.rest.payment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.api.gateway.services.payment.PlanService;
import feign.FeignException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/gateway/plans", produces = "application/json")
public class PlanController {
    private final PlanService planService;

    public PlanController(PlanService planService) {
        this.planService = planService;
    }

    @GetMapping
    public ResponseEntity<Page<Object>> getAllPlans(Pageable pageable){
        return ResponseEntity.ok(this.planService.getAllPlans(pageable));
    }

    @PostMapping
    public ResponseEntity<?>createPlan(@RequestBody Object resource) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        try{
            response.put("Plan", this.planService.createPlan(resource));
            response.put("message","Created");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (FeignException.BadRequest ex){
            String responseBody = ex.contentUTF8();
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(responseBody, Map.class);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (FeignException ex){
            response.put("error","Error al hacer una consulta al plan");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
