package com.example.uade.tpo.controller;

import com.example.uade.tpo.dtos.response.MessageResponse;
import com.example.uade.tpo.entity.ClinicalTrial;
import com.example.uade.tpo.entity.User;
import com.example.uade.tpo.repository.IClinicalTrialRepository;
import com.example.uade.tpo.repository.IUserRepository;
import com.example.uade.tpo.service.NotificationService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trials")
public class ClinicalTrialControler {

    @Autowired
    private IClinicalTrialRepository IClinicalTrialRepository;

    @Autowired
    private IUserRepository IUserRepository;

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ClinicalTrial createTrial(@RequestBody ClinicalTrial clinicalTrial) {
        return IClinicalTrialRepository.save(clinicalTrial);
    }

    @GetMapping
    public List<ClinicalTrial> getAllTrials() {
        return IClinicalTrialRepository.findAll();
    }

    @GetMapping("/{id}")
    public ClinicalTrial getTrialById(@PathVariable Long id) {
        return IClinicalTrialRepository.findById(id).orElseThrow(() ->
                new OpenApiResourceNotFoundException("Trial not found"));
    }

    @PostMapping("/{trialId}/accept/{userId}")
    public ResponseEntity<?> acceptApplication(@PathVariable Long trialId, @PathVariable Long userId) {
        ClinicalTrial trial = IClinicalTrialRepository.findById(trialId).orElseThrow(() ->
                new OpenApiResourceNotFoundException("Trial not found"));
        User user = IUserRepository.findById(userId).orElseThrow(() ->
                new OpenApiResourceNotFoundException("User not found"));

        if (!trial.getCandidates().contains(user)) {
            return ResponseEntity.badRequest().body(new MessageResponse("User has not applied to this trial!"));
        }

        trial.getParticipants().add(user);
        IClinicalTrialRepository.save(trial);

        notificationService.sendAcceptanceNotification(user, trial);

        return ResponseEntity.ok(new MessageResponse("Application accepted successfully!"));
    }

    @GetMapping("/search")
    public List<ClinicalTrial> searchTrials(@RequestParam String query) {
        return IClinicalTrialRepository.findByTitleContainingOrDescriptionContaining(query, query);
    }

    @PutMapping("/{id}")
    public ClinicalTrial updateTrial(@PathVariable Long id, @RequestBody ClinicalTrial clinicalTrial) {
        ClinicalTrial trial = IClinicalTrialRepository.findById(id).orElseThrow(() ->
                new OpenApiResourceNotFoundException("Trial not found"));
        trial.setTitle(clinicalTrial.getTitle());
        trial.setDescription(clinicalTrial.getDescription());
        trial.setStatus(clinicalTrial.getStatus());

        return IClinicalTrialRepository.save(trial);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTrial(@PathVariable Long id) {
        IClinicalTrialRepository.findById(id).orElseThrow(() ->
                new OpenApiResourceNotFoundException("Trial not found"));

        IClinicalTrialRepository.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
