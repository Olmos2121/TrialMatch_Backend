package com.example.uade.tpo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "clinical_trials")
public class ClinicalTrial {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliniTri_seq")
    @SequenceGenerator(name = "cliniTri_seq", sequenceName = "cliniTri_seq", allocationSize = 1)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String status;
    @ManyToOne
    @JoinColumn(name = "laboratory_id")
    private User laboratory;
    @ManyToMany
    private Set<User> participants;
    @ManyToMany
    private Set<User> candidates;
}
