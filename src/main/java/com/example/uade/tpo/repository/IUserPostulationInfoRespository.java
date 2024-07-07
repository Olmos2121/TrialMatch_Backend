package com.example.uade.tpo.repository;

import com.example.uade.tpo.entity.UserPostulationInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserPostulationInfoRespository extends JpaRepository<UserPostulationInfo, Long> {
    UserPostulationInfo findByUserId(Long id);
}
