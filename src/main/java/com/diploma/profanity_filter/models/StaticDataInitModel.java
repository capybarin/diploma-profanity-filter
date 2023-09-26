package com.diploma.profanity_filter.models;

import lombok.SneakyThrows;
import lombok.ToString;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


@ToString
@Component
public class StaticDataInitModel implements ApplicationListener<ApplicationReadyEvent> {

    public static List<String> insultDictionary = new ArrayList<>();
    public static List<String> adultDictionary = new ArrayList<>();
    public static List<String> intoleranceDictionary = new ArrayList<>();
    public static List<String> globalDictionary = new ArrayList<>();
    public static List<String> customAdditionalDictionary = new ArrayList<>();
    public static MultiValuedMap<String, String> visuallySimilarCharacters = new ArrayListValuedHashMap<>();


    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        //load dictionary
        JSONParser parser = new JSONParser();
        Object filePath = parser.parse(new FileReader("src/main/resources/profanity_dictionary_base_en_json.json"));
        JSONArray dictArray = new JSONObject(filePath.toString()).getJSONArray("dictionary");
        JSONArray insultDict = new JSONObject(filePath.toString()).getJSONArray("insult");
        JSONArray adultDict = new JSONObject(filePath.toString()).getJSONArray("adult");
        JSONArray intoleranceDict = new JSONObject(filePath.toString()).getJSONArray("intolerance");

        dictArray.putAll(insultDict);
        dictArray.putAll(adultDict);
        dictArray.putAll(intoleranceDict);

        for (int i=0; i<dictArray.length(); i++) {
            globalDictionary.add(dictArray.getString(i));
        }

        for (int i=0; i<adultDict.length(); i++) {
            adultDictionary.add(adultDict.getString(i));
        }

        for (int i=0; i<intoleranceDict.length(); i++) {
            intoleranceDictionary.add(intoleranceDict.getString(i));
        }

        for (int i=0; i<insultDict.length(); i++) {
            insultDictionary.add(insultDict.getString(i));
        }

        //lowerToNumber
        visuallySimilarCharacters.put("1", "l");
        visuallySimilarCharacters.put("1", "i");
        visuallySimilarCharacters.put("3", "e");
        visuallySimilarCharacters.put("4", "a");
        visuallySimilarCharacters.put("6", "b");
        visuallySimilarCharacters.put("7", "t");
        visuallySimilarCharacters.put("9", "g");
        visuallySimilarCharacters.put("9", "q");
        visuallySimilarCharacters.put("0", "o");
        visuallySimilarCharacters.put("0", "u");

        //upperToNumber
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
        visuallySimilarCharacters.put("0", "U");

        //load THE WHOLE FUCKING ALPHABET
        visuallySimilarCharacters.put("a", "a"); //1
        visuallySimilarCharacters.put("b", "b"); //1
        visuallySimilarCharacters.put("b", "h"); //0,429
        visuallySimilarCharacters.put("b", "p"); //0,419
        visuallySimilarCharacters.put("b", "q"); //0,386
        visuallySimilarCharacters.put("b", "g"); //0,129
        visuallySimilarCharacters.put("c", "c"); //1
        visuallySimilarCharacters.put("c", "e"); //0,341
        visuallySimilarCharacters.put("d", "d"); //1
        visuallySimilarCharacters.put("e", "e"); //1
        visuallySimilarCharacters.put("e", "c"); //0,341
        visuallySimilarCharacters.put("f", "f"); //1
        visuallySimilarCharacters.put("g", "g"); //1
        visuallySimilarCharacters.put("g", "q"); //0,241
        visuallySimilarCharacters.put("g", "b"); //0,129
        visuallySimilarCharacters.put("h", "h"); //1
        visuallySimilarCharacters.put("h", "b"); //0,429
        visuallySimilarCharacters.put("i", "i"); //1
        visuallySimilarCharacters.put("i", "I"); //0,5
        visuallySimilarCharacters.put("i", "J"); //0,5
        visuallySimilarCharacters.put("i", "l"); //0,356
        visuallySimilarCharacters.put("i", "j"); //0,339
        visuallySimilarCharacters.put("j", "j"); //1
        visuallySimilarCharacters.put("j", "i"); //0,339
        visuallySimilarCharacters.put("k", "k"); //1
        visuallySimilarCharacters.put("l", "l"); //1
        visuallySimilarCharacters.put("l", "I"); //0,5
        visuallySimilarCharacters.put("l", "J"); //0,5
        visuallySimilarCharacters.put("l", "i"); //0,356
        visuallySimilarCharacters.put("m", "m"); //1
        visuallySimilarCharacters.put("n", "n"); //1
        visuallySimilarCharacters.put("o", "o"); //1
        visuallySimilarCharacters.put("o", "u"); //0,269
        visuallySimilarCharacters.put("p", "p"); //1
        visuallySimilarCharacters.put("p", "b"); //0,419
        visuallySimilarCharacters.put("q", "q"); //1
        visuallySimilarCharacters.put("q", "b"); //0,386
        visuallySimilarCharacters.put("q", "g"); //0,241
        visuallySimilarCharacters.put("r", "r"); //1
        visuallySimilarCharacters.put("s", "s"); //1
        visuallySimilarCharacters.put("s", "z"); //0,251
        visuallySimilarCharacters.put("t", "t"); //1
        visuallySimilarCharacters.put("u", "u"); //1
        visuallySimilarCharacters.put("u", "o"); //0,269
        visuallySimilarCharacters.put("v", "v"); //1
        visuallySimilarCharacters.put("v", "w"); //0,287
        visuallySimilarCharacters.put("w", "w"); //1
        visuallySimilarCharacters.put("w", "v"); //0,287
        visuallySimilarCharacters.put("x", "x"); //1
        visuallySimilarCharacters.put("y", "y"); //1
        visuallySimilarCharacters.put("z", "z"); //1
        visuallySimilarCharacters.put("z", "s"); //0,251


        visuallySimilarCharacters.put("A", "A"); //1
        visuallySimilarCharacters.put("B", "B"); //1
        visuallySimilarCharacters.put("B", "D"); //0,384
        visuallySimilarCharacters.put("B", "G"); //0,325
        visuallySimilarCharacters.put("C", "C"); //1
        visuallySimilarCharacters.put("C", "G"); //0,369
        visuallySimilarCharacters.put("C", "O"); //0,366
        visuallySimilarCharacters.put("D", "D"); //1
        visuallySimilarCharacters.put("D", "B"); //0,384
        visuallySimilarCharacters.put("D", "O"); //0,377
        visuallySimilarCharacters.put("E", "E"); //1
        visuallySimilarCharacters.put("F", "F"); //1
        visuallySimilarCharacters.put("F", "P"); //0,384
        visuallySimilarCharacters.put("G", "G"); //1
        visuallySimilarCharacters.put("G", "O"); //0,406
        visuallySimilarCharacters.put("G", "C"); //0,369
        visuallySimilarCharacters.put("H", "H"); //1
        visuallySimilarCharacters.put("H", "N"); //0,405
        visuallySimilarCharacters.put("I", "I"); //1
        visuallySimilarCharacters.put("I", "l"); //0,5
        visuallySimilarCharacters.put("I", "i"); //0,5
        visuallySimilarCharacters.put("I", "J"); //0,353
        visuallySimilarCharacters.put("I", "L"); //0,244
        visuallySimilarCharacters.put("J", "J"); //1
        visuallySimilarCharacters.put("J", "l"); //0,5
        visuallySimilarCharacters.put("J", "i"); //0,5
        visuallySimilarCharacters.put("J", "I"); //0,353
        visuallySimilarCharacters.put("K", "K"); //1
        visuallySimilarCharacters.put("L", "L"); //1
        visuallySimilarCharacters.put("L", "I"); //0,244
        visuallySimilarCharacters.put("M", "M"); //1
        visuallySimilarCharacters.put("N", "N"); //1
        visuallySimilarCharacters.put("N", "H"); //0,405
        visuallySimilarCharacters.put("O", "O"); //1
        visuallySimilarCharacters.put("O", "G"); //0,406
        visuallySimilarCharacters.put("O", "D"); //0,377
        visuallySimilarCharacters.put("O", "C"); //0,366
        visuallySimilarCharacters.put("O", "U"); //0,360
        visuallySimilarCharacters.put("P", "P"); //1
        visuallySimilarCharacters.put("P", "F"); //0,384
        visuallySimilarCharacters.put("Q", "Q"); //1
        visuallySimilarCharacters.put("R", "R"); //1
        visuallySimilarCharacters.put("S", "S"); //1
        visuallySimilarCharacters.put("S", "Z"); //0,280
        visuallySimilarCharacters.put("T", "T"); //1
        visuallySimilarCharacters.put("U", "U"); //1
        visuallySimilarCharacters.put("U", "O"); //0,360
        visuallySimilarCharacters.put("V", "V"); //1
        visuallySimilarCharacters.put("V", "Y"); //0,401
        visuallySimilarCharacters.put("V", "W"); //0,243
        visuallySimilarCharacters.put("W", "W"); //1
        visuallySimilarCharacters.put("W", "V"); //0,243
        visuallySimilarCharacters.put("X", "X"); //1
        visuallySimilarCharacters.put("Y", "Y"); //1
        visuallySimilarCharacters.put("Y", "V"); //0,401
        visuallySimilarCharacters.put("Z", "Z"); //1
        visuallySimilarCharacters.put("Z", "S"); //0,280

        System.out.println(visuallySimilarCharacters);
    }
}
