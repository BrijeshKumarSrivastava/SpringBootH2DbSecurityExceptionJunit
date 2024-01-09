package com.accenture.springbootexceptionhandlingwithjunit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(value = {EmployeeNotFoundException.class})
    public ResponseEntity<Object> handleEmployeeNotFoundException
            (EmployeeNotFoundException employeeNotFoundException)
    {
        EmployeeException employeeException = new EmployeeException(
                employeeNotFoundException.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(employeeException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {MissingInputValueException.class})
    public ResponseEntity<Object> handleEmptyInputValueException
            (MissingInputValueException emptyInputValueException)
    {
        EmployeeException employeeException = new EmployeeException(
                emptyInputValueException.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(employeeException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {EmptyEmployeeListException.class})
    public ResponseEntity<Object> handleEmptyInputValueException
            (EmptyEmployeeListException emptyEmployeeListException)
    {
        EmployeeException employeeException = new EmployeeException(
                emptyEmployeeListException.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(employeeException, HttpStatus.NOT_FOUND);
    }


}
