package com.example.uade.tpo.repository;

import com.example.uade.tpo.entity.ClinicalTrial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClinicalTrialRepository extends JpaRepository<ClinicalTrial, Long> {
}
