package com.slowmedia0.PP1.web;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorMessage(
    int status,
    String message,
    LocalDateTime timestamp
){
    public ErrorMessage(String message, HttpStatus httpStatus){
        this(httpStatus.value(), message, LocalDateTime.now());
    }
}
