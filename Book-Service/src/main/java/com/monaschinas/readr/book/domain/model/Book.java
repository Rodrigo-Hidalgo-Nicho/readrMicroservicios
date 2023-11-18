package com.monaschinas.readr.book.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@With
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 64)
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 128)
    private String synopsis;

    @NotNull
    private Date publishedAt;

    //Relationships
    @NotNull
    private Long sagaId;

    @NotNull
    private Long authorId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "book_status_id", nullable = false)
    @JsonIgnore
    private BookStatus bookStatus;
}