package com.example.uade.tpo.controller;

import com.example.uade.tpo.dtos.response.UserPostulationInfoResponseDto;
import com.example.uade.tpo.dtos.response.UserResponseDto;
import com.example.uade.tpo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{email}")
    public ResponseEntity<UserResponseDto> getUser(@PathVariable String email) {
        try {
            UserResponseDto userResponseDto = userService.getUser(email);
            return ResponseEntity.ok(userResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/postulationInfo/{id}")
    public ResponseEntity<UserPostulationInfoResponseDto> getUserPostulationInfo(@PathVariable Long id) {
        try {
            UserPostulationInfoResponseDto userPostulationInfoResponseDto = userService.getUserPostulationInfo(id);
            return ResponseEntity.ok(userPostulationInfoResponseDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
