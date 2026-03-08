package com.sweet.security.handler;

import com.sweet.common.response.ResponseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleGenericException(Exception e) {
        log.error("Exception: {}", e);
        return ResponseEntity.fail(e.getMessage());
    }

}
