package com.example.uade.tpo.controller;

import com.example.uade.tpo.entity.ClinicalTrial;
import com.example.uade.tpo.repository.ClinicalTrialRepository;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trials")
public class ClinicalTrialControler {

    @Autowired
    private ClinicalTrialRepository clinicalTrialRepository;

    @PostMapping
    public ClinicalTrial createTrial(@RequestBody ClinicalTrial clinicalTrial) {
        return clinicalTrialRepository.save(clinicalTrial);
    }

    @GetMapping
    public List<ClinicalTrial> getAllTrials() {
        return clinicalTrialRepository.findAll();
    }

    @GetMapping("/{id}")
    public ClinicalTrial getTrialById(@PathVariable Long id) {
        return clinicalTrialRepository.findById(id).orElseThrow(() ->
                new OpenApiResourceNotFoundException("Trial not found"));
    }

    @GetMapping("/search")
    public List<ClinicalTrial> searchTrials(@RequestParam String query) {
        return clinicalTrialRepository.findByTitleContainingOrDescriptionContaining(query, query);
    }

    @PutMapping("/{id}")
    public ClinicalTrial updateTrial(@PathVariable Long id, @RequestBody ClinicalTrial clinicalTrial) {
        ClinicalTrial trial = clinicalTrialRepository.findById(id).orElseThrow(() ->
                new OpenApiResourceNotFoundException("Trial not found"));
        trial.setTitle(clinicalTrial.getTitle());
        trial.setDescription(clinicalTrial.getDescription());
        trial.setStatus(clinicalTrial.getStatus());

        return clinicalTrialRepository.save(trial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrial(@PathVariable Long id) {
        clinicalTrialRepository.findById(id).orElseThrow(() ->
                new OpenApiResourceNotFoundException("Trial not found"));

        clinicalTrialRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
