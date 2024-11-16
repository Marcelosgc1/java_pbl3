package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.models.Evento;
import com.vendaingressos.problema3_gui.models.Pagina;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.vendaingressos.problema3_gui.Main.controller;

public class ControllerAllEvents {


    @FXML
    private ListView<Evento> todosEventos;

    @FXML
    private void initialize() {
        todosEventos.setCellFactory(te -> new ListCell<Evento>(){
            @Override
            public void updateItem(Evento evento, boolean empty) {
                super.updateItem(evento, empty);
                if (empty) {
                    setText(null);
                }
                else {
                    setText(formatarTexto(evento));

                }
            }
        });
        try{
            Calendar calendar = Calendar.getInstance();
            List<Evento> eventos = controller.listarEventosDisponiveis(calendar);

            ObservableList<Evento> a = FXCollections.observableArrayList(eventos);

            todosEventos.setItems(a);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }

    private String formatarTexto(Evento evento) {
        return evento.getNome() + " - " + evento.getData();
    }

    @FXML
    private void aaa(){
        todosEventos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    ControllerGUI.mudarPagina(Page.EVENTO_UNICO, (Stage) todosEventos.getScene().getWindow(), todosEventos.getSelectionModel().getSelectedItem());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
