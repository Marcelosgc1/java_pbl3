package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.models.Evento;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ControllerSingleEvent {

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

    @FXML
    private void initialize() {
        qnt.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, evento.getAssentosDisponiveis())
        );
        nome.setText(evento.getNome());
        descricao.setText(evento.getDescricao());
    }

    public void comprar() throws Exception {
        Integer ingressosQnt = qnt.getValue();
        Stage novaJanela = new Stage();
        novaJanela.initModality(Modality.WINDOW_MODAL);
        novaJanela.initOwner(nome.getScene().getWindow());
        novaJanela.setTitle("comprar ingresso");
        ControllerGUI.mudarPagina(Page.COMPRAR, novaJanela, evento, ingressosQnt);
        qnt.decrement(evento.getAssentosDisponiveis());
//        controller.realizarCompra(usuarioLogado, evento);


    }


}