package com.vendaingressos.problema3_gui;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.Controller;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.models.Evento;
import com.vendaingressos.problema3_gui.models.Pagina;
import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Calendar;

public class Main extends Application {
    public static Controller controller = new Controller();

    public static void initEventos() throws Exception {
        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        if (admin == null) {
            admin = controller.loginUsuario("admin", "senha123");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);

        Evento evento = controller.cadastrarEvento(admin,"Show de Rock", "Banda XYZ", calendar, 100, 100.0);
        Evento evento1 = controller.cadastrarEvento(admin,"Show de Pop", "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\nAAAAAAAAAAAAAAABandaAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABanda XYZ", calendar, 100, 100.0);
        Evento evento2 = controller.cadastrarEvento(admin,"Show de Musica Classica", "Banda XYZ", calendar, 100, 100.0);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ControllerGUI.pageStack.push(new Pagina<>(Page.LOGIN));
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Page.LOGIN.path));
        VBox root = loader.load();

        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Sistema de Venda de Ingressos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
//        initEventos();
        launch(args);
    }
}
