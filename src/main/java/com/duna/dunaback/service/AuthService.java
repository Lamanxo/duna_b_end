package com.duna.dunaback.service;

import com.duna.dunaback.dtos.JwtRequest;
import com.duna.dunaback.dtos.JwtResponse;
import com.duna.dunaback.dtos.RegistrationUserDto;
import com.duna.dunaback.dtos.UserDtoOut;
import com.duna.dunaback.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager authenticationManager;

    public JwtResponse createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getPhone(), authRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Неправильный логин или пароль");
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getPhone());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }
    public UserDtoOut createNewUser(RegistrationUserDto registrationUserDto) {
        return userService.createNewUser(registrationUserDto);

    }
}
