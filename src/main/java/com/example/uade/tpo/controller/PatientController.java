package com.example.uade.tpo.controller;

import com.example.uade.tpo.dtos.request.ApplicationRequestDto;
import com.example.uade.tpo.dtos.request.PatientRequestDto;
import com.example.uade.tpo.dtos.response.ApplicationResponseDto;
import com.example.uade.tpo.dtos.response.PatientResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody PatientRequestDto patientDto) {
        PatientResponseDto newPatient = patientService.createPatient(patientDto);
        if (newPatient == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(newPatient, HttpStatus.CREATED);
    }

    @PutMapping("/{patientId}")
    public ResponseEntity<PatientResponseDto> updatePatient
            (@PathVariable Long patientId, @RequestBody PatientRequestDto patientDetails) {
        PatientResponseDto updatedPatient = patientService.updatePatient(patientId, patientDetails);
        if (updatedPatient == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
    }

    @PostMapping("/Application")
    public ResponseEntity<?> createApply(@RequestBody ApplicationRequestDto applicationDto) {
        ApplicationResponseDto newApplication = patientService.createApplication(applicationDto);
        if (newApplication == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(newApplication, HttpStatus.CREATED);
    }

    @PostMapping("/Application/trial")
    public ResponseEntity<?> createApplyTrial(@RequestBody ApplicationRequestDto applicationDto) {
        ApplicationResponseDto newApplication = patientService.createApplicationTrial(applicationDto);
        if (newApplication == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(newApplication, HttpStatus.CREATED);
    }



}
