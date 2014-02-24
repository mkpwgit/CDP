package com.epam.cdp.lesson1;

/**
* Created by mikalai on 2/24/14.
*/
public class RussianTranslator_old implements LanguageTranslator {

    @Override
    public String translate(String text) {
        return text+ " RU";
    }
}
