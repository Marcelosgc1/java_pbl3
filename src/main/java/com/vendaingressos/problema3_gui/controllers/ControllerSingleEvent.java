package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.models.Evento;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ControllerSingleEvent {

    private final Evento evento;

    @FXML
    private Label nome;

    @FXML
    private Label descricao;

    public ControllerSingleEvent(Evento evento) {
        this.evento = evento;
    }

    @FXML
    private void initialize() {
        nome.setText(evento.getNome());
        descricao.setText(evento.getDescricao());
    }
}