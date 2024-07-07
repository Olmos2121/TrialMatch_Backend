package com.example.uade.tpo.service;

import com.example.uade.tpo.dtos.request.UserPostulationInfoRequestDto;
import com.example.uade.tpo.entity.ClinicalTrial;
import com.example.uade.tpo.entity.User;
import com.example.uade.tpo.entity.UserPostulationInfo;
import com.example.uade.tpo.repository.IClinicalTrialRepository;
import com.example.uade.tpo.repository.IUserPostulationInfoRespository;
import com.example.uade.tpo.repository.IUserRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Service
public class ApplicationService {

    @Autowired
    private IClinicalTrialRepository clinicalTrialRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserPostulationInfoRespository userPostulationInfoRepository;

    public void applyToTrial(Long trialId, String userEmail) {
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(trialId).orElseThrow(()
                -> new OpenApiResourceNotFoundException("Clinical trial not found"));
        Optional<UserDetails> userDetails = userRepository.findByEmail(userEmail);
        clinicalTrial.getCandidatos().add((User) userDetails.get());
        clinicalTrialRepository.save(clinicalTrial);
    }

    public void saveUserInfo(Long trialId, String userEmail, UserPostulationInfoRequestDto userPostulationInfoRequestDto) {
        Optional<UserDetails> userDetails = userRepository.findByEmail(userEmail);
        if(userDetails.isEmpty()) {
            throw new OpenApiResourceNotFoundException("User not found");
        }
        User user = (User) userDetails.get();
        UserPostulationInfo userPostulationInfo = new UserPostulationInfo();

        userPostulationInfo.setTrialId(trialId);
        userPostulationInfo.setFechaNacimiento(userPostulationInfoRequestDto.getFechaNacimiento());
        userPostulationInfo.setGenero(userPostulationInfoRequestDto.getGenero());
        userPostulationInfo.setDireccion(userPostulationInfoRequestDto.getDireccion());
        userPostulationInfo.setTelefono(userPostulationInfoRequestDto.getTelefono());
        userPostulationInfo.setHistorialMedico(userPostulationInfoRequestDto.getHistorialMedico());
        userPostulationInfo.setMedicamentosActuales(userPostulationInfoRequestDto.getMedicamentosActuales());
        userPostulationInfo.setAlergiasConocidas(userPostulationInfoRequestDto.getAlergiasConocidas());
        userPostulationInfo.setCirugiasPrevias(userPostulationInfoRequestDto.getCirugiasPrevias());
        userPostulationInfo.setEnfermedadesCronicas(userPostulationInfoRequestDto.getEnfermedadesCronicas());
        userPostulationInfo.setNivelEducativo(userPostulationInfoRequestDto.getNivelEducativo());
        userPostulationInfo.setOcupacion(userPostulationInfoRequestDto.getOcupacion());
        userPostulationInfo.setHabitosFumar(userPostulationInfoRequestDto.getHabitosFumar());
        userPostulationInfo.setHabitosAlcohol(userPostulationInfoRequestDto.getHabitosAlcohol());
        userPostulationInfo.setHabitosEjercicio(userPostulationInfoRequestDto.getHabitosEjercicio());
        userPostulationInfo.setHabitosAlimenticios(userPostulationInfoRequestDto.getHabitosAlimenticios());
        userPostulationInfo.setConsentimiento(userPostulationInfoRequestDto.getConsentimiento());

        userPostulationInfo.setUser(user);
        user.setUserPostulationInfo(userPostulationInfo);

        userPostulationInfoRepository.save(userPostulationInfo);
        userRepository.save(user);
    }
}

