package com.vendaingressos.problema3_gui;

import com.vendaingressos.problema3_gui.Enum.Pagina;
import com.vendaingressos.problema3_gui.controllers.Controller;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static Controller controller = new Controller();
    @Override
    public void start(Stage primaryStage) throws Exception {
        ControllerGUI.pageStack.add(Pagina.LOGIN);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("loginScreen.fxml"));
        VBox root = loader.load();

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Sistema de Venda de Ingressos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
