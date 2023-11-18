package com.service.api.gateway.api.rest.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.api.gateway.services.user.RoleService;
import com.service.api.gateway.services.user.UserService;
import feign.FeignException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/gateway/users", produces = "application/json")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Page<Object>> getAllUsers(Pageable pageable){
        return ResponseEntity.ok(this.userService.getAllUsers(pageable));
    }

    @PostMapping
    public ResponseEntity<?>createUser(@RequestBody Object resource) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        try{
            response.put("User", this.userService.createUser(resource));
            response.put("message","Created");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (FeignException.BadRequest ex){
            String responseBody = ex.contentUTF8();
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(responseBody, Map.class);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (FeignException ex){
            response.put("error","Error al hacer una consulta al user");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
