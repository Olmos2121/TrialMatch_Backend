package com.example.uade.tpo.utils;

import com.example.uade.tpo.dtos.response.ClinicalTrialResponseDto;
import com.example.uade.tpo.dtos.response.UserResponseDto;
import com.example.uade.tpo.entity.ClinicalTrial;
import com.example.uade.tpo.entity.User;

public class Mapper {

    public static UserResponseDto convertUserResponseDto(User user){
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setEmail(user.getEmail());
        userResponseDto.setFirstname(user.getFirstname());
        userResponseDto.setLastname(user.getLastname());
        return userResponseDto;
    }

    public static ClinicalTrialResponseDto convertClinicalTrialResponseDto(ClinicalTrial clinicalTrial){
        ClinicalTrialResponseDto clinicalTrialResponseDto = new ClinicalTrialResponseDto();
        clinicalTrialResponseDto.setId(clinicalTrial.getId());
        clinicalTrialResponseDto.setInvestigador(clinicalTrial.getInvestigador());
        clinicalTrialResponseDto.setNombre(clinicalTrial.getName());
        clinicalTrialResponseDto.setDescripcion(clinicalTrial.getDescripcion());
        clinicalTrialResponseDto.setProvincia(clinicalTrial.getProvincia());
        clinicalTrialResponseDto.setEnfermedad(clinicalTrial.getEnfermedad());
        clinicalTrialResponseDto.setEstado(clinicalTrial.getEstado());
        clinicalTrialResponseDto.setGenero(clinicalTrial.getGenero());
        clinicalTrialResponseDto.setRangoEtarioMin(clinicalTrial.getRangoEtarioMin());
        clinicalTrialResponseDto.setRangoEtarioMax(clinicalTrial.getRangoEtarioMax());
        clinicalTrialResponseDto.setFechaInicio(clinicalTrial.getFechaInicio());
        clinicalTrialResponseDto.setFechaFin(clinicalTrial.getFechaFin());
        clinicalTrialResponseDto.setCandidatosSanos(clinicalTrial.getCandidatosSanos());
        clinicalTrialResponseDto.setFase(clinicalTrial.getFase());
        return clinicalTrialResponseDto;
    }
}
