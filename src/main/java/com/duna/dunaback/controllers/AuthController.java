package com.duna.dunaback.controllers;

import com.duna.dunaback.dtos.UserDtoOut;
import com.duna.dunaback.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.duna.dunaback.dtos.JwtRequest;
import com.duna.dunaback.dtos.JwtResponse;
import com.duna.dunaback.dtos.RegistrationUserDto;
import com.duna.dunaback.service.AuthService;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/auth")
    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {
        return authService.createAuthToken(authRequest);
    }

    @PostMapping("/sign_up")
    public UserDtoOut createNewUser(@RequestBody @Validated RegistrationUserDto registrationUserDto) {
        return authService.createNewUser(registrationUserDto);
    }
}