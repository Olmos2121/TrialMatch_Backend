package com.example.uade.tpo.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@NoArgsConstructor
public class SearchTrialRequestDto {
    String provincia;
    String condicionEnfermedad;
    String estado;
}
