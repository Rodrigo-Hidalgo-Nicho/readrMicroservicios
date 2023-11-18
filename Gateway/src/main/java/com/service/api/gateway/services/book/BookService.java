package com.service.api.gateway.services.book;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@FeignClient(
        value = "book",
        path = "api/v1/publishing/books"
        //url = "http://localhost:8081"
)
public interface BookService {
    @GetMapping
    Page<Object> getAllBooks(Pageable pageable);

    @PostMapping
    Object createBook(@RequestBody Object resource);

    @GetMapping("/{bookId}")
    Object getBookById(@PathVariable("bookId") Long bookId);

    @GetMapping("/by-saga/{sagaId}")
    List<Object> getBooksBySagaId(@PathVariable("sagaId") Long sagaId);
}
