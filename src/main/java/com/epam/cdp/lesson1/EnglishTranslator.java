
package com.epam.cdp.lesson1;


/**
 * Created by mikalai on 2/24/14.
 */
public class EnglishTranslator implements LanguageTranslator {

    @Override
    public String translate(String text) {
        return text + " EN";
    }
}

