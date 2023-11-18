package com.service.api.gateway.services.comment;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        value = "comment",
        path = "api/v1/publishing/comments",
        url = "http://localhost:8083"
)
public interface CommentService {
    @GetMapping
    Page<Object> getAllComments(Pageable pageable);

    @PostMapping
    Object createComment(@RequestBody Object resource);

    @GetMapping("by-profile/{profileId}")
    Page<Object> getCommentsByProfileId(@PathVariable("profileId") Long profileId, Pageable pageable);
}
