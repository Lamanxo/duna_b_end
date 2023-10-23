package com.duna.dunaback.exceptions.authreg;

public class EmailAlreadyExistException extends AuthOrRegistrationException {
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
