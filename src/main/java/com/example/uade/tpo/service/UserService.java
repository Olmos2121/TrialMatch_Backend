package com.example.uade.tpo.service;

import com.example.uade.tpo.dtos.response.UserPostulationInfoResponseDto;
import com.example.uade.tpo.dtos.response.UserResponseDto;
import com.example.uade.tpo.entity.User;
import com.example.uade.tpo.entity.UserPostulationInfo;
import com.example.uade.tpo.repository.IUserPostulationInfoRespository;
import com.example.uade.tpo.repository.IUserRepository;
import com.example.uade.tpo.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserPostulationInfoRespository userPostulationInfoRepository;

    public UserResponseDto getUser(String email) {
        Optional<UserDetails> userDetails = userRepository.findByEmail(email);
        if(userDetails.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        User user = (User) userDetails.get();
        return Mapper.convertUserResponseDto(user);
    }

    public UserPostulationInfoResponseDto getUserPostulationInfo(Long id) {
        UserPostulationInfo userPostulationInfo = userPostulationInfoRepository.findByUserId(id);
        return Mapper.convertUserPostulationInfoResponseDto(userPostulationInfo);
    }
}
