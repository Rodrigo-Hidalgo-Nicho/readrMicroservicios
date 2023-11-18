package com.service.api.gateway.services.payment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "subscription",
        path = "api/v1/publishing/subscriptions",
        url = "http://localhost:8084"
)
public interface SubscriptionService {
    @GetMapping
    Page<Object> getAllSubscriptions(Pageable pageable);

    @PostMapping
    Object createSubscription(@RequestBody Object resource);
}
