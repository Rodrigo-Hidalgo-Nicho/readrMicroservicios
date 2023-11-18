package com.service.api.gateway.services.chapter;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        value = "chapter",
        path = "api/v1/publishing/chapters",
        url = "http://localhost:8082"
)
public interface ChapterService {
    @GetMapping
    Page<Object> getAllChapters(Pageable pageable);

    @PostMapping
    Object createChapter(@RequestBody Object resource);

    @GetMapping("/by-book/{bookId}")
    List<Object> getChaptersByBookId(@PathVariable("bookId") Long bookId);
}
