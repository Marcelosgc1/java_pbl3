package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.abstracts.SingleEvent;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.interfaces.GUI;
import com.vendaingressos.problema3_gui.models.Evento;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SingleEventActive extends SingleEvent implements GUI {

    @FXML
    private Button comprar;
    @FXML
    private Spinner<Integer> qnt;

    public SingleEventActive(Evento evento) {
        super(evento);
    }

    @Override
    public void setLanguage() {
        super.setLanguage();
        descricao.setText(evento.getDescricao()+"\n"+ControllerGUI.get("ToggleButton.preco")+": "+evento.getPreco()+"R$");
        comprar.setText(ControllerGUI.get("Button.comprar"));

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
        qnt.decrement(evento.getAssentosDisponiveis());

        Stage novaJanela = new Stage();
        novaJanela.initModality(Modality.WINDOW_MODAL);
        novaJanela.initOwner(comprar.getScene().getWindow());
        novaJanela.setTitle("comprar ingresso");
        ControllerGUI.mudarPagina(Page.COMPRAR, novaJanela, evento, ingressosQnt);

    }


}