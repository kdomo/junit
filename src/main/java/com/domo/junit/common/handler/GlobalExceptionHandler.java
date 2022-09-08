package com.domo.junit.common.handler;

import com.domo.junit.common.ResultDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> apiException(RuntimeException e){
        return new ResponseEntity<>(ResultDto.builder().code(-1).msg(e.getMessage()).body("").build(), HttpStatus.BAD_REQUEST);
    }
}
