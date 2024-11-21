package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.controllers.GerenciadorDeIdiomas;
import com.vendaingressos.problema3_gui.interfaces.GUI;
import com.vendaingressos.problema3_gui.models.Evento;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerSingleEvent implements GUI {

    private final Evento evento;

    @FXML
    private Label nome;
    @FXML
    private Label descricao;
    @FXML
    private Button comprar;
    @FXML
    private Spinner<Integer> qnt;

    public ControllerSingleEvent(Evento evento) {
        this.evento = evento;
    }

    @Override
    public void setLanguage() {
        nome.setText(evento.getNome());
        descricao.setText(evento.getDescricao());
        comprar.setText(GerenciadorDeIdiomas.get("Button.comprar"));

    }

    @FXML
    public void initialize() {
        setLanguage();
        qnt.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, evento.getAssentosDisponiveis())
        );
    }

    public void comprar() throws Exception {
        Integer ingressosQnt = qnt.getValue();
        Stage novaJanela = new Stage();
        novaJanela.initModality(Modality.WINDOW_MODAL);
        novaJanela.initOwner(nome.getScene().getWindow());
        novaJanela.setTitle("comprar ingresso");
        ControllerGUI.mudarPagina(Page.COMPRAR, novaJanela, evento, ingressosQnt);
        qnt.decrement(evento.getAssentosDisponiveis());

    }


}