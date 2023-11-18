package com.monaschinas.readr.comment.resource;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
public class CommentResource {
    private Long id;
    private Date createdAt;
    private Long bookId;
    private Long profileId;
}
