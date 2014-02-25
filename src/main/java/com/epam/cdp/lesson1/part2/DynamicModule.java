package com.epam.cdp.lesson1.part2;

/**
 * Created by mikalai on 2/25/14.
 */
public class DynamicModule extends TrueStaticModule {

    public String toString() {
        return "DynamicModule, version 2! "+(counter++);
    }
}
