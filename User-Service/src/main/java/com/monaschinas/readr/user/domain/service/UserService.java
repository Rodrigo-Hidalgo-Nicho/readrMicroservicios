package com.monaschinas.readr.user.domain.service;

import com.monaschinas.readr.user.domain.model.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(Long userId);
    User create(User user);
    User update(Long userId, User user);
    ResponseEntity<?> delete(Long userId);
}
