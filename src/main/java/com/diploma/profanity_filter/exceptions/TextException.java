package com.diploma.profanity_filter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class TextException extends RuntimeException{
    public TextException(String errorMsg){
        super("Text is too " + errorMsg);
    }
}
