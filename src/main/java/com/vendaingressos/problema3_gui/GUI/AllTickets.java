package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.interfaces.GUI;
import com.vendaingressos.problema3_gui.models.Evento;
import com.vendaingressos.problema3_gui.models.Ingresso;
import com.vendaingressos.problema3_gui.widget.ListIngressos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import static com.vendaingressos.problema3_gui.Main.controller;
import static com.vendaingressos.problema3_gui.controllers.ControllerGUI.usuarioLogado;

public class AllTickets implements GUI {
    @FXML
    private ToggleButton atual;
    @FXML
    private ToggleButton passado;
    @FXML
    private ListView<Ingresso> todosIngressosList;

    private List<Ingresso> ticketsFiltrado;

    @Override
    public void setLanguage() {
        atual.setText(ControllerGUI.get("Toggle.atual"));
        passado.setText(ControllerGUI.get("Toggle.passado"));
    }

    @FXML
    public void initialize() {
        setLanguage();
        try {
            ticketsFiltrado = controller.listarIngressosAtualizado(usuarioLogado, Calendar.getInstance());
            setToggleButtonIngressos(todosIngressosList);
            todosIngressosList.setCellFactory(new Callback<ListView<Ingresso>, ListCell<Ingresso>>() {
                @Override
                public ListCell<Ingresso> call(ListView<Ingresso> param) {
                    return new ListIngressos();
                }
            });
            todosIngressosList.setItems(ticketsFiltrado.stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }



    private void setToggleButtonIngressos(ListView<Ingresso> todosIngressos) throws IOException {
        ToggleGroup toggleGroup = new ToggleGroup();
        atual.setToggleGroup(toggleGroup);
        passado.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {

            ToggleButton selectedButton = (ToggleButton) newToggle;

            if (selectedButton == atual) {
                passado.setSelected(false);
                ObservableList<Ingresso> lista = ticketsFiltrado.stream().filter(Ingresso::isAtivo).collect(Collectors.toCollection(FXCollections::observableArrayList));
                todosIngressos.setItems(lista);
                return;
            }
            if (selectedButton == passado) {
                atual.setSelected(false);
                ObservableList<Ingresso> lista = ticketsFiltrado.stream().filter(Ingresso::isInativo).collect(Collectors.toCollection(FXCollections::observableArrayList));
                todosIngressos.setItems(lista);
                return;
            }
            if (!(passado.isSelected() || atual.isSelected())) {
                todosIngressos.setItems(ticketsFiltrado.stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
            }
        });
    }

    @FXML
    private void detalhesIngresso(){
        todosIngressosList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    Ingresso ingressoSelecionado = todosIngressosList.getSelectionModel().getSelectedItem();
                    Evento eventoSelecionado = controller.carregarEvento(ingressoSelecionado.getEvento());
                    Page EVENTO_UNICO = eventoSelecionado.isAtivo(Calendar.getInstance()) ? Page.EVENTO_UNICO_ATIVO : Page.EVENTO_UNICO_DESATIVADO;
                    ControllerGUI.mudarPagina(EVENTO_UNICO, (Stage) todosIngressosList.getScene().getWindow(), eventoSelecionado);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

}
