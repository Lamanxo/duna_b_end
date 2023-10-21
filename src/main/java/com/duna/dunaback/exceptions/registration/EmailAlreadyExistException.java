package com.duna.dunaback.exceptions.registration;

public class EmailAlreadyExistException extends RegistrationException {
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
