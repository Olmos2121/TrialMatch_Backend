package com.example.uade.tpo.repository;

import com.example.uade.tpo.entity.Investigator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IInvestigatorRepository extends JpaRepository<Investigator, Long> {
    boolean existsByEmail(String email);

    Optional<UserDetails> findByEmail(String email);

}
