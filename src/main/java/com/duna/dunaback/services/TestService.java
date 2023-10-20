package com.duna.dunaback.services;

import com.duna.dunaback.dtos.RegistrationUserDtoOut;
import com.duna.dunaback.entities.User;
import com.duna.dunaback.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class TestService {

    private final UserRepo userRepo;

    public RegistrationUserDtoOut getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(()->
                new UsernameNotFoundException("User with id: " + id + "not found "));
        return new RegistrationUserDtoOut(user.getId(), user.getUsername(), user.getPhone(), user.getEmail());
    }


}
