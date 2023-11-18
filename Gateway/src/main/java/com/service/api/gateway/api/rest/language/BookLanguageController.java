package com.service.api.gateway.api.rest.language;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.api.gateway.services.language.BookLanguageService;
import feign.FeignException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "api/v1/gateway/book-languages", produces = "application/json")
public class BookLanguageController {
    private final BookLanguageService bookLanguageService;

    public BookLanguageController(BookLanguageService bookLanguageService) {
        this.bookLanguageService = bookLanguageService;
    }

    @GetMapping
    public ResponseEntity<Page<Object>> getAllBookLanguages(Pageable pageable){
        return ResponseEntity.ok(this.bookLanguageService.getAllBookLanguages(pageable));
    }

    @PostMapping
    public ResponseEntity<?>createBookLanguage(@RequestBody Object resource) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        try{
            response.put("Book Language", this.bookLanguageService.createBookLanguage(resource));
            response.put("message","Created");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (FeignException.BadRequest ex){
            String responseBody = ex.contentUTF8();
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(responseBody, Map.class);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (FeignException ex){
            response.put("error","Error al hacer una consulta al book language");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
