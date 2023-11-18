package com.service.api.gateway.api.rest.book;

import com.service.api.gateway.services.book.BookStatusService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/v1/gateway/book-statuses", produces = "application/json")
public class BookStatusController {

    private final BookStatusService bookStatusService;


    public BookStatusController(BookStatusService bookStatusService) { this.bookStatusService = bookStatusService; }

    @GetMapping
    public ResponseEntity<Page<Object>>getAllBookStatuses(Pageable pageable){
        return ResponseEntity.ok(this.bookStatusService.getAllBookStatuses(pageable));
    }
}
