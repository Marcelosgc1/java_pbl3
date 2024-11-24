package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.Idiomas;
import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.models.Pagina;
import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Stack;

public class ControllerGUI {

    public static Usuario usuarioLogado;
    public static Stack<Pagina> pageStack = new Stack<>();
    private static ResourceBundle bundle;
    public static Idiomas idioma = Idiomas.PTBR;

    static {
        setLanguage(idioma);
    }

    public static void setLanguage(Idiomas novoIdioma) {
        idioma = novoIdioma;
        bundle = ResourceBundle.getBundle("com.vendaingressos.problema3_gui.i18n.messages", Locale.forLanguageTag(idioma.localizacao));
    }

    public static String get(String key) {
        return bundle.getString(key);
    }

    public static void mudarPagina(Pagina pagina, Stage stage) throws Exception {
        if (!pagina.getPagina().equals(Page.COMPRAR)){
            pageStack.push(pagina);
        }
        pagina.mudarDePagina(stage);
    }

    public static void mudarPagina(Page page, Stage stage) throws Exception {
       mudarPagina(page, stage,null);
    }

    public static void mudarPagina(Page page, Stage stage, Object... obj) throws Exception {
        Pagina pagina = new Pagina(page);
        if (!pagina.getPagina().equals(Page.COMPRAR)){
            pageStack.push(pagina);
        }
        pagina.mudarDePagina(stage, obj);
    }

}
