package com.service.api.gateway.services.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        value = "role",
        path = "api/v1/profiling/roles",
        url = "http://localhost:8086"
)
public interface RoleService {
    @GetMapping
    Page<Object> getAllRoles(Pageable pageable);
}
