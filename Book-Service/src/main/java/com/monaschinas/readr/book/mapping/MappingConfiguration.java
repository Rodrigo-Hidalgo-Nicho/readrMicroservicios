package com.monaschinas.readr.book.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("bookMicroServiceMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public GenreMapper genreMapper(){ return new GenreMapper(); }

    @Bean
    public BookStatusMapper bookStatusMapper() { return new BookStatusMapper(); }

    @Bean
    public BookMapper bookMapper() { return new BookMapper(); }
}
