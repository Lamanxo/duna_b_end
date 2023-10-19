package com.duna.dunaback.services;

import com.duna.dunaback.dtos.JwtRequest;
import com.duna.dunaback.dtos.JwtResponse;
import com.duna.dunaback.dtos.RegistrationUserDto;
import com.duna.dunaback.dtos.RegistrationUserDtoOut;
import com.duna.dunaback.entities.User;
import com.duna.dunaback.exception.UserAlreadyExistException;
import com.duna.dunaback.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtTokenUtils jwtTokenUtils;
    private final AuthenticationManager manager;

    public JwtResponse makeAuthToken(JwtRequest request) {
        try {
            manager.authenticate(new UsernamePasswordAuthenticationToken(request.getPhone(), request.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Wrong password or phone number");
        }
        UserDetails userDetails = userService.loadUserByUsername(request.getPhone());
        String token = jwtTokenUtils.generateToken(userDetails);
        return new JwtResponse(token);
    }

    public RegistrationUserDtoOut createNewUser(RegistrationUserDto dto) {
        if (userService.findByUsernameOptional(dto.getUsername()).isPresent()) {
            throw new UserAlreadyExistException(String.format("Username '%s' already taken", dto.getUsername()));
        }
        if (userService.findByPhoneOptional(dto.getPhone()).isPresent()) {
            throw new UserAlreadyExistException(String.format("Phone number '%s' already taken", dto.getPhone()));
        }
        User user = userService.createUser(dto);
        return new RegistrationUserDtoOut(user.getId(), user.getUsername(), user.getPhone(), user.getEmail());
    }

}
