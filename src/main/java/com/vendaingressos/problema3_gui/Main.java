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

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Calendar;

public class Main extends Application {
    public static Controller controller = new Controller();

    public static void initEventos() throws Exception {
        if (Files.exists(Paths.get("repository" + File.separator + "Evento"))) return;
        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        if (admin == null) {
            admin = controller.loginUsuario("admin", "senha123");
        }
        Calendar calendar = Calendar.getInstance();

        Evento evento1 = controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", calendar, 100, 100.0);
        calendar.set(2025, Calendar.SEPTEMBER, 12);
        Evento evento2 = controller.cadastrarEvento(admin, "Show de Pop", "Artista ABC", calendar, 150, 120.0);
        calendar.set(2025, Calendar.OCTOBER, 5);
        Evento evento3 = controller.cadastrarEvento(admin, "Concerto de Jazz", "Quarteto Smooth", calendar, 200, 80.0);
        calendar.set(2025, Calendar.NOVEMBER, 20);
        Evento evento4 = controller.cadastrarEvento(admin, "Festival de Música", "Vários Artistas", calendar, 500, 200.0);
        calendar.set(2025, Calendar.DECEMBER, 15);
        Evento evento5 = controller.cadastrarEvento(admin, "Ópera Clássica", "Orquestra Sinfônica", calendar, 300, 150.0);
        calendar.set(2026, Calendar.JANUARY, 10);
        Evento evento6 = controller.cadastrarEvento(admin, "Show de Hip-Hop", "DJ Alpha", calendar, 100, 90.0);
        calendar.set(2026, Calendar.FEBRUARY, 25);
        Evento evento7 = controller.cadastrarEvento(admin, "Show de Sertanejo", "Dupla Modão", calendar, 250, 75.0);
        calendar.set(2026, Calendar.MARCH, 18);
        Evento evento8 = controller.cadastrarEvento(admin, "Teatro Musical", "Companhia XYZ", calendar, 350, 180.0);
        calendar.set(2026, Calendar.APRIL, 8);
        Evento evento9 = controller.cadastrarEvento(admin, "Festival de Blues", "Banda Blue Notes", calendar, 180, 110.0);
        calendar.set(2026, Calendar.MAY, 20);
        Evento evento10 = controller.cadastrarEvento(admin, "Show de Reggae", "Bob's Tribute", calendar, 120, 100.0);
        calendar.set(2026, Calendar.JUNE, 15);
        Evento evento11 = controller.cadastrarEvento(admin, "Stand-up Comedy", "Comediante XYZ", calendar, 200, 50.0);
        calendar.set(2026, Calendar.JULY, 10);
        Evento evento12 = controller.cadastrarEvento(admin, "Peça de Teatro", "Grupo Dramático ABC", calendar, 100, 60.0);
        calendar.set(2026, Calendar.AUGUST, 1);
        Evento evento13 = controller.cadastrarEvento(admin, "Show de Folk", "Cantor ZZZ", calendar, 150, 80.0);
        calendar.set(2026, Calendar.SEPTEMBER, 5);
        Evento evento14 = controller.cadastrarEvento(admin, "Show de Eletrônica", "DJ Beats", calendar, 200, 95.0);
        calendar.set(2026, Calendar.OCTOBER, 12);
        Evento evento15 = controller.cadastrarEvento(admin, "Festival de Dança", "Grupo DanceX", calendar, 300, 120.0);
        calendar.set(2026, Calendar.NOVEMBER, 25);
        Evento evento16 = controller.cadastrarEvento(admin, "Show de MPB", "Artista XYZ", calendar, 150, 100.0);
        calendar.set(2026, Calendar.DECEMBER, 20);
        Evento evento17 = controller.cadastrarEvento(admin, "Concerto de Natal", "Coral Angelus", calendar, 400, 200.0);
        calendar.set(2027, Calendar.JANUARY, 15);
        Evento evento18 = controller.cadastrarEvento(admin, "Show de Indie", "Banda Indie Vibes", calendar, 200, 95.0);
        calendar.set(2027, Calendar.FEBRUARY, 10);
        Evento evento19 = controller.cadastrarEvento(admin, "Show de Heavy Metal", "Banda Metal Roar", calendar, 300, 150.0);
        calendar.set(2027, Calendar.MARCH, 5);
        Evento evento20 = controller.cadastrarEvento(admin, "Feira Cultural", "Vários Expositores", calendar, 500, 30.0);
        calendar.set(2027, Calendar.APRIL, 25);
        Evento evento21 = controller.cadastrarEvento(admin, "Show de Samba", "Grupo Pagode Total", calendar, 200, 70.0);
        calendar.set(2027, Calendar.MAY, 18);
        Evento evento22 = controller.cadastrarEvento(admin, "Show de Música Latina", "Cantor LatinoHeat", calendar, 250, 110.0);
        calendar.set(2027, Calendar.JUNE, 30);
        Evento evento23 = controller.cadastrarEvento(admin, "Festival Gospel", "Vários Artistas", calendar, 400, 90.0);
        calendar.set(2027, Calendar.JULY, 22);
        Evento evento24 = controller.cadastrarEvento(admin, "Concerto de Violão", "Maestro Strings", calendar, 100, 70.0);
        calendar.set(2027, Calendar.AUGUST, 15);
        Evento evento25 = controller.cadastrarEvento(admin, "Show de Rap", "MC Flow", calendar, 200, 80.0);
        calendar.set(2027, Calendar.SEPTEMBER, 10);
        Evento evento26 = controller.cadastrarEvento(admin, "Festival de Jazz e Blues", "Vários Artistas", calendar, 300, 130.0);
        calendar.set(2027, Calendar.OCTOBER, 12);
        Evento evento27 = controller.cadastrarEvento(admin, "Show de Música Regional", "Trio Nordeste", calendar, 150, 60.0);
        calendar.set(2027, Calendar.NOVEMBER, 10);
        Evento evento28 = controller.cadastrarEvento(admin, "Show de Punk Rock", "Banda Rebel Heart", calendar, 200, 95.0);
        calendar.set(2027, Calendar.DECEMBER, 5);
        Evento evento29 = controller.cadastrarEvento(admin, "Show de Música Instrumental", "Grupo Harmonia", calendar, 100, 85.0);
        calendar.set(2028, Calendar.JANUARY, 20);
        Evento evento30 = controller.cadastrarEvento(admin, "Show de Música Experimental", "Coletivo X", calendar, 150, 105.0);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pagina pagina = new Pagina(Page.LOGIN);

        FXMLLoader loader = new FXMLLoader(getClass().getResource(Page.LOGIN.path));

        loader.setController(
                Page.LOGIN.controllerClass
                        .getDeclaredConstructors()[0]
                        .newInstance()
        );

        pagina.setController(loader.getController());
        ControllerGUI.pageStack.push(pagina);
        VBox root = loader.load();
        Scene scene = new Scene(root, 900, 600);
        primaryStage.setTitle("Sistema de Venda de Ingressos");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws Exception {
        initEventos();
        launch(args);
    }
}
