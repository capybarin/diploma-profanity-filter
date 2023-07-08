package com.diploma.profanity_filter.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ToString
public class OutputModel {

    @Getter
    @Setter
    private UUID uuid;

    @Getter
    @Setter
    private LocalDateTime date;

    @Getter
    @Setter
    private String textCensoredSuggestion;

    @Getter
    @Setter
    private int found = 0;

    @Getter
    @Setter
    private List<FoundProfanityDictModel> foundProfanity = new ArrayList<>();
}
