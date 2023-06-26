package com.diploma.profanity_filter.utils;

import com.diploma.profanity_filter.models.InputModel;
import com.diploma.profanity_filter.models.StaticDataInitModel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;


public class TextProcessor {

    public List<String> generateWordVariations(String word){
        List<String> variations = new ArrayList<>();
        generateWordVariationsHelper(word.toLowerCase(), "", 0, variations);

        return variations;
    }
    private void generateWordVariationsHelper(String word, String currentWord, int index, List<String> variations){
        if (index == word.length()){
            if (StaticDataInitModel.globalDictionary.contains(PluralsSingulars.singularize(currentWord))){
                variations.add(currentWord);
                return;
            }
            return;
        }

        char currentLetter = word.charAt(index);
        if (!StaticDataInitModel.visuallySimilarCharacters.get(String.valueOf(currentLetter)).isEmpty()) {
            for (String letter : StaticDataInitModel.visuallySimilarCharacters.get(String.valueOf(currentLetter))) {
                generateWordVariationsHelper(word, currentWord + letter, index + 1, variations);
            }
        } else generateWordVariationsHelper(word, currentWord + currentLetter, index + 1, variations);

    }

    public void processTranscribeWord(InputModel inputModel){

        String[] wordsOfText = inputModel.getText().split("\\s+"); //splitting is done no matter how many spaces are between words
        List<String> variations;
        List<String> wordsToBeReplaced = new ArrayList<>();

        for (String word: wordsOfText) {
            if (StaticDataInitModel.globalDictionary.contains(word.toLowerCase()) || StaticDataInitModel.globalDictionary.contains(PluralsSingulars.singularize(word.toLowerCase()))) {
                wordsToBeReplaced.add(word);
            } else {
                variations = generateWordVariations(word);
                if (!variations.isEmpty()){
                    wordsToBeReplaced.add(word);
                } else continue;
            }
        }

        for (String word : wordsToBeReplaced) {
            inputModel.setText(inputModel.getText().replaceAll(word, StringUtils.repeat("*", word.length())));
        }

    }
}
