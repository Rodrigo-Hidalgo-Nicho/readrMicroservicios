package com.monaschinas.readr.comment.resource;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCommentResource {
    private Long id;

    @NotNull
    private Date createdAt;

    @NotNull
    private Long bookId;

    @NotNull
    private Long profileId;
}
