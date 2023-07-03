package com.maney.api.exceptions;

public class AlreadyExistingUsernameException extends RuntimeException{

    public AlreadyExistingUsernameException(String message) {
        super(message);
    }

}
