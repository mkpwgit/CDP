package com.epam.cdp.lesson1;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by mikalai on 2/20/14.
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException {
        System.out.println("Hello world!");

        ClassLoader classLoader = ClassLoader.getSystemClassLoader();

        URL url = Class.class.getResource("/com/epam/cdp/CDP-inner-1.0.jar");
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[] {url}, ClassLoader.getSystemClassLoader());

        Class clazz = Class.forName("com.epam.cdp.lesson1.RussianTranslator", true, urlClassLoader);
        LanguageTranslator languageTranslator = (LanguageTranslator) clazz.newInstance();
        System.out.println(languageTranslator.translate("Hello world!"));

    }


}
