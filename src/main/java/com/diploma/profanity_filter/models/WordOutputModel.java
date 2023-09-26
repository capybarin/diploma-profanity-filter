package com.diploma.profanity_filter.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class WordOutputModel {

    @Getter
    @Setter
    private List<String> response = new ArrayList<>();
}
