package com.service.api.gateway.api.rest.book;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.api.gateway.services.book.BookService;
import com.service.api.gateway.services.chapter.ChapterService;
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
@RequestMapping(value = "/api/v1/gateway/books", produces = "application/json")
public class BookController {

    private final BookService bookService;
    private final ChapterService chapterService;

    public BookController(BookService bookService, ChapterService chapterService) {
        this.bookService = bookService;
        this.chapterService = chapterService;
    }


    @GetMapping
    public ResponseEntity<Page<Object>>getAllBooks(Pageable pageable){
        return ResponseEntity.ok(this.bookService.getAllBooks(pageable));
    }
    @GetMapping("/{bookId}/chapters")
    public ResponseEntity<?> getBookWithChapters(@PathVariable Long bookId) {
        Object book = bookService.getBookById(bookId);
        List<Object> chapters = chapterService.getChaptersByBookId(bookId);

        Map<String, Object> response = new HashMap<>();
        response.put("books", book);
        response.put("chapters", chapters);

        return ResponseEntity.ok(response);
    }
    @PostMapping
    public ResponseEntity<?>createBook(@RequestBody Object resource) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        try{
            response.put("Book", this.bookService.createBook(resource));
            response.put("message","Created");
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }catch (FeignException.BadRequest ex){
            String responseBody = ex.contentUTF8();
            ObjectMapper mapper = new ObjectMapper();
            response = mapper.readValue(responseBody, Map.class);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }catch (FeignException ex){
            response.put("error","Error al hacer una consulta al book");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
