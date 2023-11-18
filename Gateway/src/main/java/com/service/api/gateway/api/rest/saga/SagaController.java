package com.service.api.gateway.api.rest.saga;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.api.gateway.services.book.BookService;
import com.service.api.gateway.services.saga.SagaService;
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
@RequestMapping(value = "/api/v1/gateway/sagas", produces = "application/json")
public class SagaController {
    private final SagaService sagaService;
    private final BookService bookService;

    public SagaController(SagaService sagaService, BookService bookService) {
        this.sagaService = sagaService;
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<Page<Object>> getAllSagas(Pageable pageable){
        return ResponseEntity.ok(this.sagaService.getAllSagas(pageable));
    }

    @GetMapping("/{sagaId}/books")
    public ResponseEntity<?> getBookWithChapters(@PathVariable Long sagaId) {
        Object saga = sagaService.getSagaById(sagaId);
        List<Object> books = bookService.getBooksBySagaId(sagaId);

        Map<String, Object> response = new HashMap<>();
        response.put("saga", saga);
        response.put("books", books);

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?>createSaga(@RequestBody Object resource) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        try{
            response.put("Saga", this.sagaService.createSaga(resource));
            response.put("message","Created");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }catch (FeignException.BadRequest ex){
            String responseBody = ex.contentUTF8();
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(responseBody, Map.class);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (FeignException ex){
            response.put("error","Error al hacer una consulta al saga");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
