package com.example.uade.tpo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_postulation_info")
public class UserPostulationInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_postulation_info_seq")
    @SequenceGenerator(name = "user_postulation_info_seq", sequenceName = "user_postulation_info_seq", allocationSize = 1)
    private Long id;

}
