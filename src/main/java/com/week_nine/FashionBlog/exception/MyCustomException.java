package com.week_nine.FashionBlog.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class MyCustomException extends RuntimeException {
    private final String message;
    private final LocalDateTime time = LocalDateTime.now();
    public MyCustomException (String message) {
        this.message = message;
    }
    public MyCustomException (String message, HttpStatus status){
        this.message = message;
    }
}
