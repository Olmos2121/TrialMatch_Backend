package com.example.uade.tpo.service;

import com.example.uade.tpo.dtos.request.MessageRequestDto;
import com.example.uade.tpo.entity.ClinicalTrial;
import com.example.uade.tpo.entity.User;
import com.example.uade.tpo.repository.IClinicalTrialRepository;
import com.example.uade.tpo.repository.IUserRepository;
import com.example.uade.tpo.service.email.EmailService;
import org.springdoc.api.OpenApiResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private EmailService emailService;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IClinicalTrialRepository clinicalTrialRepository;

    public void sendAcceptanceNotification(String email, Long trialId) {
        Optional<UserDetails> userDetails = userRepository.findByEmail(email);
        User user = (User) userDetails.get();

        ClinicalTrial trial = clinicalTrialRepository.findById(trialId).orElseThrow(()
                -> new OpenApiResourceNotFoundException("Clinical trial not found"));

        String subject = "Postulación Aceptada para Ensayo Clínico";
        String text = "Estimado " + user.getUsername() + ",\n\n"
                + "Nos complace informarle que su postulación al ensayo clínico " + trial.getName() + " ha sido aceptada.\n\n"
                + "A continuacion se comunicaran por este medio para coordinar los detalles de su participación.\n\n"
                + "Saludos,\n"
                + "Equipo de TrialMatch";

        emailService.sendSimpleMessage(user.getEmail(), subject, text);
    }

    public void sendMessage(Long trialId, Long userId, MessageRequestDto message) {
        Optional<User> user = userRepository.findById(userId);
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(trialId).orElse(null);
        if (clinicalTrial == null) {
            throw new OpenApiResourceNotFoundException("Clinical trial not found");
        }
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        if(message.getMessage().isEmpty()){
            throw new RuntimeException("Message cannot be empty");
        }
        String subject = "Mensaje de " + clinicalTrial.getInvestigador() + " - " + clinicalTrial.getName();

        String text = "Estimado " + user.get().getUsername() + ",\n\n"
                + "El investigador " + clinicalTrial.getInvestigador() + " ha enviado un mensaje para usted.\n\n"
                + message.getMessage() + "\n\n"
                + "Saludos,\n"
                + "Equipo de TrialMatch";

        emailService.sendSimpleMessage(user.get().getEmail(), subject, text);
    }

    public void sendMessageToAll(Long trialId, MessageRequestDto message) {
        ClinicalTrial clinicalTrial = clinicalTrialRepository.findById(trialId).orElse(null);
        if (clinicalTrial == null) {
            throw new OpenApiResourceNotFoundException("Clinical trial not found");
        }
        if(message.getMessage().isEmpty()){
            throw new RuntimeException("Message cannot be empty");
        }
        String subject = "Mensaje de " + clinicalTrial.getInvestigador() + " - " + clinicalTrial.getName();

        String text = "Estimado participante,\n\n"
                + "El investigador " + clinicalTrial.getInvestigador() + " ha enviado un mensaje para usted.\n\n"
                + message.getMessage() + "\n\n"
                + "Saludos,\n"
                + "Equipo de TrialMatch";

        for (User user : clinicalTrial.getParticipantes()) {
            emailService.sendSimpleMessage(user.getEmail(), subject, text);
        }
    }
}
