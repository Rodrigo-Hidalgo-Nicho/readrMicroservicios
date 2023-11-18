package com.service.api.gateway.api.rest.user;

import com.service.api.gateway.services.user.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/gateway/roles", produces = "application/json")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<Page<Object>> getAllRoles(Pageable pageable){
        return ResponseEntity.ok(this.roleService.getAllRoles(pageable));
    }
}
