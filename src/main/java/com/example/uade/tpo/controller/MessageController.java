package com.example.uade.tpo.controller;

import com.example.uade.tpo.entity.Message;
import com.example.uade.tpo.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Autowired
    private IMessageRepository IMessageRepository;

    @PostMapping
    public Message createMessage(@RequestBody Message message) {
        return IMessageRepository.save(message);
    }

    @GetMapping("/trial/{trialId}")
    public List<Message> getMessagesByTrialId(@PathVariable Long trialId) {
        return IMessageRepository.findByTrialId(trialId);
    }
}
