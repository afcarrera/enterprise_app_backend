package com.sicpa.enterprise_control.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormatException extends IllegalArgumentException{
    private static final long serialVersionUID = 1L;
    public FormatException(String message) {
        super(message);
    }
}
