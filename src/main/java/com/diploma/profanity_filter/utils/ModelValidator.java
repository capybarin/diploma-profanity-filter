package com.diploma.profanity_filter.utils;

import com.diploma.profanity_filter.exceptions.ParamDoesNotExist;
import com.diploma.profanity_filter.exceptions.TextException;
import com.diploma.profanity_filter.models.InputModel;

public class ModelValidator {

    public InputModel validateInputModel (InputModel inputModel){
        if (inputModel.getText() == null || inputModel.getText().isBlank()){
            throw new ParamDoesNotExist("text");
        } else if (inputModel.getText().length() > 160){
            throw  new TextException("is too long. Text length should not be greater then 160 characters.");
        }

        return inputModel;
    }
}
