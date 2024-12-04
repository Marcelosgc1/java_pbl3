package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.Idiomas;
import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.interfaces.ComId;
import com.vendaingressos.problema3_gui.models.Ingresso;
import com.vendaingressos.problema3_gui.models.Notificacao;
import com.vendaingressos.problema3_gui.models.Pagina;
import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static com.vendaingressos.problema3_gui.Main.controller;

public class ControllerGUI {

    public static Usuario usuarioLogado;
    public static Stack<Pagina> pageStack = new Stack<>();
    private static ResourceBundle bundle;
    public static Idiomas idioma = Idiomas.PTBR;
    public static List<Notificacao> notificacoes = new ArrayList<>();
    public static Calendar calendar = Calendar.getInstance();

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

    public static void adicionarNotificacao(ComId model, Page page) {
        Notificacao n = new Notificacao(model, page);
        notificacoes.add(n);
    }

    public static Calendar DatePickerToCalendar(DatePicker dia){
        LocalDate data1 = dia.getValue();
        if (data1 == null) {
            return null;
        }

        ZoneId zone = ZoneId.systemDefault();

        Calendar dia1 = (Calendar) Calendar.getInstance().clone();
        dia1.setTime(Date.from(data1.atStartOfDay(zone).toInstant()));
        return dia1;

    }




}
