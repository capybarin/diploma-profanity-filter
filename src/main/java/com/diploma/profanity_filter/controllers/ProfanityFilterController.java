package com.diploma.profanity_filter.controllers;

import com.diploma.profanity_filter.models.InputModel;
import com.diploma.profanity_filter.models.OutputModel;
import com.diploma.profanity_filter.models.WordInputModel;
import com.diploma.profanity_filter.models.WordOutputModel;
import com.diploma.profanity_filter.utils.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class ProfanityFilterController {

    @PostMapping(path = "/text/filter", consumes = "application/json", produces = "application/json")
    public OutputModel transformRequest(@RequestBody InputModel inputModel) {
        ModelValidator modelValidator = new ModelValidator();
        inputModel = modelValidator.validateInputModel(inputModel);

        PreProcessor preProcessor = new PreProcessor();
        preProcessor.appendCustomDictionary(inputModel);

        TextProcessor textProcessor = new TextProcessor();

        return textProcessor.processTranscribeWord(inputModel);
    }


    @PostMapping(path = "/text/words", consumes = "application/json", produces = "application/json")
    public WordOutputModel createWordWithReplacements(@RequestBody WordInputModel wordInputModel){

        WordReplacementModelValidator wordReplacementModelValidator = new WordReplacementModelValidator();
        wordInputModel = wordReplacementModelValidator.validateInputModel(wordInputModel);

        WordsCreator wordsCreator = new WordsCreator();

        return wordsCreator.processWordCreation(wordInputModel);
    }
}
