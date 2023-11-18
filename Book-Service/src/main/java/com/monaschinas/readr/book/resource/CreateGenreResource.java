package com.monaschinas.readr.book.resource;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateGenreResource {
    @NotNull
    @NotBlank
    @Size(max = 24)
    private String name;
}
