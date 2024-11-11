package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerMainPage {
    @FXML
    private Label username;

    public void initialize(Usuario usuarioLogado) {
        username.setText(usuarioLogado.getNome());
    }
}
