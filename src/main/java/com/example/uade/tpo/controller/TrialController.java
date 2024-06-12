package com.example.uade.tpo.controller;

import com.example.uade.tpo.dtos.response.TrialResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public class TrialController {

    @Autowired
    private TrialService trialService;

    @GetMapping("/Trial/{trialId}")
    public ResponseEntity<?> getTrial(@PathVariable Long trialId) {
        TrialResponseDto trial = trialService.getTrial(trialId);
        if (trial == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(trial, HttpStatus.OK);
    }

    @PutMapping("/Trial/{trialId}") //endpoint for update the trial status
    public ResponseEntity<?> updateTrialStatus(@PathVariable Long trialId) {
        TrialResponseDto updatedTrial = trialService.updateTrialStatus(trialId);
        if (updatedTrial == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedTrial, HttpStatus.OK);
    }

}
