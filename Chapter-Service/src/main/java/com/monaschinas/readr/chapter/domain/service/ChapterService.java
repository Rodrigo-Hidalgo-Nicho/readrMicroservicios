package com.monaschinas.readr.chapter.domain.service;

import com.monaschinas.readr.chapter.domain.model.Chapter;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ChapterService {
    List<Chapter> getAll();
    Chapter getById(Long chapterId);
    List<Chapter> getByBookId(Long bookId);
    Chapter create(Chapter chapter);
    Chapter update(Long chapterId, Chapter chapter);
    ResponseEntity<?> delete(Long chapterId);
}
