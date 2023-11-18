package com.service.api.gateway.api.rest.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.api.gateway.services.comment.CommentService;
import com.service.api.gateway.services.user.ProfileService;
import feign.FeignException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/gateway/profiles", produces = "application/json")
public class ProfileController {
    private final ProfileService profileService;
    private final CommentService commentService;

    public ProfileController(ProfileService profileService, CommentService commentService) {
        this.profileService = profileService;
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<Page<Object>> getAllProfiles(Pageable pageable){
        return ResponseEntity.ok(this.profileService.getAllProfiles(pageable));
    }

    @GetMapping("{profileId}/comments")
    public ResponseEntity<?> getBookWithChapters(@PathVariable Long profileId, Pageable pageable) {
        Object profile = profileService.getProfileById(profileId);
        Page<Object> comments = commentService.getCommentsByProfileId(profileId, pageable);

        Map<String, Object> response = new HashMap<>();
        response.put("profile", profile);
        response.put("comments", comments);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?>createProfile(@RequestBody Object resource) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        try{
            response.put("Profile", this.profileService.createProfile(resource));
            response.put("message","Created");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (FeignException.BadRequest ex){
            String responseBody = ex.contentUTF8();
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(responseBody, Map.class);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (FeignException ex){
            response.put("error","Error al hacer una consulta al profile");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
