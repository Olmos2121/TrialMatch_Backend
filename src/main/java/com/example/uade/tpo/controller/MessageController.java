package com.example.uade.tpo.controller;

import com.example.uade.tpo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/sendAcceptance/{email}/{trialId}")
    public ResponseEntity<?> sendMessage(@PathVariable String email, @PathVariable Long trialId) {
        notificationService.sendAcceptanceNotification(email, trialId);
        return ResponseEntity.ok().build();
    }
}
