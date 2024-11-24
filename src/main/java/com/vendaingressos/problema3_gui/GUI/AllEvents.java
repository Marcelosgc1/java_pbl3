package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.interfaces.GUI;
import com.vendaingressos.problema3_gui.models.Evento;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.vendaingressos.problema3_gui.Main.controller;

public class AllEvents implements GUI {


    @FXML
    private ListView<Evento> todosEventos;
    @FXML
    private TextField pesquisa;
    @FXML
    private DatePicker menor;
    @FXML
    private DatePicker maior;
    @FXML
    private ToggleButton data;
    @FXML
    private ToggleButton preco;

    private boolean invertido = false;
    private ObservableList<Evento> eventosFiltrados;
    private final List<Evento> eventos;

    public AllEvents() throws IOException {
//        eventos = controller.listarEventosDisponiveis(Calendar.getInstance());
        eventos = controller.listarEventos();
        eventosFiltrados = FXCollections.observableArrayList(eventos);
    }


    @FXML
    public void initialize() {
        setLanguage();

        ToggleGroup toggleGroup = new ToggleGroup();
        data.setToggleGroup(toggleGroup);
        preco.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {

            ToggleButton selectedButton = (ToggleButton) newToggle;
            ToggleButton unselectedButton = (ToggleButton) oldToggle;

            if (selectedButton == data) {
                todosEventos.getItems().sort(Comparator.comparing(Evento::getData));
                invertido = false;
                return;
            }
            if (!invertido && selectedButton == preco) {
                todosEventos.getItems().sort(Comparator.comparing(Evento::getPreco));
                invertido = true;
                return;
            }
            if (invertido && unselectedButton == preco) {
                todosEventos.getItems().sort(Comparator.comparing(evento -> evento.getPreco() * (-1)));
                toggleGroup.selectToggle(unselectedButton);
                invertido = false;
                return;
            }
            if (!(preco.isSelected() || data.isSelected())) {
                todosEventos.setItems(eventosFiltrados);
            }
        });


        todosEventos.setCellFactory(te -> new ListCell<Evento>(){
            @Override
            public void updateItem(Evento evento, boolean empty) {
                super.updateItem(evento, empty);
                if (empty) {
                    setText(null);
                }
                else {
                    setText(formatarTexto(evento));
                }
            }
        });
        ObservableList<Evento> a = FXCollections.observableArrayList(eventos);
        todosEventos.setItems(a);

        setPropriedadeCalendar(menor);
        setPropriedadeCalendar(maior);

        pesquisa.textProperty().addListener((_, _, novoTexto) -> {
            filtro(novoTexto, pegarDia(menor), pegarDia(maior));
        });

    }

    private void setPropriedadeCalendar(DatePicker dia){
        dia.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observable,
                                java.time.LocalDate oldValue,
                                java.time.LocalDate newValue) {
                filtro(pesquisa.getText(), pegarDia(menor), pegarDia(maior));
            }
        });
    }

    private String formatarTexto(Evento evento) {
        return evento.getNome() + " - " +
                evento.getData().get(Calendar.DAY_OF_MONTH) + "/" +
                (evento.getData().get(Calendar.MONTH) + 1) + "/" +
                evento.getData().get(Calendar.YEAR);
    }

    @FXML
    private void detalhesDoEvento(){
        todosEventos.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                try {
                    Evento eventoSelecionado = todosEventos.getSelectionModel().getSelectedItem();
                    Page EVENTO_UNICO = eventoSelecionado.isAtivo(Calendar.getInstance()) ? Page.EVENTO_UNICO_ATIVO : Page.EVENTO_UNICO_DESATIVADO;
                    ControllerGUI.mudarPagina(EVENTO_UNICO, (Stage) todosEventos.getScene().getWindow(), eventoSelecionado);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    private void filtro(String texto, Calendar dataInicial, Calendar dataFinal) {
        eventosFiltrados = eventos.stream()
                .filter(evento -> (texto == null || evento.getNome().toLowerCase().contains(texto.toLowerCase())))
                .filter(evento -> (dataInicial == null || evento.getData().after(dataInicial)))
                .filter(evento -> (dataFinal == null || evento.getData().before(dataFinal)))
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        todosEventos.setItems(eventosFiltrados);
    }

    private Calendar pegarDia(DatePicker dia){
        LocalDate data1 = dia.getValue();
        if (data1 == null) {
            return null;
        }

        ZoneId zone = ZoneId.systemDefault();

        Calendar dia1 = Calendar.getInstance();
        dia1.setTime(Date.from(data1.atStartOfDay(zone).toInstant()));
        return dia1;

    }

    @Override
    public void setLanguage() {
        pesquisa.setPromptText(ControllerGUI.get("textField.pesquisa"));
        maior.setPromptText(ControllerGUI.get("DatePicker.maior"));
        menor.setPromptText(ControllerGUI.get("DatePicker.menor"));
        data.setText(ControllerGUI.get("ToggleButton.data"));
        preco.setText(ControllerGUI.get("ToggleButton.preco"));
    }
}
