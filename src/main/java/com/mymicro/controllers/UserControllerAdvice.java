package com.mymicro.controllers;


import com.mymicro.exceptions.ApiException;
import com.mymicro.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.mymicro.exceptions.UserExistsException;
import org.springframework.web.bind.support.WebExchangeBindException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Objects;


@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<Object> userExists(UserExistsException exception){
        ApiException apiException = new ApiException(
            exception.getMessage(),
            HttpStatus.BAD_REQUEST,
            ZonedDateTime.now(ZoneId.of("Z"))
        );
     return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> userNotFound(UserNotFoundException exception){
        ApiException apiException = new ApiException(
                exception.getMessage(),
                HttpStatus.NOT_FOUND,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<Object> validationFailed(WebExchangeBindException exception){
        ApiException apiException = new ApiException(
                Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }


}
