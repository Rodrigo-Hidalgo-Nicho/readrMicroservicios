package com.monaschinas.readr.comment.domain.service;

import com.monaschinas.readr.comment.domain.model.Comment;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentService {
    List<Comment> getAll();
    Comment getById(Long commentId);
    List<Comment> getByProfileId(Long profileId);
    Comment create(Comment comment);
    Comment update(Long commentId, Comment comment);
    ResponseEntity<?> delete(Long commentId);
}
