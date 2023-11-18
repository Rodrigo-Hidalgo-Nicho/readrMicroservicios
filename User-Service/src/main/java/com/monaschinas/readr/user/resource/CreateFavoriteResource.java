package com.monaschinas.readr.user.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CreateFavoriteResource {
    @NotNull
    private Long profileId;
    @NotNull
    private Long bookId;
}
