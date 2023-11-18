package com.monaschinas.readr.chapter.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class ChapterResource {
    private Long id;
    private String title;
    private String documentContentUrl;
    private Long bookId;
}
