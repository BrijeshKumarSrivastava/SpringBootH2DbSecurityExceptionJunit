package com.accenture.springbootexceptionhandlingwithjunit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserInfoExceptionHandler {

    @ExceptionHandler(value = {UserInfoNotFoundException.class})
    public ResponseEntity<Object> handleUserInfoNotFoundException
            (UserInfoNotFoundException userInfoNotFoundException)
    {
        UserInfoException userInfoException = new UserInfoException(
                userInfoNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(userInfoException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MissingInputValueException.class})
    public ResponseEntity<Object> handleEmptyInputValueException
            (MissingInputValueException emptyInputValueException)
    {
        UserInfoException userInfoException = new UserInfoException(
                emptyInputValueException.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(userInfoException, HttpStatus.BAD_REQUEST);
    }

}
