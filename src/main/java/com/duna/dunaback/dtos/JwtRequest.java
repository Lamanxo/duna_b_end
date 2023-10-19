package com.duna.dunaback.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String phone;
    private String password;
}
