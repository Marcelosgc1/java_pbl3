package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.interfaces.GUI;
import com.vendaingressos.problema3_gui.models.Notificacao;
import com.vendaingressos.problema3_gui.widget.ListNotifications;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.vendaingressos.problema3_gui.Main.controller;
import static com.vendaingressos.problema3_gui.controllers.ControllerGUI.usuarioLogado;

public class MainPage implements GUI {
    @FXML
    private Label username;
    @FXML
    private Label change;
    @FXML
    private Button verEventos;
    @FXML
    private Button verCompras;
    @FXML
    private Button verIngressos;
    @FXML
    private TextField newName;
    @FXML
    private TextField newEmail;
    @FXML
    private TextField newCPF;
    @FXML
    private TextField newPass;
    @FXML
    private Button confirmChange;
    @FXML
    private Label data;
    @FXML
    private ListView<Notificacao> notifications;




    /**
     * Inicializa os widgets da página
     */
    @FXML
    public void initialize() {

        setLanguage();
        try{
            controller.listarIngressosAtualizado(usuarioLogado, ControllerGUI.calendar);
        }catch (Exception e){
            e.printStackTrace();
        }
        notifications.setItems(ControllerGUI.notificacoes.stream().collect(Collectors.toCollection(FXCollections::observableArrayList)));
        notifications.setCellFactory(new Callback<ListView<Notificacao>, ListCell<Notificacao>>() {
            @Override
            public ListCell<Notificacao> call(ListView<Notificacao> param) {
                return new ListNotifications();
            }
        });
    }

    /**
     * Muda a página para a de todos os eventos
     */
    public void verTodosEventos() throws Exception {
        ControllerGUI.mudarPagina(
            Page.TODOS_EVENTOS,
            (Stage) verEventos.getScene().getWindow()
        );
    }

    /**
     * Muda a página para a de todas as compras realizadas pelo usuário
     */
    public void verTodasAsCompras() throws Exception {
        ControllerGUI.mudarPagina(
                Page.TODAS_COMPRAS,
                (Stage) verCompras.getScene().getWindow()
        );
    }

    /**
     * Muda a página para de todos os ingressos que o usuário tem
     */
    public void verTodosIngressos() throws Exception {
        ControllerGUI.mudarPagina(
                Page.TODOS_INGRESSOS,
                (Stage) verIngressos.getScene().getWindow()
        );
    }


    /**
     * Altera os pessoais do usuário.
     */
    public void confirmarMudancas() throws Exception {
        if(!newName.getText().isEmpty()) {
            String novoNome = newName.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ControllerGUI.get("Error.setNewData")+ControllerGUI.get("textField.newName")+" "+ControllerGUI.get("Error.setNewData2")+" "+novoNome, ButtonType.OK, ButtonType.CANCEL);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                controller.alterarNome(usuarioLogado, novoNome);
                newName.clear();
            }
        }
        if(!newEmail.getText().isEmpty()) {
            String novoEmail = newEmail.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ControllerGUI.get("Error.setNewData")+ControllerGUI.get("textField.newEmail")+" "+ControllerGUI.get("Error.setNewData2")+" "+novoEmail, ButtonType.OK, ButtonType.CANCEL);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                controller.alterarEmail(usuarioLogado, novoEmail);
                newEmail.clear();
            }
        }
        if(!newCPF.getText().isEmpty()) {
            String novoCPF = newCPF.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ControllerGUI.get("Error.setNewData")+ControllerGUI.get("textField.newCPF")+" "+ControllerGUI.get("Error.setNewData2")+" "+novoCPF, ButtonType.OK, ButtonType.CANCEL);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                controller.alterarCpf(usuarioLogado, novoCPF);
                newCPF.clear();
            }
        }
        if(!newPass.getText().isEmpty()) {
            String novaSenha = newPass.getText();

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ControllerGUI.get("Error.setNewData")+ControllerGUI.get("textField.newName")+" "+ControllerGUI.get("Error.setNewData2")+" "+novaSenha, ButtonType.OK, ButtonType.CANCEL);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                controller.alterarSenha(usuarioLogado, novaSenha);
                newPass.clear();
            }
        }
        initialize();
    }
    /**
     * Coloca a mensagem de texto na linguagem atual do sistema
     */
    @Override
    public void setLanguage(){
        username.setText(ControllerGUI.get("label.username") + usuarioLogado.getNome());
        verEventos.setText(ControllerGUI.get("button.allEvents"));
        verCompras.setText(ControllerGUI.get("button.allPurchases"));
        verIngressos.setText(ControllerGUI.get("button.allTickets"));
        change.setText(ControllerGUI.get("Label.change"));
        newName.setPromptText(ControllerGUI.get("textField.newName"));
        newEmail.setPromptText(ControllerGUI.get("textField.newEmail"));
        newCPF.setPromptText(ControllerGUI.get("textField.newCPF"));
        newPass.setPromptText(ControllerGUI.get("textField.newPass"));
        confirmChange.setText(ControllerGUI.get("Button.confirmChange"));
        data.setText(
                ControllerGUI.get("textField.login") + ": " + usuarioLogado.getLogin() + "\n" +
                ControllerGUI.get("textField.email") + ": " + usuarioLogado.getEmail() + "\n" +
                ControllerGUI.get("textField.cpf") + ": " + usuarioLogado.getCpf()
        );
    }


}
