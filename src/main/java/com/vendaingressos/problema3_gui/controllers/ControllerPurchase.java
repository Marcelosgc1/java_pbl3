package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.FormaDePagamento;
import com.vendaingressos.problema3_gui.models.Evento;
import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import static com.vendaingressos.problema3_gui.Main.controller;
import static com.vendaingressos.problema3_gui.controllers.ControllerGUI.usuarioLogado;

public class ControllerPurchase {
    @FXML
    private ComboBox<FormaDePagamento> pagamentos;
    @FXML
    private Button realizarCompra;
    @FXML
    private Button sair;

    private Evento evento;
    private Integer quantidade;
    public ControllerPurchase(Evento evento, Integer quantidade) {
        this.evento = evento;
        this.quantidade = quantidade;
    }

    @FXML
    public void initialize() {
        ObservableList<FormaDePagamento> a = FXCollections.observableArrayList(FormaDePagamento.values());
        pagamentos.setItems(a);
    }

    public void comprar() throws IOException {
        FormaDePagamento pagamento = pagamentos.getSelectionModel().getSelectedItem();
        if (pagamento == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Selecione uma forma de pagamento!");
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmação");
        alert.setHeaderText("Confirmar compra");
        alert.setContentText("Deseja realizar o compra de "+quantidade+" ingressos pelo valor de "+ evento.getPreco()*quantidade+" reais?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.realizarCompra(usuarioLogado, evento, quantidade, Calendar.getInstance(), pagamento);
            alert.close();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Confirmação");
            alert.setHeaderText("Compra Realizada com sucesso!");
            alert.showAndWait();
        }
        cancelar();
    }

    public void cancelar() {
        ((Stage) sair.getScene().getWindow()).close();
    }


}
