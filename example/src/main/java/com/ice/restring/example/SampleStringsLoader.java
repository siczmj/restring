package com.ice.restring.example;

import com.ice.restring.StringsLoader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This is just a really simple sample of strings loader.
 * in real applications, you might call an API to get your strings.
 * <p>
 * All overridden methods will be called on background thread.
 */
public class SampleStringsLoader implements StringsLoader {

    @Override
    public List<String> getLanguages() {
        return Arrays.asList("en", "de", "fa", "ru");
    }

    @Override
    public Map<String, String> getStrings(String language) {
        Map<String, String> map = new HashMap<>();
        switch (language) {
            case "en": {
                map.put("title", "This is title (from restring).");
                map.put("subtitle", "This is subtitle (from restring).");
                break;
            }
            case "de": {
                map.put("title", "Das ist Titel (from restring).");
                map.put("subtitle", "Das ist Untertitel (from restring).");
                break;
            }
            case "fa": {
                map.put("title", "In sarkhat ast (from restring).");
                map.put("subtitle", "In matn ast (from restring).");
                break;
            }
            case "ru": {
                map.put("title", "Это заголовок (из restring).");
                map.put("subtitle", "Это подзаголовок (из restring).");
                break;
            }
        }
        return map;
    }
}
