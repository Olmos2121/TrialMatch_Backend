package com.example.uade.tpo.service;

import com.example.uade.tpo.entity.ClinicalTrial;
import com.example.uade.tpo.entity.User;
import com.example.uade.tpo.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
//    @Autowired
//    private EmailService emailService;

//    public void sendAcceptanceNotification(User user, ClinicalTrial trial) {
//        String to = user.getEmail();
//        String subject = "Postulación Aceptada para Ensayo Clínico";
//        String text = "Estimado " + user.getUsername() + ",\n\n"
//                + "Nos complace informarle que su postulación al ensayo clínico " + trial.getTitle() + " ha sido aceptada.\n\n"
//                + "A continuacion se comunicaran por este medio para coordinar los detalles de su participación.\n\n"
//                + "Saludos,\n"
//                + "Equipo de TrialMatch";
//
//        emailService.sendSimpleMessage(to, subject, text);
//    }
}
