package com.diploma.profanity_filter.utils;

import com.diploma.profanity_filter.models.StaticDataInitModel;
import com.diploma.profanity_filter.models.WordInputModel;
import com.diploma.profanity_filter.models.WordOutputModel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WordsCreator {

    private String createReplacementsForTheWord(int numOfReplacements, String baseWord, List<Integer> indicesToReplace){
        StringBuilder wordToReturn = new StringBuilder(baseWord);
        for (int i = 0; i < numOfReplacements; i++) {
            int indexOfCharToReplace = indicesToReplace.get(ThreadLocalRandom.current().nextInt(0, indicesToReplace.size()));
            int indexOfLetterToUse = ThreadLocalRandom.current().nextInt(1,
                    StaticDataInitModel.visuallySimilarCharacters.get(String.valueOf(baseWord.charAt(indexOfCharToReplace))).size());
            wordToReturn.setCharAt(indexOfCharToReplace, StaticDataInitModel.visuallySimilarCharacters
                    .get(String.valueOf(baseWord.charAt(indexOfCharToReplace)))
                    .toArray()[indexOfLetterToUse]
                    .toString()
                    .charAt(0));
            indicesToReplace.remove((Integer) indexOfCharToReplace);
        }

        return wordToReturn.toString();
    }

    public WordOutputModel processWordCreation(WordInputModel wordInputModel){
        WordOutputModel wordOutputModel = new WordOutputModel();

        int numLettersThatCanBeReplaced = 0;
        List<Integer> indicesThatCanBeReplaced = new ArrayList<>();

        for (int i = 0;i < wordInputModel.getBaseWord().length(); i++){
            if (StaticDataInitModel.visuallySimilarCharacters.get(String.valueOf(wordInputModel.getBaseWord().charAt(i))).size() <= 1){
                continue;
            }
            numLettersThatCanBeReplaced++;
            indicesThatCanBeReplaced.add(i);
        }
        if (indicesThatCanBeReplaced.size() == 0) {
            wordOutputModel.setMessage("Nothing can be done here.");
            return wordOutputModel;
        }


        if (wordInputModel.getMaxNumberOfReplacements() <= 0 || wordInputModel.getMaxNumberOfReplacements() > numLettersThatCanBeReplaced){
            wordOutputModel.setResponse(createReplacementsForTheWord(numLettersThatCanBeReplaced/2, wordInputModel.getBaseWord(), indicesThatCanBeReplaced));
        }
        if (wordInputModel.getMaxNumberOfReplacements() >=1 && wordInputModel.getMaxNumberOfReplacements() <= numLettersThatCanBeReplaced){
            wordOutputModel.setResponse(createReplacementsForTheWord(wordInputModel.getMaxNumberOfReplacements(), wordInputModel.getBaseWord(), indicesThatCanBeReplaced));
        }

        return wordOutputModel;
    }
}
