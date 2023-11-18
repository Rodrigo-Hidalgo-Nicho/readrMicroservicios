package com.monaschinas.readr.comment.mapping;

import com.monaschinas.readr.comment.domain.model.Comment;
import com.monaschinas.readr.comment.resource.*;
import com.monaschinas.readr.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CommentMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public CommentResource toResource(Comment model) { return mapper.map(model, CommentResource.class); }

    public Page<CommentResource> modelListPage(List<Comment> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, CommentResource.class), pageable, modelList.size());
    }

    public Comment toModel(CreateCommentResource resource) {
        Comment comment = new Comment();

        comment.setCreatedAt(resource.getCreatedAt());
        comment.setBookId(resource.getBookId());
        comment.setProfileId(resource.getProfileId());

        return comment;
    }
    public Comment toModel(UpdateCommentResource resource) { return mapper.map(resource, Comment.class); }
}
