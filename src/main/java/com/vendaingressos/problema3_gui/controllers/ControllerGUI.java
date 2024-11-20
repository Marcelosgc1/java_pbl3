package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.models.Pagina;
import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.stage.Stage;

import java.util.Stack;

public class ControllerGUI {

    public static Usuario usuarioLogado;
    public static Stack<Pagina> pageStack = new Stack<>();

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
