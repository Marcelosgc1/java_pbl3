package com.vendaingressos.problema3_gui.widget;


import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.models.Evento;
import com.vendaingressos.problema3_gui.models.Ingresso;
import com.vendaingressos.problema3_gui.models.Notificacao;
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

public class ListNotifications extends ListCell<Notificacao> {
    HBox hbox = new HBox();
    Label label = new Label("(empty)");
    Pane pane = new Pane();
    Button detalhes = new Button(ControllerGUI.get("Button.detail"));
    Notificacao notificacao;

    public ListNotifications() {
        super();
        hbox.getChildren().addAll(label, pane, detalhes);
        HBox.setHgrow(pane, Priority.ALWAYS);
        detalhes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ControllerGUI.mudarPagina(notificacao.page, (Stage) detalhes.getScene().getWindow());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Override
    protected void updateItem(Notificacao item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        if (empty) {
            notificacao = null;
            setGraphic(null);
            return;
        }
        this.notificacao = item;
        label.setText(notificacao.toString());
        setGraphic(hbox);
    }
}
