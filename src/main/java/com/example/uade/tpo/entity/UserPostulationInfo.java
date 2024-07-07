package com.example.uade.tpo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "postulation_info")
@EqualsAndHashCode(exclude = "user")
public class UserPostulationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_postulation_info_seq")
    @SequenceGenerator(name = "user_postulation_info_seq", sequenceName = "user_postulation_info_seq", allocationSize = 1)
    private Long id;
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column (nullable = false)
    private Long trialId;
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaNacimiento;
    @Column(nullable = false)
    private String genero;
    @Column(nullable = false)
    private String direccion;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String historialMedico;
    @Column(nullable = false)
    private String medicamentosActuales;
    @Column(nullable = false)
    private String alergiasConocidas;
    @Column(nullable = false)
    private String cirugiasPrevias;
    @Column(nullable = false)
    private String enfermedadesCronicas;
    @Column(nullable = false)
    private String nivelEducativo;
    @Column(nullable = false)
    private String ocupacion;
    @Column(nullable = false)
    private String habitosFumar;
    @Column(nullable = false)
    private String habitosAlcohol;
    @Column(nullable = false)
    private String habitosEjercicio;
    @Column(nullable = false)
    private String habitosAlimenticios;
    @Column(nullable = false)
    private Boolean consentimiento;
}