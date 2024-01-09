package com.accenture.springbootexceptionhandlingwithjunit.exception;

import org.springframework.http.HttpStatus;

public class UserInfoException {
    private final String message;
    private final HttpStatus httpStatus;
    public UserInfoException(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
