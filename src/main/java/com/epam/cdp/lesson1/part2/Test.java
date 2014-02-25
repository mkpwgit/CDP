package com.epam.cdp.lesson1.part2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by mikalai on 2/25/14.
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, IOException {
        for (; ; ) {
//            ClassLoader loader = new DynamicClassOverLoader(new String[]{".", "/home/mikalai/gitProjects/CDP/build/classes/main"});
//            Class clazz = Class.forName("com.epam.cdp.lesson1.part2.TestModule", true, loader);
//            Object object = clazz.newInstance();
//            System.out.println(object);
//            new BufferedReader(new InputStreamReader(System.in)).readLine();

            ClassLoader loader= new DynamicClassOverLoader(new String[] {".", "/home/mikalai/gitProjects/CDP/build/classes/main"});
            Class clazz= Class.forName("com.epam.cdp.lesson1.part2.DynamicModule",true,loader);
            TrueStaticModule trueStaticModule=(TrueStaticModule) clazz.newInstance();
            System.out.println(trueStaticModule.getCounter());
            System.out.println(trueStaticModule);
            new BufferedReader(new InputStreamReader (System.in)).readLine();
        }
    }
}
