package com.example.uade.tpo.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "messages")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private User receiver;
    @ManyToOne
    @JoinColumn(name = "trial_id")
    private ClinicalTrial trial;


}
