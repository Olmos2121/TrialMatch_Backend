package com.example.uade.tpo.repository;

import com.example.uade.tpo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IMessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByTrialId(Long trialId);
}
