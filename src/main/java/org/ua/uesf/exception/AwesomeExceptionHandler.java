package org.ua.uesf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@ControllerAdvice(annotations = RestController.class)
public class AwesomeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ThereIsNoSuchUserException.class)
    protected ResponseEntity<ExceptionMessage> handleThereIsNoSuchUserException(WebRequest webRequest) {
        return new ResponseEntity( ExceptionMessage.builder().build(),
                HttpStatus.NOT_FOUND);
    }


}
