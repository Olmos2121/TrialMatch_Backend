package com.example.uade.tpo.dtos.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ClinicalTrialResponseDto {
    Long id;
    String nombre;
    String investigador;
    String provincia;
    String enfermedad;
    String descripcion;
    String estado;
    String genero;
    int rangoEtarioMax;
    int rangoEtarioMin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaInicio;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate fechaFin;
    String candidatosSanos;
    String fase;
}
