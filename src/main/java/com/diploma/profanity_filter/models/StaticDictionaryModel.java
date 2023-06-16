package com.diploma.profanity_filter.models;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@ToString
@Component
public class StaticDictionaryModel implements ApplicationListener<ApplicationReadyEvent> {

    @Getter
    @Setter
    public static List<String> globalDictionary = new ArrayList<>();


    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        JSONParser parser = new JSONParser();
        Object filePath = parser.parse(new FileReader("src/main/resources/profanity_dictionary_base_en_json.json"));
        JSONArray dictArray = new JSONObject(filePath.toString()).getJSONArray("dictionary");
        for (int i=0; i<dictArray.length(); i++) {
            globalDictionary.add(dictArray.getString(i));
        }
    }
}
