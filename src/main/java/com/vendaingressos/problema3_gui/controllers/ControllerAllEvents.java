package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.models.Evento;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.vendaingressos.problema3_gui.Main.controller;

public class ControllerAllEvents {


    @FXML
    private ListView<Evento> todosEventos;

    @FXML
    private void initialize() {
        try{
            List<Evento> eventos = controller.listarEventosDisponiveis(new Date());

            ObservableList<Evento> a = FXCollections.observableArrayList(eventos);

            todosEventos.setItems(a);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }
}
