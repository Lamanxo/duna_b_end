package com.duna.dunaback.service;

import com.duna.dunaback.entities.EmailToken;
import com.duna.dunaback.repositories.EmailTokenRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class EmailTokenService {
    private final EmailTokenRepo tokenRepo;

    public EmailToken getEmailTokenByToken(String token) {
        return tokenRepo.findByToken(token).orElseThrow(()-> new EntityNotFoundException("Email token not found"));
    }

    public String saveEmailToken(EmailToken token) {
        return tokenRepo.save(token).getToken();
    }

}
