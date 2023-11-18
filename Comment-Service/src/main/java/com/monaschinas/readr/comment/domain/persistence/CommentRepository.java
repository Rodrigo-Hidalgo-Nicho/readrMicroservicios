package com.monaschinas.readr.comment.domain.persistence;

import com.monaschinas.readr.comment.domain.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProfileId(Long profileId);
}
