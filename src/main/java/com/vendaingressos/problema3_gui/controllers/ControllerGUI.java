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

    /**
     * Muda o idioma do sistema para o escolhido
     * @param novoIdioma novo idioma que será usado
     */
    public static void setLanguage(Idiomas novoIdioma) {
        idioma = novoIdioma;
        bundle = ResourceBundle.getBundle("com.vendaingressos.problema3_gui.i18n.messages", Locale.forLanguageTag(idioma.localizacao));
    }

    /**
     * Pega a string na linguagem atual do sistema, relacionada a uma chave
     * @param key chave da string
     * @return String com texto no idioma atual do sistema
     */
    public static String get(String key) {
        return bundle.getString(key);
    }

    /**
     * Muda a página do sistema para outra nova
     * Além disso, põe a nova página na Stack de páginas
     * @param pagina Página nova que será aberta
     * @param stage Stage do programa
     */
    public static void mudarPagina(Pagina pagina, Stage stage) throws Exception {
        if (!pagina.getPagina().equals(Page.COMPRAR)){
            pageStack.push(pagina);
        }
        pagina.mudarDePagina(stage);
    }

    /**
     * Muda a página do sistema para outra nova
     * @param page Página nova que será aberta (Enumerador)
     * @param stage Stage do programa
     */
    public static void mudarPagina(Page page, Stage stage) throws Exception {
       mudarPagina(page, stage,null);
    }

    /**
     * Muda a página do sistema para outra nova
     * @param page Página nova que será aberta (Enumerador)
     * @param stage Stage do programa
     * @param obj Array de objetos, que será usado como construtor da página nova
     */
    public static void mudarPagina(Page page, Stage stage, Object... obj) throws Exception {
        Pagina pagina = new Pagina(page);
        if (!pagina.getPagina().equals(Page.COMPRAR)){
            pageStack.push(pagina);
        }
        pagina.mudarDePagina(stage, obj);
    }

    /**
     * Adiciona uma nova notificação à lista de notificações
     * @param model Objeto que gerou a notificação (Ingresso, evento ou compra)
     * @param page Página que será aberta ao clicar na notificação
     */
    public static void adicionarNotificacao(ComId model, Page page) {
        Notificacao n = new Notificacao(model, page);
        notificacoes.add(n);
    }

    /**
     * Recebe um DatePicker (javaFX) e converte seu valor interno para o Calendar
     * @param dia DatePicker que terá o sua data lida
     * @return a Data, mas como objeto do tipo Calendar
     */
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
