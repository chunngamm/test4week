package com.sparta.homework.repository;

import com.sparta.homework.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.config.web.servlet.PortMapperDsl;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);


}