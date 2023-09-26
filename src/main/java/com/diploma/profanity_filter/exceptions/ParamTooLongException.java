package com.diploma.profanity_filter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ParamTooLongException extends RuntimeException{

    public ParamTooLongException(String errorMsg){
        super(errorMsg + " is too long");
    }
}
