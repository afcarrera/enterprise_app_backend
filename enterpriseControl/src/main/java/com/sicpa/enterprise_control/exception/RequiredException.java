package com.sicpa.enterprise_control.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequiredException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public RequiredException(String message) {
        super(message);
    }
}
