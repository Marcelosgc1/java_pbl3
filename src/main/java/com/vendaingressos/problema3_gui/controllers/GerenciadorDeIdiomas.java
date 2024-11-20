package com.vendaingressos.problema3_gui.controllers;

import java.util.Locale;
import java.util.ResourceBundle;

public class GerenciadorDeIdiomas {
    private static ResourceBundle bundle;
    private static Locale currentLocale;

    static {
        setLanguage("pt");
    }

    public static void setLanguage(String languageCode) {
        currentLocale = Locale.forLanguageTag(languageCode);
        bundle = ResourceBundle.getBundle("com.vendaingressos.problema3_gui.i18n.messages", currentLocale);
    }

    public static String get(String key) {
        return bundle.getString(key);
    }
}

