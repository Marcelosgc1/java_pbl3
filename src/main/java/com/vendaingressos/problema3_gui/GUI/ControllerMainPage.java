package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.controllers.GerenciadorDeIdiomas;
import com.vendaingressos.problema3_gui.interfaces.Traduzivel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import static com.vendaingressos.problema3_gui.controllers.ControllerGUI.usuarioLogado;

public class ControllerMainPage implements Traduzivel {
    @FXML
    private Label username;
    @FXML
    private Button verEventos;
    @FXML
    private Button verCompras;
//    private String t;
//    public ControllerMainPage(String t) {
//        this.t = t;
//    }

    @FXML
    public void initialize() {
        setLanguage();
    }

    public void verTodosEventos() throws Exception {
        ControllerGUI.mudarPagina(
            Page.TODOS_EVENTOS,
            (Stage) verEventos.getScene().getWindow()
        );
    }
    public void verTodasAsCompras() throws Exception {
        ControllerGUI.mudarPagina(
                Page.TODAS_COMPRAS,
                (Stage) verCompras.getScene().getWindow()
        );
    }

    @Override
    public void setLanguage(){
        username.setText(GerenciadorDeIdiomas.get("label.username") + usuarioLogado.getNome());
        verEventos.setText(GerenciadorDeIdiomas.get("button.allEvents"));
        verCompras.setText(GerenciadorDeIdiomas.get("button.allPurchases"));
    }


}
