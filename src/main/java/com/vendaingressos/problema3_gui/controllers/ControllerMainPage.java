package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.Pagina;
import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

import static com.vendaingressos.problema3_gui.controllers.ControllerGUI.usuarioLogado;

public class ControllerMainPage {
    @FXML
    private Label username;
    @FXML
    private Button verEventos;

    @FXML
    public void initialize() {
        username.setText(usuarioLogado.getNome());
    }

    public void verTodosEventos() throws IOException {
        ControllerGUI.mudarPagina(
            Pagina.TODOS_EVENTOS,
            (Stage) verEventos.getScene().getWindow()
        );
    }
}
