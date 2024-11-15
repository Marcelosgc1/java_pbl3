package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.Pagina;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

import static com.vendaingressos.problema3_gui.controllers.ControllerGUI.pageStack;

public class ControllerTopBar {
    @FXML
    private MenuButton perfil;
    @FXML
    private MenuItem perfilMenu;
    @FXML
    private MenuItem logout;
    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        perfil.setText(ControllerGUI.usuarioLogado.getLogin());
        perfilMenu.setText("Ver Perfil");
        logout.setText("Logout");
        backButton.setText("Voltar");
    }

    public void voltarParaLogin() throws IOException {
        pageStack.clear();
        ControllerGUI.mudarPagina(
                Pagina.LOGIN,
                (Stage) perfil.getScene().getWindow()
        );
    }

    public void irParaPerfil() throws IOException {
        if (pageStack.peek().equals(pageStack.get(1))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Você já está na página de perfil!");
            alert.showAndWait();
            return;
        }
        ControllerGUI.mudarPagina(
                Pagina.PERFIL,
                (Stage) perfil.getScene().getWindow()
        );
    }

    public void voltar() throws IOException {
        pageStack.pop();
        if (!pageStack.isEmpty()) {
            ControllerGUI.mudarPagina(pageStack.pop(), (Stage) perfil.getScene().getWindow());
            return;
        }
        ControllerGUI.mudarPagina(Pagina.LOGIN, (Stage) perfil.getScene().getWindow());

    }





}


