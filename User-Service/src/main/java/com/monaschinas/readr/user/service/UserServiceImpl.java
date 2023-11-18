package com.monaschinas.readr.user.service;

import com.monaschinas.readr.user.domain.model.User;
import com.monaschinas.readr.user.domain.persistence.UserRepository;
import com.monaschinas.readr.user.domain.service.UserService;
import com.monaschinas.readr.shared.exception.ResourceNotFoundException;
import com.monaschinas.readr.shared.exception.ResourceValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final String ENTITY = "User";
    private final UserRepository userRepository;
    private final Validator validator;

    public UserServiceImpl(UserRepository userRepository, Validator validator) {
        this.userRepository = userRepository;
        this.validator = validator;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public User create(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.save(user);
    }

    @Override
    public User update(Long userId, User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return userRepository.findById(userId)
                .map(userToUpdate -> userRepository.save(userToUpdate
                                .withFirstName(user.getFirstName())
                                .withLastName(user.getLastName())
                                .withEmail(user.getEmail())
                                .withPassword(user.getPassword())
                        )
                )
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }

    @Override
    public ResponseEntity<?> delete(Long userId) {
        return userRepository.findById(userId)
                .map(user -> {
                    userRepository.delete(user);
                    return ResponseEntity.ok().build();
                })
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, userId));
    }
}
