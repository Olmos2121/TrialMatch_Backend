package com.example.uade.tpo.service;

import com.example.uade.tpo.dtos.request.EditTrialRequestDto;
import com.example.uade.tpo.dtos.request.SearchTrialRequestDto;
import com.example.uade.tpo.dtos.request.TrialRequestDto;
import com.example.uade.tpo.dtos.response.ClinicalTrialResponseDto;
import com.example.uade.tpo.dtos.response.PostulateResponseDto;
import com.example.uade.tpo.dtos.response.UserResponseDto;
import com.example.uade.tpo.entity.ClinicalTrial;
import com.example.uade.tpo.entity.Investigator;
import com.example.uade.tpo.entity.User;
import com.example.uade.tpo.repository.IClinicalTrialRepository;
import com.example.uade.tpo.repository.IInvestigatorRepository;
import com.example.uade.tpo.repository.IUserRepository;
import com.example.uade.tpo.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClinicalTrialService {
    @Autowired
    private IClinicalTrialRepository clinicalTrialRepository;
    @Autowired
    private IInvestigatorRepository investigatorRepository;
    @Autowired
    private IUserRepository userRepository;

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

    public PostulateResponseDto getTrial(Long id, String email) {
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(id).orElse(null);
        Optional<UserDetails> user = userRepository.findByEmail(email);
        if (clinicalTrial == null) {
            return null;
        }
        if (user.isEmpty()){
            return null;
        }

        User postulante = (User) user.get();

        if(clinicalTrial.getCandidatos().contains(postulante) || clinicalTrial.getParticipantes().contains(postulante)){
            return new PostulateResponseDto(Mapper.convertClinicalTrialResponseDto(clinicalTrial), true);
        } else {
            return new PostulateResponseDto(Mapper.convertClinicalTrialResponseDto(clinicalTrial), false);
        }
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

    public Boolean acceptApply(Long trialId, Long userId) {
        Optional<User> candidato = userRepository.findById(userId);
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(trialId).orElse(null);
        if (clinicalTrial == null) {
            return false;
        }
        if(candidato.isEmpty()){
            return false;
        }
        clinicalTrial.getCandidatos().remove(candidato.get());
        clinicalTrial.getParticipantes().add(candidato.get());

        clinicalTrialRepository.save(clinicalTrial);
        return true;
    }

    public Boolean rejectApply(Long trialId, Long userId) {
        Optional<User> candidato = userRepository.findById(userId);
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(trialId).orElse(null);
        if (clinicalTrial == null) {
            return false;
        }
        if(candidato.isEmpty()){
            return false;
        }
        clinicalTrial.getCandidatos().remove(candidato.get());

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

    public Boolean removeParticipant(Long trialId, Long userId) {
        Optional<User> participante = userRepository.findById(userId);
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(trialId).orElse(null);
        if (clinicalTrial == null) {
            return false;
        }
        if(participante.isEmpty()){
            return false;
        }
        clinicalTrial.getParticipantes().remove(participante.get());

        clinicalTrialRepository.save(clinicalTrial);
        return true;
    }
}
