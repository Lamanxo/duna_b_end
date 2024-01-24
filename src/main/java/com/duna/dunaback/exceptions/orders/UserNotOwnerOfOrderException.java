package com.duna.dunaback.exceptions.orders;

public class UserNotOwnerOfOrderException extends RuntimeException {
    public UserNotOwnerOfOrderException(String message) {
        super(message);
    }
}
