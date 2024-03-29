package com.diploma.profanity_filter.utils;

import com.diploma.profanity_filter.models.FoundProfanityDictModel;
import com.diploma.profanity_filter.models.InputModel;
import com.diploma.profanity_filter.models.OutputModel;
import com.diploma.profanity_filter.models.StaticDataInitModel;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class TextProcessor {

    private int getLevenshteinDist(String inputWord, String dictWord){
        return LevenshteinDistance.getDefaultInstance().apply(inputWord, dictWord);
    }
    private List<String> generateWordVariations(String word){
        List<String> variations = new ArrayList<>();
        generateWordVariationsHelper(word.toLowerCase(), "", 0, variations);

        return variations;
    }
    private void generateWordVariationsHelper(String word, String currentWord, int index, List<String> variations){
        if (index == word.length()){

            if (StaticDataInitModel.globalDictionary.contains(PluralsSingulars.singularize(currentWord))){
                variations.add(currentWord);
                System.out.println(variations);
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

    private String getWordTypeByDictionary(String word){
        if (word == null || word.isEmpty()) return "global";
        if (StaticDataInitModel.adultDictionary.contains(PluralsSingulars.singularize(word.toLowerCase())))
            return "adult";
        if (StaticDataInitModel.insultDictionary.contains(PluralsSingulars.singularize(word.toLowerCase())))
            return "insult";
        if (StaticDataInitModel.intoleranceDictionary.contains(PluralsSingulars.singularize(word.toLowerCase())))
            return "intolerance";
        return "global";
    }

    public OutputModel processTranscribeWord(InputModel inputModel){
        OutputModel outputModel = new OutputModel();

        String[] wordsOfText = inputModel.getText().split("[-|\\.| |_|,|!|?|+|;|:|\n|]+"); //splitting is done no matter how many spaces are between words

        List<String> variations;
        List<String> wordsToBeReplacedFoundInGlobalDict = new ArrayList<>();
        Map<Integer, String> wordPositionToMatchMap = new HashMap<>();

        List<String> wordsAfterLevenshteinDist = new ArrayList<>(Arrays.stream(wordsOfText).map(word -> word.toLowerCase()).collect(Collectors.toList()));
        List<String> alikeWordPairs = new ArrayList<>();
        for (int i = 0; i < wordsAfterLevenshteinDist.size(); i++) {
            alikeWordPairs.clear();
            for (String dictWord : StaticDataInitModel.globalDictionary) {
                int maxNumberOfWordEdits = (wordsAfterLevenshteinDist.get(i).length() > dictWord.length()) ? wordsAfterLevenshteinDist.get(i).length() / 2 : dictWord.length() / 2;
                if (getLevenshteinDist(wordsAfterLevenshteinDist.get(i), dictWord) < maxNumberOfWordEdits) {
                    alikeWordPairs.add(dictWord);
                }
            }

            if (alikeWordPairs.size() == 0) {
                wordsAfterLevenshteinDist.remove(i);
                i--;
            }
        }

        wordsOfText = wordsAfterLevenshteinDist.toArray(new String[0]);
        int wordIndex = -1;
        int foundHidden = 0;
        int foundObvious = 0;

        for (String word: wordsOfText) {
            if (StaticDataInitModel.globalDictionary.contains(word.toLowerCase()) ||
                    StaticDataInitModel.globalDictionary.contains(PluralsSingulars.singularize(word.toLowerCase()))) {

                wordIndex = inputModel.getText().toLowerCase().indexOf(word, wordIndex+1);
                wordPositionToMatchMap.put(wordIndex, PluralsSingulars.singularize(word));
                wordsToBeReplacedFoundInGlobalDict.add(word);
                foundObvious++;
            } else {
                variations = generateWordVariations(word);
                if (!variations.isEmpty()){
                    wordIndex = inputModel.getText().toLowerCase().indexOf(word, wordIndex+1);
                    wordPositionToMatchMap.put(wordIndex, PluralsSingulars.singularize(variations.get(0)));
                    wordsToBeReplacedFoundInGlobalDict.add(word);
                    foundHidden++;
                }
            }
        }
        StringBuffer resultText = new StringBuffer(inputModel.getText());
        for (String word : wordsToBeReplacedFoundInGlobalDict) {
            FoundProfanityDictModel foundProfanityDictModel = new FoundProfanityDictModel();
            foundProfanityDictModel.setOriginalWord(word);
            foundProfanityDictModel.setMatch(wordPositionToMatchMap.get(resultText.toString().toLowerCase().indexOf(word)));
            foundProfanityDictModel.setType(getWordTypeByDictionary(wordPositionToMatchMap.get(resultText.toString().toLowerCase().indexOf(word))));
            foundProfanityDictModel.setStartPos(resultText.toString().toLowerCase().indexOf(word));
            foundProfanityDictModel.setEndPos(resultText.toString().toLowerCase().indexOf(word)+word.length()-1);
            outputModel.getFoundProfanity().add(foundProfanityDictModel);

            resultText = resultText.replace(resultText.toString().toLowerCase().indexOf(word), resultText.toString().toLowerCase().indexOf(word)+word.length(), StringUtils.repeat("*", word.length()));
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
        outputModel.setFoundHiddenWords(foundHidden);
        outputModel.setFoundObviousWords(foundObvious);
        outputModel.setTextCensoredSuggestion(resultTextToString);
        outputModel.setDate(LocalDateTime.now());
        outputModel.setUuid(UUID.randomUUID());

        return outputModel;
    }
}
