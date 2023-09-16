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

        //load charactersVisuallySimilar
        //lowerToLower
        //straight load
        visuallySimilarCharacters.put("b", "q"); //0,386
        visuallySimilarCharacters.put("h", "b"); //0,429
        visuallySimilarCharacters.put("b", "p"); //0,419
        visuallySimilarCharacters.put("e", "c"); //0,341
        visuallySimilarCharacters.put("i", "j"); //0,339
        visuallySimilarCharacters.put("i", "l"); //0,356
        visuallySimilarCharacters.put("g", "q"); //0,241
        visuallySimilarCharacters.put("v", "w"); //0,287
        visuallySimilarCharacters.put("o", "u"); //0,269
        visuallySimilarCharacters.put("s", "z"); //0,251
        visuallySimilarCharacters.put("g", "b"); //0,129
        //reverse load
        visuallySimilarCharacters.put("q", "b"); //0,386
        visuallySimilarCharacters.put("b", "h"); //0,429
        visuallySimilarCharacters.put("p", "b"); //0,419
        visuallySimilarCharacters.put("c", "e"); //0,341
        visuallySimilarCharacters.put("j", "i"); //0,339
        visuallySimilarCharacters.put("l", "i"); //0,356
        visuallySimilarCharacters.put("q", "g"); //0,241
        visuallySimilarCharacters.put("w", "v"); //0,287
        visuallySimilarCharacters.put("u", "o"); //0,269
        visuallySimilarCharacters.put("z", "s"); //0,251
        visuallySimilarCharacters.put("b", "g"); //0,129

        //lowerToNumber
        //straight load
        /*visuallySimilarCharacters.put("l", "1");
        visuallySimilarCharacters.put("i", "1");
        visuallySimilarCharacters.put("e", "3");
        visuallySimilarCharacters.put("a", "4");
        visuallySimilarCharacters.put("b", "6");
        visuallySimilarCharacters.put("t", "7");
        visuallySimilarCharacters.put("g", "9");
        visuallySimilarCharacters.put("q", "9");
        visuallySimilarCharacters.put("o", "0");
        visuallySimilarCharacters.put("u", "0");*/
        //reverse load
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
        visuallySimilarCharacters.put("C", "G"); //0,369
        visuallySimilarCharacters.put("C", "O"); //0,366
        visuallySimilarCharacters.put("D", "O"); //0,377
        visuallySimilarCharacters.put("D", "B"); //0,384
        visuallySimilarCharacters.put("F", "P"); //0,384
        visuallySimilarCharacters.put("G", "O"); //0,406
        visuallySimilarCharacters.put("H", "N"); //0,405
        visuallySimilarCharacters.put("L", "I"); //0,244
        visuallySimilarCharacters.put("I", "J"); //0,353
        visuallySimilarCharacters.put("V", "W"); //0,243
        visuallySimilarCharacters.put("V", "Y"); //0,401
        visuallySimilarCharacters.put("S", "Z"); //0,280
        visuallySimilarCharacters.put("O", "U"); //0,360
        visuallySimilarCharacters.put("G", "B"); //0,325
        //reverse load
        visuallySimilarCharacters.put("G", "C"); //0,369
        visuallySimilarCharacters.put("O", "C"); //0,366
        visuallySimilarCharacters.put("O", "D"); //0,377
        visuallySimilarCharacters.put("B", "D"); //0,384
        visuallySimilarCharacters.put("P", "F"); //0,384
        visuallySimilarCharacters.put("O", "G"); //0,406
        visuallySimilarCharacters.put("N", "H"); //0,405
        visuallySimilarCharacters.put("I", "L"); //0,244
        visuallySimilarCharacters.put("J", "I"); //0,353
        visuallySimilarCharacters.put("W", "V"); //0,243
        visuallySimilarCharacters.put("Y", "V"); //0,401
        visuallySimilarCharacters.put("Z", "S"); //0,280
        visuallySimilarCharacters.put("U", "O"); //0,360
        visuallySimilarCharacters.put("B", "G"); //0,325

        //upperToNumber
        //straight load
        /*visuallySimilarCharacters.put("I", "1");
        visuallySimilarCharacters.put("Z", "2");
        visuallySimilarCharacters.put("E", "3");
        visuallySimilarCharacters.put("A", "4");
        visuallySimilarCharacters.put("S", "5");
        visuallySimilarCharacters.put("G", "6");
        visuallySimilarCharacters.put("T", "7");
        visuallySimilarCharacters.put("B", "8");
        visuallySimilarCharacters.put("R", "9");
        visuallySimilarCharacters.put("O", "0");
        visuallySimilarCharacters.put("U", "0");*/
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
        visuallySimilarCharacters.put("0", "U");

        //load THE WHOLE FUCKING ALPHABET
        visuallySimilarCharacters.put("a", "a");
        visuallySimilarCharacters.put("b", "b");
        visuallySimilarCharacters.put("c", "c");
        visuallySimilarCharacters.put("d", "d");
        visuallySimilarCharacters.put("e", "e");
        visuallySimilarCharacters.put("f", "f");
        visuallySimilarCharacters.put("g", "g");
        visuallySimilarCharacters.put("h", "h");
        visuallySimilarCharacters.put("i", "i");
        visuallySimilarCharacters.put("j", "j");
        visuallySimilarCharacters.put("k", "k");
        visuallySimilarCharacters.put("l", "l");
        visuallySimilarCharacters.put("m", "m");
        visuallySimilarCharacters.put("n", "n");
        visuallySimilarCharacters.put("o", "o");
        visuallySimilarCharacters.put("p", "p");
        visuallySimilarCharacters.put("q", "q");
        visuallySimilarCharacters.put("r", "r");
        visuallySimilarCharacters.put("s", "s");
        visuallySimilarCharacters.put("t", "t");
        visuallySimilarCharacters.put("u", "u");
        visuallySimilarCharacters.put("v", "v");
        visuallySimilarCharacters.put("w", "w");
        visuallySimilarCharacters.put("x", "x");
        visuallySimilarCharacters.put("y", "y");
        visuallySimilarCharacters.put("z", "z");

        visuallySimilarCharacters.put("A", "A");
        visuallySimilarCharacters.put("B", "B");
        visuallySimilarCharacters.put("C", "C");
        visuallySimilarCharacters.put("D", "D");
        visuallySimilarCharacters.put("E", "E");
        visuallySimilarCharacters.put("F", "F");
        visuallySimilarCharacters.put("G", "G");
        visuallySimilarCharacters.put("H", "H");
        visuallySimilarCharacters.put("I", "I");
        visuallySimilarCharacters.put("J", "J");
        visuallySimilarCharacters.put("K", "K");
        visuallySimilarCharacters.put("L", "L");
        visuallySimilarCharacters.put("M", "M");
        visuallySimilarCharacters.put("N", "N");
        visuallySimilarCharacters.put("O", "O");
        visuallySimilarCharacters.put("P", "P");
        visuallySimilarCharacters.put("Q", "Q");
        visuallySimilarCharacters.put("R", "R");
        visuallySimilarCharacters.put("S", "S");
        visuallySimilarCharacters.put("T", "T");
        visuallySimilarCharacters.put("U", "U");
        visuallySimilarCharacters.put("V", "V");
        visuallySimilarCharacters.put("W", "W");
        visuallySimilarCharacters.put("X", "X");
        visuallySimilarCharacters.put("Y", "Y");
        visuallySimilarCharacters.put("Z", "Z");

        System.out.println(visuallySimilarCharacters);
        /*visuallySimilarCharacters.put("1", "1");
        visuallySimilarCharacters.put("2", "2");
        visuallySimilarCharacters.put("3", "3");
        visuallySimilarCharacters.put("4", "4");
        visuallySimilarCharacters.put("5", "5");
        visuallySimilarCharacters.put("6", "6");
        visuallySimilarCharacters.put("7", "7");
        visuallySimilarCharacters.put("8", "8");
        visuallySimilarCharacters.put("9", "9");
        visuallySimilarCharacters.put("0", "0");*/
    }
}
