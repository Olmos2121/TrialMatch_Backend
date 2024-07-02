package com.example.uade.tpo.controller;

import com.example.uade.tpo.entity.Investigator;
import com.example.uade.tpo.repository.IInvestigatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/investigator")
public class InvestigatorController {

    @Autowired
    private IInvestigatorRepository investigatorRepository;

    @GetMapping("/me")
    public ResponseEntity<Investigator> getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        UserDetails investigator = investigatorRepository.findByEmail(email).orElseThrow(() ->
                new RuntimeException("Investigador no encontrado"));

        return ResponseEntity.ok((Investigator) investigator);
    }
}
