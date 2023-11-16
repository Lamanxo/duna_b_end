package com.duna.dunaback.exceptions.authreg;

public class UserIsBlockedException extends AuthOrRegistrationException {
    public UserIsBlockedException(String message) {
        super(message);
    }
}
