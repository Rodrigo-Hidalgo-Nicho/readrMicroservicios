package com.monaschinas.readr.chapter.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateChapterResource {
    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @NotBlank
    private String documentContentUrl;

    @NotNull
    private Long bookId;
}
