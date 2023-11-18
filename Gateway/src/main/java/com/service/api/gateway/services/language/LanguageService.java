package com.service.api.gateway.services.language;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        value = "language",
        path = "api/v1/publishing/languages",
        url = "http://localhost:8087"
)
public interface LanguageService {
    @GetMapping
    Page<Object> getAllLanguages(Pageable pageable);

}
