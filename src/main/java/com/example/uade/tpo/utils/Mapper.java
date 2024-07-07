package com.example.uade.tpo.utils;

import com.example.uade.tpo.dtos.response.ClinicalTrialResponseDto;
import com.example.uade.tpo.dtos.response.UserPostulationInfoResponseDto;
import com.example.uade.tpo.dtos.response.UserResponseDto;
import com.example.uade.tpo.entity.ClinicalTrial;
import com.example.uade.tpo.entity.User;
import com.example.uade.tpo.entity.UserPostulationInfo;

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

    public static UserPostulationInfoResponseDto convertUserPostulationInfoResponseDto(UserPostulationInfo userPostulationInfo){
        UserPostulationInfoResponseDto userPostulationInfoResponseDto = new UserPostulationInfoResponseDto();
        userPostulationInfoResponseDto.setId(userPostulationInfo.getId());
        userPostulationInfoResponseDto.setTrialId(userPostulationInfo.getTrialId());
        userPostulationInfoResponseDto.setFechaNacimiento(userPostulationInfo.getFechaNacimiento());
        userPostulationInfoResponseDto.setGenero(userPostulationInfo.getGenero());
        userPostulationInfoResponseDto.setDireccion(userPostulationInfo.getDireccion());
        userPostulationInfoResponseDto.setTelefono(userPostulationInfo.getTelefono());
        userPostulationInfoResponseDto.setHistorialMedico(userPostulationInfo.getHistorialMedico());
        userPostulationInfoResponseDto.setMedicamentosActuales(userPostulationInfo.getMedicamentosActuales());
        userPostulationInfoResponseDto.setAlergiasConocidas(userPostulationInfo.getAlergiasConocidas());
        userPostulationInfoResponseDto.setCirugiasPrevias(userPostulationInfo.getCirugiasPrevias());
        userPostulationInfoResponseDto.setEnfermedadesCronicas(userPostulationInfo.getEnfermedadesCronicas());
        userPostulationInfoResponseDto.setNivelEducativo(userPostulationInfo.getNivelEducativo());
        userPostulationInfoResponseDto.setOcupacion(userPostulationInfo.getOcupacion());
        userPostulationInfoResponseDto.setHabitosFumar(userPostulationInfo.getHabitosFumar());
        userPostulationInfoResponseDto.setHabitosAlcohol(userPostulationInfo.getHabitosAlcohol());
        userPostulationInfoResponseDto.setHabitosEjercicio(userPostulationInfo.getHabitosEjercicio());
        userPostulationInfoResponseDto.setHabitosAlimenticios(userPostulationInfo.getHabitosAlimenticios());
        userPostulationInfoResponseDto.setConsentimiento(userPostulationInfo.getConsentimiento());
        return userPostulationInfoResponseDto;
    }
}
