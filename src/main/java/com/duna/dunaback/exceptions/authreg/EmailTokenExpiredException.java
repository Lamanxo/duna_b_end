package com.duna.dunaback.exceptions.authreg;

public class EmailTokenExpiredException extends AuthOrRegistrationException {
    public EmailTokenExpiredException(String message) {
        super(message);
    }
}
