package com.monaschinas.readr.chapter.domain.persistence;

import com.monaschinas.readr.chapter.domain.model.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Optional<Chapter> findByTitle(String title);
    List<Chapter> findByBookId(Long bookId);
}
