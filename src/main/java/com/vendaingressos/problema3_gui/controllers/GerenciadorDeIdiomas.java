package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.Idiomas;

import java.util.Locale;
import java.util.ResourceBundle;

public class GerenciadorDeIdiomas {
    private static ResourceBundle bundle;
    private static Locale currentLocale;
    public static Idiomas idioma = Idiomas.PTBR;

    static {
        setLanguage(idioma);
    }

    public static void setLanguage(Idiomas novoIdioma) {
        idioma = novoIdioma;
        currentLocale = Locale.forLanguageTag(idioma.localizacao);
        bundle = ResourceBundle.getBundle("com.vendaingressos.problema3_gui.i18n.messages", currentLocale);
    }

    public static String get(String key) {
        return bundle.getString(key);
    }
}

