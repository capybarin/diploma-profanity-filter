package com.diploma.profanity_filter.controllers;

import com.diploma.profanity_filter.models.InputModel;
import com.diploma.profanity_filter.models.StaticDictionaryModel;
import com.diploma.profanity_filter.utils.ModelValidator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileReader;
import java.io.IOException;

@RestController
@CrossOrigin
public class ProfanityFilterController {

    @PostMapping(path = "/text/filter", consumes = "application/json", produces = "application/json")
    public InputModel test(@RequestBody InputModel inputModel) throws IOException, ParseException {
        ModelValidator modelValidator = new ModelValidator();
        inputModel = modelValidator.validateInputModel(inputModel);
        return inputModel;
    }
}
