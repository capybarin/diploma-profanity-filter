package com.diploma.profanity_filter.models;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.ToString;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@ToString
@Component
public class StaticDictionaryModel implements ApplicationListener<ApplicationReadyEvent> {


    public static List<String> globalDictionary = new ArrayList<>();
    public static MultiValuedMap<String, String> visuallySimilarCharacters = new HashSetValuedHashMap<>();


    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //load dictionary
        JSONParser parser = new JSONParser();
        Object filePath = parser.parse(new FileReader("src/main/resources/profanity_dictionary_base_en_json.json"));
        JSONArray dictArray = new JSONObject(filePath.toString()).getJSONArray("dictionary");
        for (int i=0; i<dictArray.length(); i++) {
            globalDictionary.add(dictArray.getString(i));
        }

        //load charactersVisuallySimilar
        //lowerToLower
        //straight load
        visuallySimilarCharacters.put("e", "c");
        visuallySimilarCharacters.put("i", "j");
        visuallySimilarCharacters.put("g", "q");
        visuallySimilarCharacters.put("v", "w");
        visuallySimilarCharacters.put("o", "u");
        visuallySimilarCharacters.put("s", "z");
        visuallySimilarCharacters.put("g", "b");
        //reverse load
        visuallySimilarCharacters.put("c", "e");
        visuallySimilarCharacters.put("j", "i");
        visuallySimilarCharacters.put("q", "g");
        visuallySimilarCharacters.put("w", "v");
        visuallySimilarCharacters.put("u", "o");
        visuallySimilarCharacters.put("z", "s");
        visuallySimilarCharacters.put("b", "g");

        //lowerToNumber
        //straight load
        visuallySimilarCharacters.put("l", "1");
        visuallySimilarCharacters.put("e", "3");
        visuallySimilarCharacters.put("a", "4");
        visuallySimilarCharacters.put("b", "6");
        visuallySimilarCharacters.put("g", "9");
        visuallySimilarCharacters.put("q", "9");
        visuallySimilarCharacters.put("o", "0");
        //reverse load
        visuallySimilarCharacters.put("1", "l");
        visuallySimilarCharacters.put("3", "e");
        visuallySimilarCharacters.put("4", "a");
        visuallySimilarCharacters.put("6", "b");
        visuallySimilarCharacters.put("9", "g");
        visuallySimilarCharacters.put("9", "q");
        visuallySimilarCharacters.put("0", "o");

        //lowerToUpper
        //straight load
        visuallySimilarCharacters.put("l", "I");
        visuallySimilarCharacters.put("l", "J");
        visuallySimilarCharacters.put("i", "I");
        visuallySimilarCharacters.put("i", "J");
        //reverse load
        visuallySimilarCharacters.put("I", "l");
        visuallySimilarCharacters.put("J", "l");
        visuallySimilarCharacters.put("I", "i");
        visuallySimilarCharacters.put("J", "i");

        //upperToUpper
        //straight load
        visuallySimilarCharacters.put("C", "G");
        visuallySimilarCharacters.put("L", "I");
        visuallySimilarCharacters.put("I", "J");
        visuallySimilarCharacters.put("V", "W");
        visuallySimilarCharacters.put("S", "Z");
        visuallySimilarCharacters.put("O", "U");
        visuallySimilarCharacters.put("G", "B");
        //reverse load
        visuallySimilarCharacters.put("G", "C");
        visuallySimilarCharacters.put("I", "L");
        visuallySimilarCharacters.put("J", "I");
        visuallySimilarCharacters.put("W", "V");
        visuallySimilarCharacters.put("Z", "S");
        visuallySimilarCharacters.put("U", "O");
        visuallySimilarCharacters.put("B", "G");

        //upperToNumber
        //straight load
        visuallySimilarCharacters.put("I", "1");
        visuallySimilarCharacters.put("Z", "2");
        visuallySimilarCharacters.put("E", "3");
        visuallySimilarCharacters.put("A", "4");
        visuallySimilarCharacters.put("S", "5");
        visuallySimilarCharacters.put("G", "6");
        visuallySimilarCharacters.put("T", "7");
        visuallySimilarCharacters.put("B", "8");
        visuallySimilarCharacters.put("R", "9");
        visuallySimilarCharacters.put("O", "0");
        //reverse load
        visuallySimilarCharacters.put("1", "I");
        visuallySimilarCharacters.put("2", "Z");
        visuallySimilarCharacters.put("3", "E");
        visuallySimilarCharacters.put("4", "A");
        visuallySimilarCharacters.put("5", "S");
        visuallySimilarCharacters.put("6", "G");
        visuallySimilarCharacters.put("7", "T");
        visuallySimilarCharacters.put("8", "B");
        visuallySimilarCharacters.put("9", "R");
        visuallySimilarCharacters.put("0", "O");
    }
}
