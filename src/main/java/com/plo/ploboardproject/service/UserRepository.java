package com.plo.ploboardproject.service;

import com.plo.ploboardproject.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Optional<User> findByKakaoId(Long kakaoId);

    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
