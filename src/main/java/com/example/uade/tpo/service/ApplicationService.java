package com.example.uade.tpo.service;

import com.example.uade.tpo.entity.ClinicalTrial;
import com.example.uade.tpo.entity.User;
import com.example.uade.tpo.repository.IClinicalTrialRepository;
import com.example.uade.tpo.repository.IUserRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private IClinicalTrialRepository clinicalTrialRepository;

    @Autowired
    private IUserRepository userRepository;

    public void applyToTrial(Long trialId, String userEmail) {
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(trialId).orElseThrow(()
                -> new OpenApiResourceNotFoundException("Clinical trial not found"));
        Optional<UserDetails> userDetails = userRepository.findByEmail(userEmail);
        clinicalTrial.getCandidatos().add((User) userDetails.get());
        clinicalTrialRepository.save(clinicalTrial);
    }
}

