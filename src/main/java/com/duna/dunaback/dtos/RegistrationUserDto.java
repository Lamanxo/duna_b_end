package com.duna.dunaback.dtos;

import lombok.Data;

import javax.validation.constraints.Email;

@Data
public class RegistrationUserDto {
    private String username;
    private String password;
    private String phone;
    @Email
    private String email;
}
