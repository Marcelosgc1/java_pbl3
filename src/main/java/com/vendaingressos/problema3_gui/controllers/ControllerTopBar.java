package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.Pagina;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

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
        ControllerGUI.pageStack.clear();
        ControllerGUI.pageStack.add(Pagina.LOGIN);
        ControllerGUI.mudarPagina(
                Pagina.LOGIN.path,
                (Stage) perfil.getScene().getWindow()
        );
    }

    public void irParaPerfil() throws IOException {
        if (ControllerGUI.pageStack.peek().equals(Pagina.PERFIL)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Você já está na página de perfil!");
            alert.showAndWait();
            return;
        }
        ControllerGUI.mudarPagina(
                Pagina.PERFIL.path,
                (Stage) perfil.getScene().getWindow()
        );
    }

    public void voltar() throws IOException {
        ControllerGUI.pageStack.pop();
        Pagina lastPage = ControllerGUI.pageStack.peek();

        ControllerGUI.mudarPagina(
                lastPage == null ? Pagina.LOGIN.path : lastPage.path,
                (Stage) perfil.getScene().getWindow()
        );
    }



}

