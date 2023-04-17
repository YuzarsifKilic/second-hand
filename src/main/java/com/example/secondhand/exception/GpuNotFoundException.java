package com.example.secondhand.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GpuNotFoundException extends RuntimeException {

    public GpuNotFoundException(String message) {
        super(message);
    }
}
