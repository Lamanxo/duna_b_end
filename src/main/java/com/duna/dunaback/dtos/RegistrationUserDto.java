package com.duna.dunaback.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@RequiredArgsConstructor
@Data
public class RegistrationUserDto {
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String phone;
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
}
