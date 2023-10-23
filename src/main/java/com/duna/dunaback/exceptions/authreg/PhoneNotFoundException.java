package com.duna.dunaback.exceptions.authreg;

public class PhoneNotFoundException extends AuthOrRegistrationException {
    public PhoneNotFoundException(String message) {
        super(message);
    }
}
