package com.example.uade.tpo.repository;

import com.example.uade.tpo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<UserDetails> findByEmail(String email);

    boolean existsByEmail(String email);
}
