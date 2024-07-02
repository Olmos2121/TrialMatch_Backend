package com.example.uade.tpo.controller;

import com.example.uade.tpo.dtos.response.MessageResponse;
import com.example.uade.tpo.entity.ClinicalTrial;
import com.example.uade.tpo.entity.User;
import com.example.uade.tpo.repository.IClinicalTrialRepository;
import com.example.uade.tpo.repository.IUserRepository;
import com.example.uade.tpo.service.ApplicationService;
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
    private ApplicationService applicationService;

    @PostMapping("/{trialId}/apply/{userEmail}")
    public ResponseEntity<?> applyToTrial(@PathVariable Long trialId, @PathVariable String userEmail) {
        try {
            applicationService.applyToTrial(trialId, userEmail);
            return ResponseEntity.ok(new MessageResponse("Applied to trial successfully!"));
        } catch (OpenApiResourceNotFoundException e) {
            return ResponseEntity.status(404).body(new MessageResponse("Resource not found"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).body(new MessageResponse(e.getMessage()));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new MessageResponse("Internal server error"));
        }
    }
}
