package com.duna.dunaback.dtos;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}
