package com.diploma.profanity_filter.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class WordOutputModel {

    @Getter
    @Setter
    private String response;

    @Getter
    @Setter
    private String message;
}
