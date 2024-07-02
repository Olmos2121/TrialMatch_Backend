package com.example.uade.tpo.service;

import com.example.uade.tpo.dtos.request.SearchTrialRequestDto;
import com.example.uade.tpo.dtos.request.TrialRequestDto;
import com.example.uade.tpo.dtos.response.ClinicalTrialResponseDto;
import com.example.uade.tpo.entity.ClinicalTrial;
import com.example.uade.tpo.entity.Investigator;
import com.example.uade.tpo.entity.User;
import com.example.uade.tpo.repository.IClinicalTrialRepository;
import com.example.uade.tpo.repository.IInvestigatorRepository;
import com.example.uade.tpo.utils.Mapper;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class ClinicalTrialService {
    @Autowired
    private IClinicalTrialRepository clinicalTrialRepository;
    @Autowired
    private IInvestigatorRepository investigatorRepository;

    public List<ClinicalTrialResponseDto> getAllTrials() {
        List<ClinicalTrial> clinicalTrials = clinicalTrialRepository.findAll();
        List<ClinicalTrialResponseDto> clinicalTrialResponseDtos = new ArrayList<>();
        for (ClinicalTrial clinicalTrial : clinicalTrials) {
            clinicalTrialResponseDtos.add(Mapper.convertClinicalTrialResponseDto(clinicalTrial));
        }
        return clinicalTrialResponseDtos;
    }

    public ClinicalTrialResponseDto createTrial(String email, TrialRequestDto trial){
        Investigator investigator = getInvestigator(email);
        ClinicalTrial clinicalTrial = new ClinicalTrial();
        clinicalTrial.setName(trial.getName());
        clinicalTrial.setInvestigador(investigator.getCompanyName());
        clinicalTrial.setProvincia(trial.getProvincia());
        clinicalTrial.setEnfermedad(trial.getEnfermedad());
        clinicalTrial.setEstado(trial.getStatus());
        clinicalTrial.setGenero(trial.getGenero());
        clinicalTrial.setRangoEtarioMin(trial.getRangoEtarioMin());
        clinicalTrial.setRangoEtarioMax(trial.getRangoEtarioMax());
        clinicalTrial.setFechaInicio(trial.getStartDate());
        clinicalTrial.setFechaFin(trial.getEndDate());
        clinicalTrial.setCandidatosSanos(trial.getCandidatosSanos());
        clinicalTrial.setFase(trial.getFase());
        clinicalTrial.setParticipantes(new HashSet<>());
        clinicalTrial.setCandidatos(new HashSet<>());
        clinicalTrialRepository.save(clinicalTrial);
        return Mapper.convertClinicalTrialResponseDto(clinicalTrial);
    }

    private Investigator getInvestigator(String email) {
        Optional<UserDetails> investigator = investigatorRepository.findByEmail(email);
        return (Investigator) investigator.get();
    }

    public ClinicalTrialResponseDto getTrial(Long id) {
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(id).orElse(null);
        if (clinicalTrial == null) {
            return null;
        }
        return Mapper.convertClinicalTrialResponseDto(clinicalTrial);
    }
}
