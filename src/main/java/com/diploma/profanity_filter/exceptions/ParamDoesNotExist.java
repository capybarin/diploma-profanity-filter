package com.diploma.profanity_filter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class ParamDoesNotExist extends RuntimeException{
    public ParamDoesNotExist(String paramName){
        super(paramName + " does not exist.");
    }
}
