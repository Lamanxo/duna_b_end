package com.duna.dunaback.exceptions.authreg;

public class UserNotVerifiedException extends AuthOrRegistrationException {
    public UserNotVerifiedException(String message) {
        super(message);
    }
}
