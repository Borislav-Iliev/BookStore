package com.example.bookstore.web.rest;

import com.example.bookstore.model.dto.error.ErrorDto;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<List<ErrorDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ErrorDto> errors = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(er -> {
            errors.add(new ErrorDto()
                    .setStatus(HttpStatus.BAD_REQUEST.value())
                    .setMessage(er.getDefaultMessage())
                    .setTimestamp(LocalDateTime.now())
            );
        });

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }

    @ExceptionHandler({NoSuchElementException.class, TypeMismatchException.class})
    public ResponseEntity<ErrorDto> handleInvalidIdException(Exception ex) {
        ErrorDto error = new ErrorDto();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        error.setTimestamp(LocalDateTime.now());


        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }
}
