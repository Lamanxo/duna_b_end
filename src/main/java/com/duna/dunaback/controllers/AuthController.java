package com.duna.dunaback.controllers;

import com.duna.dunaback.dtos.JwtRequest;
import com.duna.dunaback.dtos.JwtResponse;
import com.duna.dunaback.dtos.RegistrationUserDto;
import com.duna.dunaback.dtos.RegistrationUserDtoOut;
import com.duna.dunaback.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
private final AuthService authService;

    @PostMapping("/auth")
    public JwtResponse createAuthToken(@RequestBody JwtRequest request) {
        return authService.makeAuthToken(request);
    }

    @PostMapping("/registration")
    public RegistrationUserDtoOut createNewUser(@RequestBody RegistrationUserDto dto) {
        return authService.createNewUser(dto);
    }

}
