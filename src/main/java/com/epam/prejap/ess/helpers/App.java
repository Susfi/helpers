package com.epam.prejap.ess.helpers;

import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.epam.prejap.ess.helpers.App.MsgKeys.HELLO_WORLD;

/**
 * Hello world! In more than one language!
 */
public class App {
    enum MsgKeys {
        HELLO_WORLD, BYE_WORLD
    }
    public static void main(String[] args) {
        final Locale pl_PL = new Locale("pl", "PL");
        final Locale russianLocale = new Locale("ru", "RU");

        ResourceBundle msgs = ResourceBundle.getBundle("messages", russianLocale);

        System.out.println(msgs.getString(HELLO_WORLD.name()));
        Arrays.asList(1, 2, 3).forEach(System.out::println);
        System.out.println(msgs.getString(MsgKeys.BYE_WORLD.toString()));
    }
}

