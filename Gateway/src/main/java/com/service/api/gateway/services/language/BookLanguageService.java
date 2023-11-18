package com.service.api.gateway.services.language;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        value = "book-language",
        path = "api/v1/publishing/book-languages",
        url = "http://localhost:8087"
)
public interface BookLanguageService {
    @GetMapping
    Page<Object> getAllBookLanguages(Pageable pageable);

    @PostMapping
    Object createBookLanguage(@RequestBody Object resource);
}
