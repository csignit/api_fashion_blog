package com.week_nine.FashionBlog.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class AllExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AllExceptionHandler.class);
    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<?> naMeDeyHandleException(ArithmeticException exception) {
        return ResponseEntity.badRequest().body(exception.getLocalizedMessage());
    }
    @ExceptionHandler(MyCustomException.class)
    public ResponseEntity<?> handlingMyOwnException(MyCustomException exception) {
        return ResponseEntity.badRequest().body(exception.getLocalizedMessage());
    }

        @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?>handleGlobalExceptions(MethodArgumentNotValidException ex, WebRequest webRequest){
        String[] errors = ex.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toArray(String[]::new);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

}

