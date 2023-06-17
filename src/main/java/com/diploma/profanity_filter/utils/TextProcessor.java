package com.diploma.profanity_filter.utils;

import com.diploma.profanity_filter.models.InputModel;
import com.diploma.profanity_filter.models.StaticDictionaryModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextProcessor {

    public void processTranscribeWord(InputModel inputModel){
        List<String> possibleWords = new ArrayList<>();
        int charIter = 0;
        for (char c: inputModel.getText().toCharArray()) {
            System.out.println(c + ": "+StaticDictionaryModel.visuallySimilarCharacters.get(String.valueOf(c)).toArray().length);
            for (int i = 0; i < StaticDictionaryModel.visuallySimilarCharacters.get(String.valueOf(c)).toArray().length; i++) {
                System.out.println("   "+ StaticDictionaryModel.visuallySimilarCharacters.get(String.valueOf(c)).toArray()[i]);
            }
            charIter++;
        }
    }
}
