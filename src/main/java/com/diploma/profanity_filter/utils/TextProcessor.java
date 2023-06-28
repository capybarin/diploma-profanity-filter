package com.diploma.profanity_filter.utils;

import com.diploma.profanity_filter.models.InputModel;
import com.diploma.profanity_filter.models.OutputModel;
import com.diploma.profanity_filter.models.StaticDataInitModel;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class TextProcessor {

    private List<String> generateWordVariations(String word){
        List<String> variations = new ArrayList<>();
        generateWordVariationsHelper(word.toLowerCase(), "", 0, variations);

        return variations;
    }
    private void generateWordVariationsHelper(String word, String currentWord, int index, List<String> variations){
        if (index == word.length()){
            if (StaticDataInitModel.globalDictionary.contains(PluralsSingulars.singularize(currentWord)) ||
                    StaticDataInitModel.customAdditionalDictionary.contains(PluralsSingulars.singularize(currentWord))){
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

    public OutputModel processTranscribeWord(InputModel inputModel){
        OutputModel outputModel = new OutputModel();

        String[] wordsOfText = inputModel.getText().split("\\s+"); //splitting is done no matter how many spaces are between words
        List<String> variations;
        List<String> wordsToBeReplaced = new ArrayList<>();

        for (String word: wordsOfText) {
            System.out.println(word);
            if (StaticDataInitModel.globalDictionary.contains(word.toLowerCase()) ||
                    StaticDataInitModel.globalDictionary.contains(PluralsSingulars.singularize(word.toLowerCase())) ||
                    StaticDataInitModel.customAdditionalDictionary.contains(word)) {
                wordsToBeReplaced.add(word);
            } else {
                variations = generateWordVariations(word);
                if (!variations.isEmpty()){
                    wordsToBeReplaced.add(word);
                }
            }
        }

        for (String word : wordsToBeReplaced) {
            System.out.println(word);
            inputModel.setText(inputModel.getText().replaceAll("\\b"+word+"\\b", StringUtils.repeat("*", word.length())));
        }

        StaticDataInitModel.customAdditionalDictionary.clear();

        outputModel.setTextCensored(inputModel.getText());
        outputModel.setDate(LocalDateTime.now());
        outputModel.setUuid(UUID.randomUUID());

        return outputModel;
    }
}
