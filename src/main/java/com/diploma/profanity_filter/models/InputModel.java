package com.diploma.profanity_filter.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
public class InputModel {

    @Getter
    @Setter
    private List<String> additionalDictionary;

    @Getter
    @Setter
    private String text;

}
