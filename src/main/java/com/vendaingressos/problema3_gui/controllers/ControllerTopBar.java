package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
    public void initialize() {
        perfil.setText(ControllerLogin.usuarioLogado.getLogin());
        perfilMenu.setText("Ver Perfil");
        logout.setText("Logout");
    }

    public void voltarParaLogin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vendaingressos/problema3_gui/loginScreen.fxml"));
        Scene loginScreen = new Scene(loader.load());

        Stage stage = (Stage) perfil.getScene().getWindow();
        stage.setScene(loginScreen);
        stage.show();
    }

    public void irParaPerfil() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Você já está na página de perfil!");
        alert.showAndWait();
    }

}


