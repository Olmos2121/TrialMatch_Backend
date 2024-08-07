package com.example.uade.tpo.controller.auth;

import com.example.uade.tpo.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/register/investigator")
    public ResponseEntity<AuthenticationResponse> registerLaboratory(@Valid @RequestBody RegisterLabRequest request) {
        return ResponseEntity.ok(service.registerLaboratory(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/authenticate/investigator")
    public ResponseEntity<AuthenticationResponse> authenticateInvestigator(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticateInvestigator(request));
    }
}
