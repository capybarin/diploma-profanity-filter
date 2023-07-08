package com.diploma.profanity_filter.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class FoundProfanityDictModel {

    @Getter
    @Setter
    private String originalWord;

    @Getter
    @Setter
    private String match;

    @Getter
    @Setter
    private String type;

    @Getter
    @Setter
    private int startPos = -1;

    @Getter
    @Setter
    private int endPos = -1;
}
