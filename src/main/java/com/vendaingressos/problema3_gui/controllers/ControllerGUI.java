package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.interfaces.ComId;
import com.vendaingressos.problema3_gui.models.Pagina;
import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Stack;

public class ControllerGUI {

    public static Usuario usuarioLogado;
    public static Stack<Pagina<?>> pageStack = new Stack<>();


//    public static void mudarPagina(Pagina proxPagina, Stage stage) throws IOException {
//        pageStack.push(proxPagina);
//        FXMLLoader loader = new FXMLLoader(ControllerGUI.class.getResource(proxPagina.path));
//        loader.getRoot();
//        Scene cenaAtual = new Scene(loader.load());
//        stage.setScene(cenaAtual);
//        stage.show();
//    }

//    public static void mudarPagina(Pagina proxPagina, Scene scene) throws IOException {
//        pageStack.push(proxPagina);
//        FXMLLoader loader = new FXMLLoader(ControllerGUI.class.getResource(proxPagina.path));
//        scene.setRoot(loader.load());
//    }

    public static void mudarPagina(Pagina<?> pagina, Stage stage) throws Exception {
        if (!pagina.getPagina().equals(Page.COMPRA)){
            pageStack.push(pagina);
        }
        pagina.mudarDePagina(stage);
    }

    public static void mudarPagina(Page page, Stage stage) throws Exception {
       mudarPagina(new Pagina<>(page), stage);
    }

    public static void mudarPagina(Page page, Stage stage, Object... obj) throws Exception {
        mudarPagina(new Pagina<>(page, obj), stage);
    }

}
