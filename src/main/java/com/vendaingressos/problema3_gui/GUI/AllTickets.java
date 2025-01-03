package com.vendaingressos.problema3_gui.GUI;


import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.interfaces.GUI;
import com.vendaingressos.problema3_gui.models.Ingresso;
import com.vendaingressos.problema3_gui.widget.ListIngressos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;
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
    /**
     * Coloca a mensagem de texto na linguagem atual do sistema
     */
    @Override
    public void setLanguage() {
        atual.setText(ControllerGUI.get("Toggle.atual"));
        passado.setText(ControllerGUI.get("Toggle.passado"));
    }
    /**
     * Inicializa os widgets da página
     */
    @FXML
    public void initialize() {
        setLanguage();
        try {
            updateList();
            todosIngressosList.setItems(ticketsFiltrado.stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
            setToggleButtonIngressos(todosIngressosList);
            todosIngressosList.setCellFactory(new Callback<ListView<Ingresso>, ListCell<Ingresso>>() {
                @Override
                public ListCell<Ingresso> call(ListView<Ingresso> param) {
                    return new ListIngressos();
                }
            });

        }
        catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Atualiza a lista de ingressos
     */
    private void updateList() throws IOException {
        ticketsFiltrado = controller.listarIngressosAtualizado(usuarioLogado, ControllerGUI.calendar);
        if (ticketsFiltrado == null) {
            ticketsFiltrado = new ArrayList<>();
        }
    }

    /**
     * Põe a propriedade de ToggleButton para filtrar os ingressos entre ativos e inativos
     * @param todosIngressos ListView dos ingressos
     */
    private void setToggleButtonIngressos(ListView<Ingresso> todosIngressos) throws IOException {
        ToggleGroup toggleGroup = new ToggleGroup();
        atual.setToggleGroup(toggleGroup);
        passado.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {

            ToggleButton selectedButton = (ToggleButton) newToggle;
            try {
                updateList();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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

    /**
     * Dá detalhes do ingresso no caso de clique duplo, isso mostra seu ID
     */
    @FXML
    private void detalhesIngresso() {
        todosIngressosList.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                Ingresso selectedIngresso = todosIngressosList.getSelectionModel().getSelectedItem();
                if (selectedIngresso != null) {
                    alertaIngresso(selectedIngresso);
                }
            }
        });
    }

    /**
     * Alerta com detelhes do ingresso
     * @param ingresso Ingresso que terá seus detalhes apresentados
     */
    public static void alertaIngresso(Ingresso ingresso){
        String nomeDoEvento = controller.carregarEvento(ingresso.getEvento()).getNome();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(ControllerGUI.get("Title.ticket"));
        alert.setHeaderText(ControllerGUI.get("Header.Ticket") + ": " + nomeDoEvento);
        alert.setContentText(ControllerGUI.get("Content.Ticket") + "\n"+ingresso.getId());
        alert.showAndWait();
    }


}
