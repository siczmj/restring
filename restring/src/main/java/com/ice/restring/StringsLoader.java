package com.ice.restring;

import java.util.List;
import java.util.Map;

/**
 * Loader of strings skeleton. Clients can implement this interface if they want to load strings on initialization.
 * First the list of languages will be asked, then strings of each language.
 */
public interface StringsLoader {

    /**
     * Get supported languages.
     *
     * @return the list of languages.
     */
    List<String> getLanguages();

    /**
     * Get strings of a language as keys &amp; values.
     *
     * @param language of the strings.
     * @return the strings as (key, value).
     */
    Map<String, String> getStrings(String language);
}
