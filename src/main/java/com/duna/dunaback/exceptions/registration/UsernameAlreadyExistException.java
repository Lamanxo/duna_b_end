package com.duna.dunaback.exceptions.registration;

public class UsernameAlreadyExistException extends RegistrationException{
    public UsernameAlreadyExistException(String message) {
        super(message);
    }
}
