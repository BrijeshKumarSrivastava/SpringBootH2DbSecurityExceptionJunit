package com.accenture.springbootexceptionhandlingwithjunit.exception;

public class EmptyEmployeeListException extends RuntimeException{

    public EmptyEmployeeListException(String message) {
        super(message);
    }

}
