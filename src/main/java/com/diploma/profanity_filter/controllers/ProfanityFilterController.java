package com.diploma.profanity_filter.controllers;

import com.diploma.profanity_filter.models.InputModel;
import com.diploma.profanity_filter.models.OutputModel;
import com.diploma.profanity_filter.utils.ModelValidator;
import com.diploma.profanity_filter.utils.PreProcessor;
import com.diploma.profanity_filter.utils.TextProcessor;
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
}
