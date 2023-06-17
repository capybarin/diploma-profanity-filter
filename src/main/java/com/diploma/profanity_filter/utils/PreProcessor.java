package com.diploma.profanity_filter.utils;

import com.diploma.profanity_filter.models.InputModel;

import java.util.List;

public class PreProcessor {

    public String removeSpecialCharacters(InputModel inputModel){
        String resultText = inputModel.getText().replaceAll("[@#$\\\\/<>_`+|=\"№%^\\[\\]&*]", "");

        return resultText;
    }

    public List<String> appendCustomDictionary (InputModel inputModel){

        return null;
    }

}
