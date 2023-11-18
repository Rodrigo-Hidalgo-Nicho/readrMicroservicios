package com.monaschinas.readr.user.domain.persistence;

import com.monaschinas.readr.user.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
