package com.duna.dunaback.exceptions.authreg;

public class UserAlreadyVerificatedException extends AuthOrRegistrationException {
    public UserAlreadyVerificatedException(String message) {
        super(message);
    }
}
