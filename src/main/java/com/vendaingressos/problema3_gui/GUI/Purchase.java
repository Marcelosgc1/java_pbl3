package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.FormaDePagamento;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.interfaces.GUI;
import com.vendaingressos.problema3_gui.models.Evento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;

import static com.vendaingressos.problema3_gui.Main.controller;
import static com.vendaingressos.problema3_gui.controllers.ControllerGUI.usuarioLogado;

public class Purchase implements GUI {
    @FXML
    private ComboBox<FormaDePagamento> pagamentos;
    @FXML
    private Button realizarCompra;
    @FXML
    private Button sair;

    private Evento evento;
    private Integer quantidade;


    public Purchase(Evento evento, Integer quantidade) {
        this.evento = evento;
        this.quantidade = quantidade;
    }

    @Override
    public void setLanguage() {
        realizarCompra.setText(ControllerGUI.get("Button.confirmarCompra"));
        sair.setText(ControllerGUI.get("Button.cancel"));
    }

    @FXML
    public void initialize() {
        setLanguage();
        ObservableList<FormaDePagamento> a = FXCollections.observableArrayList(FormaDePagamento.values());
        pagamentos.setItems(a);
    }

    public void comprar() throws IOException {
        FormaDePagamento pagamento = pagamentos.getSelectionModel().getSelectedItem();
        if (pagamento == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR, ControllerGUI.get("Error.formaDePagamento"));
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(ControllerGUI.get("Title.confirmacao"));
        alert.setHeaderText(ControllerGUI.get("Button.confirmarCompra"));
        alert.setContentText(ControllerGUI.get("Text.compra1")+quantidade+" "+ControllerGUI.get("Text.compra2")+ evento.getPreco()*quantidade+" reais?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            controller.realizarCompra(usuarioLogado, evento, quantidade, Calendar.getInstance(), pagamento);
            alert.close();
            alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(ControllerGUI.get("Title.confirmacao"));
            alert.setHeaderText(ControllerGUI.get("Header.confirmada"));
            alert.showAndWait();
        }
        cancelar();
    }

    public void cancelar() {
        ((Stage) sair.getScene().getWindow()).close();
    }


}
