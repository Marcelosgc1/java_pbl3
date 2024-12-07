package com.vendaingressos.problema3_gui.abstracts;

import com.vendaingressos.problema3_gui.Enum.Page;
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

public abstract class SingleEvent implements GUI {

    public final Evento evento;

    @FXML
    public Label nome;
    @FXML
    public Label descricao;


    public SingleEvent(Evento evento) {
        this.evento = evento;
    }

    /**
     * Coloca a mensagem de texto na linguagem atual do sistema
     */
    @Override
    public void setLanguage() {
        nome.setText(evento.getNome());
        descricao.setText(evento.getDescricao());

    }

    /**
     * inicializa a página
     */
    @FXML
    public void initialize() {
        setLanguage();
    }


}