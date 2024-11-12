package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

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

    public void aaaaaaaaaaaaaaaaaaaa(){
        ControllerGUI.mudarPagina(
            Pagina.TODOS_EVENTOS.path,
            (Stage) verEventos.getScene().getWindow()
        );
    }
}
