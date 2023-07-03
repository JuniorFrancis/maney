package com.maney.api.exceptions;

public class InvalidCredentialsException extends IllegalArgumentException {

    public InvalidCredentialsException(String s) {
        super(s);
    }
}
