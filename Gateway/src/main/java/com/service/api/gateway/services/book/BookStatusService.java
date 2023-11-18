package com.service.api.gateway.services.book;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        value = "book-status",
        path = "api/v1/publishing/book-statuses"
        //url = "http://localhost:8081"
)
public interface BookStatusService {

    @GetMapping
    Page<Object> getAllBookStatuses(Pageable pageable);
}
