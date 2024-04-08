package com.amit.electronic.store.exception;


public class InvalidUserRequestException extends RuntimeException {
    public InvalidUserRequestException(String invalidUserRequest) {
        super(invalidUserRequest);
    }
}
