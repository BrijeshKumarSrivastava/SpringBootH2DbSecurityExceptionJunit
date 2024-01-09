package com.accenture.springbootexceptionhandlingwithjunit.exception;

public class MissingInputValueException extends RuntimeException{
    public MissingInputValueException(String message) {
        super(message);
    }

}
