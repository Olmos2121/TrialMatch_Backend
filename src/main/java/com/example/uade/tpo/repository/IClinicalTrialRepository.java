package com.example.uade.tpo.repository;

import com.example.uade.tpo.entity.ClinicalTrial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClinicalTrialRepository extends JpaRepository<ClinicalTrial, Long> {
}
