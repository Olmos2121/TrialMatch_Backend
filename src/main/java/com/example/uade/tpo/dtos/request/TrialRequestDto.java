package com.example.uade.tpo.dtos.request;

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
public class TrialRequestDto {
    String name;
    String enfermedad;
    String provincia;
    String status;
    String fase;
    String genero;
    int rangoEtarioMax;
    int rangoEtarioMin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate endDate;
    String candidatosSanos;
}
