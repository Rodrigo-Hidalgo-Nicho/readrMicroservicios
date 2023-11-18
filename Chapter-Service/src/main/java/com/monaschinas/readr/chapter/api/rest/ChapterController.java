package com.monaschinas.readr.chapter.api.rest;

import com.monaschinas.readr.chapter.domain.service.ChapterService;
import com.monaschinas.readr.chapter.mapping.ChapterMapper;
import com.monaschinas.readr.chapter.resource.ChapterResource;
import com.monaschinas.readr.chapter.resource.CreateChapterResource;
import com.monaschinas.readr.chapter.resource.UpdateChapterResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/publishing/chapters", produces = "application/json")
public class ChapterController {
    private final ChapterService chapterService;
    private final ChapterMapper mapper;

    public ChapterController(ChapterService chapterService, ChapterMapper mapper) {
        this.chapterService = chapterService;
        this.mapper = mapper;
    }
    @GetMapping
    public Page<ChapterResource> getAllChapters(Pageable pageable) {
        return mapper.modelListPage(chapterService.getAll(), pageable);
    }
    @GetMapping("/by-book/{bookId}")
    public Page<ChapterResource> getChaptersByBookId(@PathVariable Long bookId, Pageable pageable){
        return mapper.modelListPage(chapterService.getByBookId(bookId), pageable);
    }
    @GetMapping ("{chapterId}")
    public ChapterResource getChapterById(@PathVariable Long chapterId) {
        return mapper.toResource(chapterService.getById(chapterId));
    }
    @PostMapping
    public ChapterResource createChapter(@RequestBody CreateChapterResource resource) {
        return mapper.toResource(chapterService.create(mapper.toModel(resource)));
    }
    @PutMapping({"{chapterId}"})
    public ChapterResource updateChapter(@PathVariable Long chapterId
            , @RequestBody UpdateChapterResource resource) {
        return mapper.toResource(chapterService.update(chapterId, mapper.toModel(resource)));
    }
    @DeleteMapping({"{chapterId}"})
    public ResponseEntity<?> deleteChapter(@PathVariable Long chapterId) {
        return chapterService.delete(chapterId);
    }

}
