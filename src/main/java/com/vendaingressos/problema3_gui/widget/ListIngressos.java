package com.vendaingressos.problema3_gui.widget;


import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.models.Evento;
import com.vendaingressos.problema3_gui.models.Ingresso;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;

import static com.vendaingressos.problema3_gui.Main.controller;
import static com.vendaingressos.problema3_gui.controllers.ControllerGUI.usuarioLogado;

public class ListIngressos extends ListCell<Ingresso> {
    HBox hbox = new HBox();
    Label label = new Label("(empty)");
    Pane pane = new Pane();
    Button detalhes = new Button(ControllerGUI.get("Button.detail"));
    Button cancelar = new Button(ControllerGUI.get("Button.cancel"));
    Evento evento;
    Ingresso ingresso;

    public ListIngressos() {
        super();
        hbox.getChildren().addAll(label, pane, detalhes, cancelar);
        HBox.setHgrow(pane, Priority.ALWAYS);
        cancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    controller.cancelarCompra(usuarioLogado,ingresso,Calendar.getInstance());
                    ControllerGUI.pageStack.peek().getController().initialize();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        detalhes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Page EVENTO_UNICO = evento.isAtivo(Calendar.getInstance()) ? Page.EVENTO_UNICO_ATIVO : Page.EVENTO_UNICO_DESATIVADO;
                try {
                    ControllerGUI.mudarPagina(EVENTO_UNICO, (Stage) detalhes.getScene().getWindow(), evento);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    protected void updateItem(Ingresso item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (empty) {
            evento = null;
            setGraphic(null);
            return;
        }
        Evento evento = controller.carregarEvento(item.getEvento());
        this.evento = evento;
        this.ingresso = item;
        label.setText(evento.getNome());
        setGraphic(hbox);
    }
}
