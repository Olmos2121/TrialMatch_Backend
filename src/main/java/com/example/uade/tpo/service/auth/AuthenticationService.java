package com.example.uade.tpo.service.auth;


import com.example.uade.tpo.Configuration.auth.JwtService;
import com.example.uade.tpo.controller.auth.AuthenticationRequest;
import com.example.uade.tpo.controller.auth.AuthenticationResponse;
import com.example.uade.tpo.controller.auth.RegisterLabRequest;
import com.example.uade.tpo.controller.auth.RegisterRequest;
import com.example.uade.tpo.entity.Investigator;
import com.example.uade.tpo.entity.User;
import com.example.uade.tpo.entity.enumeration.Role;
import com.example.uade.tpo.repository.IInvestigatorRepository;
import com.example.uade.tpo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private IInvestigatorRepository investigatorRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.PATIENT)
                .build();

        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse registerLaboratory(RegisterLabRequest request) {
        var investigator = Investigator.builder()
                .companyName(request.getCompanyName())
                .cuit(request.getCuit())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.INVESTIGATOR)
                .build();

        investigatorRepository.save(investigator);
        var jwtToken = jwtService.generateToken(investigator);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateInvestigator(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var investigator = investigatorRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("Investigador no encontrado"));

        var jwtToken = jwtService.generateToken(investigator);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
