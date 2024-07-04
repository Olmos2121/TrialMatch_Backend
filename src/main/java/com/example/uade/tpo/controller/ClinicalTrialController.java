package com.example.uade.tpo.controller;


import com.example.uade.tpo.dtos.request.EditTrialRequestDto;
import com.example.uade.tpo.dtos.request.SearchTrialRequestDto;
import com.example.uade.tpo.dtos.request.TrialRequestDto;
import com.example.uade.tpo.dtos.response.ClinicalTrialResponseDto;
import com.example.uade.tpo.dtos.response.UserResponseDto;
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

    @GetMapping("/all/{email}")
    public ResponseEntity<List<ClinicalTrialResponseDto>> getTrialsByEmail(@PathVariable String email) {
        try {
            List<ClinicalTrialResponseDto> clinicalTrialResponseDtos =
                    clinicalTrialService.getTrialsByEmail(email);
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTrial(@PathVariable Long id) {
        try {
            Boolean deleted = clinicalTrialService.deleteTrial(id);
            return ResponseEntity.ok(deleted);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/editTrial/{id}")
    public ResponseEntity<ClinicalTrialResponseDto> editTrial(@PathVariable Long id,
                                                              @RequestBody EditTrialRequestDto trialRequestDto) {
        try {
            ClinicalTrialResponseDto clinicalTrialResponseDto = clinicalTrialService.editTrial(id, trialRequestDto);
            return ResponseEntity.ok(clinicalTrialResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/candidatos/{id}")
    public ResponseEntity<List<UserResponseDto>> getCandidates(@PathVariable Long id) {
        try {
            List<UserResponseDto> clinicalTrialResponseDtos = clinicalTrialService.getCandidates(id);
            return ResponseEntity.ok(clinicalTrialResponseDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/acceptApply/{id}/{email}")
    public ResponseEntity<Boolean> acceptApply(@PathVariable Long id, @PathVariable String email) {
        try {
            Boolean added = clinicalTrialService.acceptApply(id, email);
            return ResponseEntity.ok(added);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/rejectApply/{id}/{email}")
    public ResponseEntity<Boolean> rejectApply(@PathVariable Long id, @PathVariable String email) {
        try {
            Boolean rejected = clinicalTrialService.rejectApply(id, email);
            return ResponseEntity.ok(rejected);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/removeParticipant/{id}/{email}")
    public ResponseEntity<Boolean> removeParticipant(@PathVariable Long id, @PathVariable String email) {
        try {
            Boolean removed = clinicalTrialService.removeParticipant(id, email);
            return ResponseEntity.ok(removed);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/participantes/{id}")
    public ResponseEntity<List<UserResponseDto>> getParticipants(@PathVariable Long id) {
        try {
            List<UserResponseDto> clinicalTrialResponseDtos = clinicalTrialService.getParticipants(id);
            return ResponseEntity.ok(clinicalTrialResponseDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}