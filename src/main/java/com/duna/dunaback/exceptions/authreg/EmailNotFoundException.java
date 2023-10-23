package com.duna.dunaback.exceptions.authreg;

public class EmailNotFoundException extends AuthOrRegistrationException {
    public EmailNotFoundException(String message) {
        super(message);
    }
}
