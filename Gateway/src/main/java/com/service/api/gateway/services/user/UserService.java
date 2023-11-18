package com.service.api.gateway.services.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "user",
        path = "api/v1/profiling/users",
        url = "http://localhost:8086"
)
public interface UserService {
    @GetMapping
    Page<Object> getAllUsers(Pageable pageable);

    @PostMapping
    Object createUser(@RequestBody Object resource);
}
