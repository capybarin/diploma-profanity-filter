package com.diploma.profanity_filter.utils;

import com.diploma.profanity_filter.models.InputModel;
import com.diploma.profanity_filter.models.StaticDataInitModel;


public class TextProcessor {

    private void generateWordVariations(String word, String currentWord, int index){
        if (index == word.length()){
            System.out.println("current word: "+currentWord + " is in list? " + StaticDataInitModel.globalDictionary.contains(currentWord.toLowerCase()));

            return;
        }

        char currentLetter = word.charAt(index);
        if (!StaticDataInitModel.visuallySimilarCharacters.get(String.valueOf(currentLetter)).isEmpty()) {
            for (String letter : StaticDataInitModel.visuallySimilarCharacters.get(String.valueOf(currentLetter))) {
                generateWordVariations(word, currentWord + letter, index + 1);
            }
        } else generateWordVariations(word, currentWord + currentLetter, index + 1);

    }

    public void processTranscribeWord(InputModel inputModel){
        String[] wordsOfText = inputModel.getText().split("\\s+"); //splitting is done no matter how many spaces are between words
        for (String word: wordsOfText) {
            if (StaticDataInitModel.globalDictionary.contains(word.toLowerCase())) {
                System.out.println("word in list "+word);
            } else {generateWordVariations(word, "", 0);}
        }


        /*for (char c: inputModel.getText().toCharArray()) {
            System.out.println(c + ": "+StaticDictionaryModel.visuallySimilarCharacters.get(String.valueOf(c)).toArray().length);
            for (int i = 0; i < StaticDictionaryModel.visuallySimilarCharacters.get(String.valueOf(c)).toArray().length; i++) {
                System.out.println("   "+ StaticDictionaryModel.visuallySimilarCharacters.get(String.valueOf(c)).toArray()[i]);
            }
            charIter++;
        }*/
    }
}
