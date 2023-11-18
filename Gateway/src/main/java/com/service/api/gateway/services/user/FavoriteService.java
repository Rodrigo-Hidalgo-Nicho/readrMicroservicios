package com.service.api.gateway.services.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "favorite",
        path = "api/v1/profiling/favorites",
        url = "http://localhost:8086"
)
public interface FavoriteService {
    @GetMapping
    Page<Object> getAllFavorites(Pageable pageable);

    @PostMapping
    Object createFavorite(@RequestBody Object resource);
}
