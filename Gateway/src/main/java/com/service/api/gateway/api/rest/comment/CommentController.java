package com.service.api.gateway.api.rest.comment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.api.gateway.services.comment.CommentService;
import feign.FeignException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/gateway/comments", produces = "application/json")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<Page<Object>> getAllComments(Pageable pageable){
        return ResponseEntity.ok(this.commentService.getAllComments(pageable));
    }

    @PostMapping
    public ResponseEntity<?>create(@RequestBody Object resource) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        try{
            response.put("Comment", this.commentService.createComment(resource));
            response.put("message","Created");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (FeignException.BadRequest ex){
            String responseBody = ex.contentUTF8();
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(responseBody, Map.class);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (FeignException ex){
            response.put("error","Error al hacer una consulta al comment");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
