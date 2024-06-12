package com.example.uade.tpo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "clinical_trials")
public class ClinicalTrial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;
    @ManyToOne
    @JoinColumn(name = "laboratory_id")
    private User laboratory;
    @ManyToMany
    private Set<User> participants;
    @ManyToMany
    private Set<User> candidates;
}
