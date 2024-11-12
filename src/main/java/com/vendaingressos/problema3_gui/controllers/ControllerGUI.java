package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.Pagina;
import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Stack;

public class ControllerGUI {

    public static Usuario usuarioLogado;
    public static Stack<Pagina> pageStack = new Stack<>();


    public static void mudarPagina(Pagina proxPagina, Stage stage) throws IOException {
        pageStack.push(proxPagina);
        FXMLLoader loader = new FXMLLoader(ControllerGUI.class.getResource(proxPagina.path));
        Scene cenaAtual = new Scene(loader.load());
        stage.setScene(cenaAtual);
        stage.show();
    }

}
