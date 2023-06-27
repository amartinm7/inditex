package com.inditex.hiring.infrastructure.framework.config;

import com.inditex.hiring.domain.offer.exception.OfferNotFound;
import com.inditex.hiring.infrastructure.framework.offer.controller.dto.ErrorHttp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

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

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<ErrorHttp> handleDomainException(HttpMediaTypeNotSupportedException ex) {
        return new ResponseEntity(ErrorHttp.of(ex.getMessage()), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorHttp> handleDomainException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getField())
                    .append(": ")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        }
        return new ResponseEntity(ErrorHttp.of(errorMessage.toString()), HttpStatus.BAD_REQUEST);
    }
}

