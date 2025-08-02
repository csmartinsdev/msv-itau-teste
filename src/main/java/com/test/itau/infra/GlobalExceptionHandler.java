package com.test.itau.infra;

import com.test.itau.exception.BusinessErrorsException;
import com.test.itau.exception.BusinessException;
import com.test.itau.exception.ResourceNotFoundException;
import com.test.itau.shared.response.ExceptionResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Object> handleException(ResourceNotFoundException e) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .message(e.getMessage())
                .code(HttpStatus.NOT_FOUND.value())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BusinessException.class,
            MissingRequestHeaderException.class,
            HttpRequestMethodNotSupportedException.class,
            HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleException(RuntimeException e) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .message(e.getMessage())
                .code(HttpStatus.BAD_REQUEST.value())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = BusinessErrorsException.class)
    public ResponseEntity<Object> handleException(BusinessErrorsException e) {
        return ResponseEntity.badRequest().body(e.getErrors());
    }

}

