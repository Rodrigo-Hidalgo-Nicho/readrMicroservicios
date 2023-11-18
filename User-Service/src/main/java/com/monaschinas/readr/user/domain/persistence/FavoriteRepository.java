package com.monaschinas.readr.user.domain.persistence;

import com.monaschinas.readr.user.domain.model.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
