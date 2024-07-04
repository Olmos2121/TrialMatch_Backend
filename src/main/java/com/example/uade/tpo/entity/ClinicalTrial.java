package com.example.uade.tpo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
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
    private String name;
    @Column(nullable = false)
    private String enfermedad;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private String investigador;
    @Column(nullable = false)
    private String provincia;
    @Column (nullable = false)
    private String fase;
    @Column (nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private String genero;
    @Column (nullable = false)
    private String candidatosSanos;
    @Column(nullable = false)
    private int rangoEtarioMax;
    @Column (nullable = false)
    private int rangoEtarioMin;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaInicio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(nullable = false)
    private LocalDate fechaFin;
    @ManyToMany
    private Set<User> participantes;
    @ManyToMany
    private Set<User> candidatos;
}
