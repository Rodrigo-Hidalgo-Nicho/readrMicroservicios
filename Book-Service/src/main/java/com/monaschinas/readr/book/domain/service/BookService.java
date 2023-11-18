package com.monaschinas.readr.book.domain.service;

import com.monaschinas.readr.book.domain.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookService {
    List<Book> getAll();
    Page<Book> getAll(Pageable pageable);
    List<Book> getBySagaId(Long sagaId);
    Book getById(Long bookId);
    Book create(Book book);
    Book update(Long bookId, Book book);
    ResponseEntity<?> delete(Long bookId);
}
