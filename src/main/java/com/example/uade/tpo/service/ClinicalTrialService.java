package com.example.uade.tpo.service;

import com.example.uade.tpo.dtos.request.EditTrialRequestDto;
import com.example.uade.tpo.dtos.request.SearchTrialRequestDto;
import com.example.uade.tpo.dtos.request.TrialRequestDto;
import com.example.uade.tpo.dtos.response.ClinicalTrialResponseDto;
import com.example.uade.tpo.dtos.response.UserResponseDto;
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

import javax.swing.text.html.Option;
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
        clinicalTrial.setDescripcion(trial.getDescripcion());
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

    public List<ClinicalTrialResponseDto> getTrialsByEmail(String email) {
        Optional<UserDetails> investigator = investigatorRepository.findByEmail(email);
        if (investigator.isEmpty()) {
            return null;
        }
        Investigator inv = (Investigator) investigator.get();
        List<ClinicalTrial> clinicalTrials = clinicalTrialRepository.findByInvestigador(inv.getCompanyName());
        List<ClinicalTrialResponseDto> clinicalTrialResponseDtos = new ArrayList<>();
        for (ClinicalTrial clinicalTrial : clinicalTrials) {
            clinicalTrialResponseDtos.add(Mapper.convertClinicalTrialResponseDto(clinicalTrial));
        }
        return clinicalTrialResponseDtos;
    }

    public Boolean deleteTrial(Long id) {
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(id).orElse(null);
        if (clinicalTrial == null) {
            return false;
        }
        clinicalTrialRepository.delete(clinicalTrial);
        return true;
    }

    public ClinicalTrialResponseDto editTrial(EditTrialRequestDto editTrialRequestDto) {
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(editTrialRequestDto.getId()).orElse(null);
        if (clinicalTrial == null) {
            return null;
        }
        clinicalTrial.setEstado(editTrialRequestDto.getStatus());
        clinicalTrial.setFase(editTrialRequestDto.getFase());
        clinicalTrialRepository.save(clinicalTrial);
        return Mapper.convertClinicalTrialResponseDto(clinicalTrial);
    }

    public List<UserResponseDto> getCandidates(Long id) {
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(id).orElse(null);
        if (clinicalTrial == null) {
            return null;
        }
        Set<User> candidatos = clinicalTrial.getCandidatos();
        List<UserResponseDto> UserResponseDtos = new ArrayList<>();
        for (User candidato : candidatos) {
            UserResponseDtos.add(Mapper.convertUserResponseDto(candidato));
        }
        return UserResponseDtos;
    }

    public Boolean acceptApply(Long id, String email) {
        Optional<UserDetails> user = investigatorRepository.findByEmail(email);
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(id).orElse(null);
        if (clinicalTrial == null) {
            return false;
        }

        User candidato = (User) user.get();

        clinicalTrial.getParticipantes().add(candidato);
        clinicalTrialRepository.save(clinicalTrial);
        return true;
    }

    public Boolean rejectApply(Long id, String email) {
        Optional<UserDetails> user = investigatorRepository.findByEmail(email);
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(id).orElse(null);
        if (clinicalTrial == null) {
            return false;
        }

        User candidato = (User) user.get();

        clinicalTrial.getCandidatos().remove(candidato);
        clinicalTrialRepository.save(clinicalTrial);
        return true;
    }

    public List<UserResponseDto> getParticipants(Long id) {
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(id).orElse(null);
        if (clinicalTrial == null) {
            return null;
        }
        Set<User> participantes = clinicalTrial.getParticipantes();
        List<UserResponseDto> clinicalTrialResponseDtos = new ArrayList<>();
        for (User participante : participantes) {
            clinicalTrialResponseDtos.add(Mapper.convertUserResponseDto(participante));
        }
        return clinicalTrialResponseDtos;
    }

    public Boolean removeParticipant(Long id, String email) {
        Optional<UserDetails> user = investigatorRepository.findByEmail(email);
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(id).orElse(null);
        if (clinicalTrial == null) {
            return false;
        }

        User participante = (User) user.get();

        clinicalTrial.getParticipantes().remove(participante);
        clinicalTrialRepository.save(clinicalTrial);
        return true;
    }
}
