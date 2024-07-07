package com.example.uade.tpo.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class UserPostulationInfoResponseDto {
    Long id;
    Long trialId;
    LocalDate fechaNacimiento;
    String genero;
    String direccion;
    String telefono;
    String historialMedico;
    String medicamentosActuales;
    String alergiasConocidas;
    String cirugiasPrevias;
    String enfermedadesCronicas;
    String nivelEducativo;
    String ocupacion;
    String habitosFumar;
    String habitosAlcohol;
    String habitosEjercicio;
    String habitosAlimenticios;
    Boolean consentimiento;
}
