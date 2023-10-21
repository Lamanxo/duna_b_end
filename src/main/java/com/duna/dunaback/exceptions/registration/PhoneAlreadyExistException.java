package com.duna.dunaback.exceptions.registration;

public class PhoneAlreadyExistException extends RegistrationException {
    public PhoneAlreadyExistException(String message) {
        super(message);
    }
}
