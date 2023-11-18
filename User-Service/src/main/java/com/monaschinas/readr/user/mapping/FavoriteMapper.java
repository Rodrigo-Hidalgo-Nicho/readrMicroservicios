package com.monaschinas.readr.user.mapping;

import com.monaschinas.readr.user.domain.model.Favorite;
import com.monaschinas.readr.user.resource.CreateFavoriteResource;
import com.monaschinas.readr.user.resource.FavoriteResource;
import com.monaschinas.readr.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class FavoriteMapper implements Serializable {
    @Autowired
    private EnhancedModelMapper mapper;
    public FavoriteResource toResource(Favorite model) {
        return mapper.map(model, FavoriteResource.class);
    }

    public Favorite toModel(CreateFavoriteResource resource) {return mapper.map(resource, Favorite.class);}

    public Page<FavoriteResource> modelListPage(List<Favorite> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, FavoriteResource.class), pageable, modelList.size());
    }
}
