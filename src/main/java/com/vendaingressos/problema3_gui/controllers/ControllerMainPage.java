package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerMainPage {
    @FXML
    private Label username;
    Usuario usuarioLogado;

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setUsuarioLogado(Usuario usuarioLogado) {
        this.usuarioLogado = usuarioLogado;
        if (username != null) {
            username.setText(usuarioLogado.getNome());
        }
    }



}
