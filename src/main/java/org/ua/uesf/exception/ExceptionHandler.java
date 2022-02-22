package org.ua.uesf.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.ua.uesf.exception.messages.messages.AlreadyExistException;
import org.ua.uesf.exception.messages.messages.ExceptionResponse;
import org.ua.uesf.exception.messages.messages.NotFoundException;


import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public @ResponseBody
    ExceptionResponse handleResourceNotFound(final NotFoundException exception,
                                             final HttpServletRequest request) {
        return ExceptionResponse.builder()
                .errorMessage(exception.getMessage())
                .requestedURI(request.getRequestURI())
                .date(Instant.now())
                .build();
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(AlreadyExistException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public @ResponseBody
    ExceptionResponse handleAlreadyExistException(final AlreadyExistException exception,
                                             final HttpServletRequest request) {
        return ExceptionResponse.builder()
                .errorMessage(exception.getMessage())
                .requestedURI(request.getRequestURI())
                .date(Instant.now())
                .build();
    }



}
