package com.monaschinas.readr.user.resource;

import lombok.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteResource {
    private Long id;
    private ProfileResource profile;
    private Long bookId;
}
