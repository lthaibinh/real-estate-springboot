package com.demo.real_estate.exception;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ControllerAdvice    
public class ExceptionControllerAdvice {

	@ExceptionHandler // when Invalid Credentials
    public ResponseEntity<ErrorMessage> handleInvalidCredentialsException(
            BadCredentialsException ex) {


        return new ResponseEntity<ErrorMessage>(
                new ErrorMessage(ex.getMessage()), HttpStatus.UNAUTHORIZED);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    class ErrorMessage {
        private String error;
    }
}

