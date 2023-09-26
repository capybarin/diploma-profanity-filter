package com.diploma.profanity_filter.utils;

import com.diploma.profanity_filter.exceptions.ParamDoesNotExist;
import com.diploma.profanity_filter.exceptions.ParamTooLongException;
import com.diploma.profanity_filter.models.WordInputModel;

public class WordReplacementModelValidator {

    public WordInputModel validateInputModel(WordInputModel wordInputModel){
        if (wordInputModel.getBaseWord() == null || wordInputModel.getBaseWord().isBlank())
            throw new ParamDoesNotExist("baseWord");
        if (wordInputModel.getBaseWord().length() > 15){
            throw new ParamTooLongException("baseWord");
        }
        if (wordInputModel.getNumberOfWordsToReturn() > 10){
            throw new ParamTooLongException("numberOfWordsToReturn");
        }

        return wordInputModel;
    }
}
