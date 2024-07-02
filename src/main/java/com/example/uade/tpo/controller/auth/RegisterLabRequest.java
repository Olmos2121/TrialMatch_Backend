package com.example.uade.tpo.controller.auth;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class RegisterLabRequest {
    String companyName;
    String cuit;
    String email;
    String phone;
    String address;
    String password;
}
