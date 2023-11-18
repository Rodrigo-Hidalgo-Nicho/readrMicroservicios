package com.service.api.gateway.api.rest.chapter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.api.gateway.services.chapter.ChapterService;
import feign.FeignException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/gateway/chapters", produces = "application/json")
public class ChapterController {
    private final ChapterService chapterService;

    public ChapterController(ChapterService chapterService) {
        this.chapterService = chapterService;
    }

    @GetMapping
    public ResponseEntity<Page<Object>> getAllChapters(Pageable pageable){
        return ResponseEntity.ok(this.chapterService.getAllChapters(pageable));
    }

    @PostMapping
    public ResponseEntity<?>createChapter(@RequestBody Object resource) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        try{
            response.put("Chapter", this.chapterService.createChapter(resource));
            response.put("message","Created");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (FeignException.BadRequest ex){
            String responseBody = ex.contentUTF8();
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(responseBody, Map.class);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (FeignException ex){
            response.put("error","Error al hacer una consulta al chapter");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
