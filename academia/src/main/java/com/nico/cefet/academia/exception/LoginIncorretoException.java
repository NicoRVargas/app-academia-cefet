package com.nico.cefet.academia.exception;

public class LoginIncorretoException extends RuntimeException {
    public LoginIncorretoException(String message) {
        super(message);
    }
}
