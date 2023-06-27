package com.inditex.hiring.infrastructure.framework.config;

import com.inditex.hiring.domain.offer.exception.OfferNotFound;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.ErrorHttp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ControllerAdvice {
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorHttp> handleDomainException(MissingServletRequestParameterException ex) {
        return new ResponseEntity(ErrorHttp.of(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorHttp> handleDomainException(OfferNotFound ex) {
        return new ResponseEntity(ErrorHttp.of(ex.getMessage()), HttpStatus.NOT_FOUND);
    }
}

