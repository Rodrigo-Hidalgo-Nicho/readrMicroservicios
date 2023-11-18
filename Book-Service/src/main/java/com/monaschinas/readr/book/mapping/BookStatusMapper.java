package com.monaschinas.readr.book.mapping;

import com.monaschinas.readr.book.domain.model.BookStatus;
import com.monaschinas.readr.book.resource.BookStatusResource;
import com.monaschinas.readr.book.resource.CreateBookStatusResource;
import com.monaschinas.readr.book.resource.UpdateBookStatusResource;
import com.monaschinas.readr.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class BookStatusMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public BookStatusResource toResource(BookStatus model){ return mapper.map(model, BookStatusResource.class); }

    public Page<BookStatusResource> modelListPage(List<BookStatus> modelList, Pageable pageable){
        return new PageImpl<>(mapper.mapList(modelList, BookStatusResource.class), pageable, modelList.size());
    }

    public BookStatus toModel(CreateBookStatusResource resource){ return mapper.map(resource, BookStatus.class); }

    public BookStatus toModel(UpdateBookStatusResource resource){ return mapper.map(resource, BookStatus.class); }
}
