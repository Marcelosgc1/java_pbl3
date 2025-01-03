package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.interfaces.GUI;
import com.vendaingressos.problema3_gui.models.Compra;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.vendaingressos.problema3_gui.Main.controller;
import static com.vendaingressos.problema3_gui.controllers.ControllerGUI.usuarioLogado;

public class AllPurchases implements GUI {

    @FXML
    private ListView<Compra> todasCompras;
    /**
     * Coloca a mensagem de texto na linguagem atual do sistema
     */
    @Override
    public void setLanguage() {

    }

    /**
     * Inicializa os widgets da página
     */
    @FXML
    public void initialize() {
        setLanguage();
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
        List<Compra> compras = List.of();
        try{
            compras = controller.listarCompras(usuarioLogado);
            if (compras.isEmpty()) {
                throw new Exception();
            }
        }catch (Exception e){
            compras = new ArrayList<>();
        }finally {
            ObservableList<Compra> a = FXCollections.observableArrayList(compras);
            todasCompras.setItems(a);
        }
    }
}
