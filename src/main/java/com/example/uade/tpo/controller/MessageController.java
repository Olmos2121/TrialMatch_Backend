package com.example.uade.tpo.controller;

import com.example.uade.tpo.dtos.request.MessageRequestDto;
import com.example.uade.tpo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/sendMessage/{trialId}/{userId}")
    public ResponseEntity<?> sendMessage(@PathVariable Long trialId, @PathVariable Long userId,
                                         @RequestBody MessageRequestDto message) {
        try {
            notificationService.sendMessage(trialId, userId, message);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/sendMessageToAll/{trialId}")
    public ResponseEntity<?> sendMessageToAll(@PathVariable Long trialId, @RequestBody MessageRequestDto message) {
        try {
            notificationService.sendMessageToAll(trialId, message);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
