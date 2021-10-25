package com.example.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // to send 404 not found instead of 500
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
        System.out.println(message);


    }

}
