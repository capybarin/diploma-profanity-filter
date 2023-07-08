package com.diploma.profanity_filter.utils;

import com.diploma.profanity_filter.models.FoundProfanityDictModel;
import com.diploma.profanity_filter.models.InputModel;
import com.diploma.profanity_filter.models.OutputModel;
import com.diploma.profanity_filter.models.StaticDataInitModel;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class TextProcessor {

    private List<String> generateWordVariations(String word){
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


    public OutputModel processTranscribeWord(InputModel inputModel){
        OutputModel outputModel = new OutputModel();

        String[] wordsOfText = inputModel.getText().split("[-|\\.| |_|,|+|;|:]+"); //splitting is done no matter how many spaces are between words

        List<String> variations;
        List<String> wordsToBeReplacedFoundInGlobalDict = new ArrayList<>();
        Map<Integer, String> wordPositionToMatchMap = new HashMap<>();

        int wordIndex = -1;

        for (String word: wordsOfText) {
            if (StaticDataInitModel.globalDictionary.contains(word.toLowerCase()) ||
                    StaticDataInitModel.globalDictionary.contains(PluralsSingulars.singularize(word.toLowerCase()))) {

                wordIndex = inputModel.getText().indexOf(word, wordIndex+1);
                wordPositionToMatchMap.put(wordIndex, word);
                wordsToBeReplacedFoundInGlobalDict.add(word);
            } else {
                variations = generateWordVariations(word);
                if (!variations.isEmpty()){
                    wordIndex = inputModel.getText().indexOf(word, wordIndex+1);
                    wordPositionToMatchMap.put(wordIndex, variations.get(0));
                    wordsToBeReplacedFoundInGlobalDict.add(word);
                }
            }
        }

        StringBuffer resultText = new StringBuffer(inputModel.getText());
        for (String word : wordsToBeReplacedFoundInGlobalDict) {
            FoundProfanityDictModel foundProfanityDictModel = new FoundProfanityDictModel();
            foundProfanityDictModel.setOriginalWord(word);
            foundProfanityDictModel.setMatch(wordPositionToMatchMap.get(resultText.indexOf(word)));
            foundProfanityDictModel.setType("NO TYPE CURRENTLY");
            foundProfanityDictModel.setStartPos(resultText.indexOf(word));
            foundProfanityDictModel.setEndPos(resultText.indexOf(word)+word.length());
            outputModel.getFoundProfanity().add(foundProfanityDictModel);

            resultText = resultText.replace(resultText.indexOf(word), resultText.indexOf(word)+word.length(), StringUtils.repeat("*", word.length()));
        }

        String resultTextToString = resultText.toString();
        for (String word : StaticDataInitModel.customAdditionalDictionary) {
            if (Arrays.stream(wordsOfText).anyMatch(word::equals)) {
                FoundProfanityDictModel foundProfanityDictModel = new FoundProfanityDictModel();

                Pattern wordInText = Pattern.compile("\\b" + word + "\\b");
                Matcher matcherWordToString = wordInText.matcher(resultText);
                if (matcherWordToString.find()) {
                    foundProfanityDictModel.setStartPos(matcherWordToString.start());
                    foundProfanityDictModel.setEndPos(matcherWordToString.start() + word.length());
                }

                foundProfanityDictModel.setOriginalWord(word);
                foundProfanityDictModel.setMatch(word);
                foundProfanityDictModel.setType("custom");
                outputModel.getFoundProfanity().add(foundProfanityDictModel);

                //resultTextToString = resultTextToString.replaceAll("(?<!\\S)"+word+"(?!\\S)", StringUtils.repeat("*", word.length()));
                resultTextToString = resultTextToString.replaceAll("\\b" + word + "\\b", StringUtils.repeat("*", word.length()));
            }
        }
        StaticDataInitModel.customAdditionalDictionary.clear();

        outputModel.setAdditionalDictionary(inputModel.getAdditionalDictionary());
        outputModel.setFoundProfanity(outputModel.getFoundProfanity().stream().sorted(Comparator.comparing(FoundProfanityDictModel::getStartPos)).collect(Collectors.toList()));
        outputModel.setFound(outputModel.getFoundProfanity().size());
        outputModel.setTextCensoredSuggestion(resultTextToString);
        outputModel.setDate(LocalDateTime.now());
        outputModel.setUuid(UUID.randomUUID());

        return outputModel;
    }
}
