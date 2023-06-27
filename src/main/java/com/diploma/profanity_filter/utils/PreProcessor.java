package com.diploma.profanity_filter.utils;

import com.diploma.profanity_filter.models.InputModel;
import com.diploma.profanity_filter.models.StaticDataInitModel;

public class PreProcessor {

    public String removeSpecialCharacters(InputModel inputModel){
        String resultText = inputModel.getText().replaceAll("[@#$\\\\/<>_`+|=\"№%^\\[\\]&*]", "");

        return resultText;
    }

    public void appendCustomDictionary(InputModel inputModel){
        if (!inputModel.getAdditionalDictionary().isEmpty()){
            StaticDataInitModel.customAdditionalDictionary.addAll(inputModel.getAdditionalDictionary());
        }
    }

}
