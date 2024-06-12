package com.example.uade.tpo.controller;

import com.example.uade.tpo.dtos.response.MessageResponse;
import com.example.uade.tpo.entity.ClinicalTrial;
import com.example.uade.tpo.entity.User;
import com.example.uade.tpo.repository.ClinicalTrialRepository;
import com.example.uade.tpo.repository.UserRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/application")
public class ApplicationController {

    @Autowired
    private ClinicalTrialRepository clinicalTrialRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/{trialId}/apply/{userId}")
    public ResponseEntity<?> applyToTrial(@PathVariable Long trialId, @PathVariable Long userId) {
        ClinicalTrial trial = clinicalTrialRepository.findById(trialId).orElseThrow(() ->
                new OpenApiResourceNotFoundException("Trial not found"));
        User user = userRepository.findById(userId).orElseThrow(() ->
                new OpenApiResourceNotFoundException("User not found"));

        if (trial.getParticipants().contains(user)) {
            return ResponseEntity.badRequest().body(new MessageResponse("User already applied to this trial!"));
        }

        trial.getParticipants().add(user);
        clinicalTrialRepository.save(trial);

        return ResponseEntity.ok(new MessageResponse("Applied successfully!"));
    }
}
