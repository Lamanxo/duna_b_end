package com.duna.dunaback.exceptions.authreg;

public class PhoneAlreadyExistException extends AuthOrRegistrationException {
    public PhoneAlreadyExistException(String message) {
        super(message);
    }
}
