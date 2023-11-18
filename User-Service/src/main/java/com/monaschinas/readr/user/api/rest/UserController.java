package com.monaschinas.readr.user.api.rest;

import com.monaschinas.readr.user.domain.service.UserService;
import com.monaschinas.readr.user.mapping.UserMapper;
import com.monaschinas.readr.user.resource.UserResource;
import com.monaschinas.readr.user.resource.CreateUserResource;
import com.monaschinas.readr.user.resource.UpdateUserResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/profiling/users", produces = "application/json")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping
    public Page<UserResource> getAllUsers(Pageable pageable) {
        return mapper.modelListPage(userService.getAll(), pageable);
    }

    @GetMapping("{userId}")
    public UserResource getUserById(@PathVariable Long userId) {
        return mapper.toResource(userService.getById(userId));
    }

    @PostMapping
    public UserResource createUser(@RequestBody CreateUserResource resource) {
        return mapper.toResource(userService.create(mapper.toModel(resource)));
    }

    @PutMapping("{userId}")
    public UserResource updateUser(@PathVariable Long userId, @RequestBody UpdateUserResource resource) {
        return mapper.toResource(userService.update(userId, mapper.toModel(resource)));
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Long userId) {
        return userService.delete(userId);
    }
}
