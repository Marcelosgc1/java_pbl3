package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.Pagina;
import com.vendaingressos.problema3_gui.models.Evento;
import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class ControllerGUI {

    public static Usuario usuarioLogado;

    public static final Stack<Scene> pageStack = new Stack<>();

    public static void mudarPagina(Scene scene, Stage stage) throws IOException {
        pageStack.push(scene);
        stage.setScene(scene);
        stage.show();
    }

    public static void mudarPagina(Pagina proxPagina, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(ControllerGUI.class.getResource(proxPagina.path));
        Scene scene = new Scene(loader.load());
        mudarPagina(scene, stage);
    }

    public static void mudarPagina(Stage stage, Evento evento) throws IOException {
        FXMLLoader loader = new FXMLLoader(ControllerGUI.class.getResource(Pagina.EVENTO_UNICO.path));
        loader.setController(new ControllerSingleEvent(evento));
        Scene scene = new Scene(loader.load());
        mudarPagina(scene, stage);
    }



}
