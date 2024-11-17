package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.models.Compra;
import com.vendaingressos.problema3_gui.models.Evento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.Calendar;
import java.util.List;

import static com.vendaingressos.problema3_gui.Main.controller;
import static com.vendaingressos.problema3_gui.controllers.ControllerGUI.usuarioLogado;

public class ControllerAllPurchases {

    @FXML
    private ListView<Compra> todasCompras;

    @FXML
    private void initialize() {
        todasCompras.setCellFactory(tc -> new ListCell<>(){
            @Override
            public void updateItem(Compra compra, boolean empty) {
                super.updateItem(compra, empty);
                if (empty) {
                    setText(null);
                }
                else {
                    setText(compra.gerarRecibo());

                }
            }
        });
        try{
            List<Compra> compras = controller.listarCompras(usuarioLogado);
            ObservableList<Compra> a = FXCollections.observableArrayList(compras);
            todasCompras.setItems(a);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.showAndWait();
        }
    }
}
