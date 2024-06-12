package com.example.uade.tpo.controller;

import com.example.uade.tpo.dtos.request.ApplicationRequestDto;
import com.example.uade.tpo.dtos.request.LaboratoryRequestDto;
import com.example.uade.tpo.dtos.response.ApplicationResponseDto;
import com.example.uade.tpo.dtos.response.LaboratoryResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class LaboratoryController {

    @Autowired
    private LaboratoryService laboratoryService;

    @PostMapping
    public ResponseEntity<?> createLaboratory(@RequestBody LaboratoryRequestDto laboratoryDto) {
        LaboratoryResponseDto newLaboratory = laboratoryService.createLaboratory(laboratoryDto);
        if (newLaboratory == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(newLaboratory, HttpStatus.CREATED);
    }

    @PutMapping("/{laboratoryId}")
    public ResponseEntity<LaboratoryResponseDto> updateLaboratory
            (@PathVariable Long laboratoryId, @RequestBody LaboratoryRequestDto laboratoryDetails) {
        LaboratoryResponseDto updatedLaboratory = laboratoryService.updateLaboratory(laboratoryId, laboratoryDetails);
        if (updatedLaboratory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedLaboratory, HttpStatus.OK);
    }

    @PutMapping("/{patientId}/Application")// accept or decline application
    public ResponseEntity<?> updateApplication(@PathVariable Long patientId) {
        ApplicationResponseDto updatedApplication = laboratoryService.updateApplication(patientId);
        if (updatedApplication == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedApplication, HttpStatus.OK);
    }
}
