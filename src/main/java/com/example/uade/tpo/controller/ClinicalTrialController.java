package com.example.uade.tpo.controller;


import com.example.uade.tpo.dtos.request.SearchTrialRequestDto;
import com.example.uade.tpo.dtos.request.TrialRequestDto;
import com.example.uade.tpo.dtos.response.ClinicalTrialResponseDto;
import com.example.uade.tpo.service.ClinicalTrialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trials")
public class ClinicalTrialController {

    @Autowired
    private ClinicalTrialService clinicalTrialService;

    @GetMapping
    public ResponseEntity<List<ClinicalTrialResponseDto>> getAllTrials() {
        try {
            List<ClinicalTrialResponseDto> clinicalTrialResponseDtos =
                    clinicalTrialService.getAllTrials();
            return ResponseEntity.ok(clinicalTrialResponseDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicalTrialResponseDto> getTrial(@PathVariable Long id) {
        try {
            ClinicalTrialResponseDto clinicalTrialResponseDto = clinicalTrialService.getTrial(id);
            return ResponseEntity.ok(clinicalTrialResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/create/{email}")
    public ResponseEntity<ClinicalTrialResponseDto> createTrial(@PathVariable String email,
                                                                @RequestBody TrialRequestDto trialRequestDto) {
        try {
            ClinicalTrialResponseDto clinicalTrialResponseDto = clinicalTrialService.createTrial(email, trialRequestDto);
            return ResponseEntity.ok(clinicalTrialResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
