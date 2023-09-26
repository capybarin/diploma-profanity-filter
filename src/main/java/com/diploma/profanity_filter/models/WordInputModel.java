package com.diploma.profanity_filter.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class WordInputModel {

    @Getter
    @Setter
    private String baseWord;

    @Getter
    @Setter
    private int numberOfWordsToReturn = 1;

    @Getter
    @Setter
    private int maxNumberOfReplacements;
}
