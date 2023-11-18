package com.monaschinas.readr.book.mapping;

import com.monaschinas.readr.book.domain.model.Genre;
import com.monaschinas.readr.book.resource.CreateGenreResource;
import com.monaschinas.readr.book.resource.GenreResource;
import com.monaschinas.readr.book.resource.UpdateGenreResource;
import com.monaschinas.readr.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class GenreMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public GenreResource toResource(Genre model) { return mapper.map(model, GenreResource.class); }

    public Page<GenreResource> modelListPage(List<Genre> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, GenreResource.class), pageable, modelList.size());
    }

    public Genre toModel(CreateGenreResource resource) { return mapper.map(resource, Genre.class); }
    public Genre toModel(UpdateGenreResource resource) { return mapper.map(resource, Genre.class); }
}
