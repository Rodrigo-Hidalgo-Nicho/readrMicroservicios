package com.service.api.gateway.services.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        value = "profile",
        path = "api/v1/profiling/profiles",
        url = "http://localhost:8086"
)
public interface ProfileService {
    @GetMapping
    Page<Object> getAllProfiles(Pageable pageable);

    @PostMapping
    Object createProfile(@RequestBody Object resource);

    @GetMapping("{profileId}")
    Object getProfileById(@PathVariable("profileId") Long profileId);
}
