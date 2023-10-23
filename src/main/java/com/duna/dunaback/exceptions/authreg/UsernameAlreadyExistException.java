package com.duna.dunaback.exceptions.authreg;

public class UsernameAlreadyExistException extends AuthOrRegistrationException {
    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
