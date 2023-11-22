package com.duna.dunaback.repositories;

import com.duna.dunaback.entities.EmailToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailTokenRepo extends JpaRepository<EmailToken, Long> {

    Optional<EmailToken> findByToken(String token);
}
