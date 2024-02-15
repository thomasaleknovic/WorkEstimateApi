package com.thomasaleknovic.workestimateapi.infra;

import com.thomasaleknovic.workestimateapi.exceptions.Estimate.EstimateNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EstimateNotFoundException.class)
    private ResponseEntity<RestErrorMessage> estimateNotFoundHandler (
            EstimateNotFoundException exception) {

        RestErrorMessage message = new RestErrorMessage(
                HttpStatus.NOT_FOUND, exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(message);
    }

    @ExceptionHandler(AuthenticationException.class)
    private ResponseEntity<RestErrorMessage> authenticationExceptionHandler(
            AuthenticationException exception) {

        RestErrorMessage message = new RestErrorMessage(
                HttpStatus.UNAUTHORIZED, "Usuário ou senha inválidos.");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(message);
    }
}
