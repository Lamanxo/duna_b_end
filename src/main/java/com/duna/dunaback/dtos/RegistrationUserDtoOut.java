package com.duna.dunaback.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Data
@AllArgsConstructor
public class RegistrationUserDtoOut {
    private Long id;
    private String username;
    private String phone;
    private String email;
}
