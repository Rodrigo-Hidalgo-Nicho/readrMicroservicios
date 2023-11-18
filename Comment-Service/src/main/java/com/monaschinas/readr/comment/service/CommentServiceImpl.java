package com.monaschinas.readr.comment.service;

import com.monaschinas.readr.comment.domain.model.Comment;
import com.monaschinas.readr.comment.domain.persistence.CommentRepository;
import com.monaschinas.readr.comment.domain.service.CommentService;
import com.monaschinas.readr.shared.exception.ResourceNotFoundException;
import com.monaschinas.readr.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CommentServiceImpl implements CommentService {
    private static final String ENTITY = "Comment";

    private final CommentRepository commentRepository;

    private final Validator validator;

    public CommentServiceImpl(CommentRepository commentRepository, Validator validator) {
        this.commentRepository = commentRepository;
        this.validator = validator;
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getByProfileId(Long profileId) {
        return commentRepository.findByProfileId(profileId);
    }
    @Override
    public Comment getById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, commentId));
    }

    @Override
    public Comment create(Comment comment) {
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        // Considerando que el nombre es único en BookStatus, pero en Comment no se menciona ningún campo único,
        // así que omito la lógica de chequeo de duplicidad.

        return commentRepository.save(comment);
    }

    @Override
    public Comment update(Long commentId, Comment comment) {
        Set<ConstraintViolation<Comment>> violations = validator.validate(comment);

        if (!violations.isEmpty()) {
            throw new ResourceValidationException(ENTITY, violations);
        }

        // Nuevamente, omito la lógica de chequeo de duplicidad ya que no se menciona ningún campo único.

        return commentRepository.findById(commentId)
                .map(commentToUpdate -> commentRepository.save(commentToUpdate
                        // Aquí se añade la lógica para actualizar los campos de `Comment`.
                        // En este ejemplo, estoy actualizando la fecha de creación, el ID del libro y el ID del perfil.
                        // Puedes añadir más campos según sea necesario.
                        .withCreatedAt(comment.getCreatedAt())
                        .withBookId(comment.getBookId())
                        .withProfileId(comment.getProfileId())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, commentId));
    }

    @Override
    public ResponseEntity<?> delete(Long commentId) {
        return commentRepository.findById(commentId)
                .map(comment -> {
                    commentRepository.delete(comment);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, commentId));
    }
}
