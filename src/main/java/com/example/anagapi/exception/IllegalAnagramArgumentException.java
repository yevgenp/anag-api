package com.example.anagapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IllegalAnagramArgumentException extends RuntimeException {
    public IllegalAnagramArgumentException(String message) {
        super(message);
    }
}
