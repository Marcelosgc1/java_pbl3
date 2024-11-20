package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.controllers.GerenciadorDeIdiomas;
import com.vendaingressos.problema3_gui.exceptions.AlreadyExistingUserException;
import com.vendaingressos.problema3_gui.exceptions.EmptyFieldException;
import com.vendaingressos.problema3_gui.exceptions.WrongPasswordException;
import com.vendaingressos.problema3_gui.interfaces.GUI;
import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import static com.vendaingressos.problema3_gui.Main.controller;

public class ControllerLogin implements GUI {


    @FXML
    private TextField login;
    @FXML
    private PasswordField senha;
    @FXML
    private PasswordField senhaConfirm;
    @FXML
    private TextField nome;
    @FXML
    private TextField cpf;
    @FXML
    private TextField email;
    @FXML
    private TextField logLogin;
    @FXML
    private TextField logSenha;
    @FXML
    private Button cadastra;
    @FXML
    private Button loga;


    @Override
    public void setLanguage() {
        login.setPromptText(GerenciadorDeIdiomas.get("textField.login"));
        senha.setPromptText(GerenciadorDeIdiomas.get("textField.senha"));
        senhaConfirm.setPromptText(GerenciadorDeIdiomas.get("textField.senhaConfirm"));
        nome.setPromptText(GerenciadorDeIdiomas.get("textField.nome"));
        cpf.setPromptText(GerenciadorDeIdiomas.get("textField.cpf"));
        email.setPromptText(GerenciadorDeIdiomas.get("textField.email"));
        logLogin.setPromptText(GerenciadorDeIdiomas.get("textField.login"));
        logSenha.setPromptText(GerenciadorDeIdiomas.get("textField.senha"));
        cadastra.setText(GerenciadorDeIdiomas.get("Button.cadastrar"));
        loga.setText(GerenciadorDeIdiomas.get("Button.login"));
    }

    @Override
    public void initialize() {
        setLanguage();
    }

    @FXML
    public void cadastraUsuario(){
        try {
            String userLogin = login.getText();
            String userPassword = senha.getText();
            String userNome = nome.getText();
            String userCpf = cpf.getText();
            String userEmail = email.getText();
            String userPasswordConfirm = senhaConfirm.getText();

            if(userEmail.isEmpty() || userCpf.isEmpty() || userNome.isEmpty() || userLogin.isEmpty() || userPassword.isEmpty() || userPasswordConfirm.isEmpty()) {
                throw new EmptyFieldException(GerenciadorDeIdiomas.get("Error.campoVazio"));
            }
            if(!userPassword.equals(userPasswordConfirm)) {
                throw new WrongPasswordException(GerenciadorDeIdiomas.get("Error.senhas"));
            }

            Usuario a = controller.cadastrarUsuario(userLogin, userPassword, userNome, userCpf, userEmail, false);

            if(a == null) {
                throw new AlreadyExistingUserException(GerenciadorDeIdiomas.get("Error.cadastrado"));
            }

            login.clear();
            senha.clear();
            nome.clear();
            cpf.clear();
            email.clear();
            senhaConfirm.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, GerenciadorDeIdiomas.get("Error.sucesso"), ButtonType.OK);
            alert.showAndWait();

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    @FXML
    public void loginUsuario() {
        try {
            String userLogin = logLogin.getText();
            String userPassword = logSenha.getText();
            if (userPassword.isEmpty() || userLogin.isEmpty()) {
                throw new EmptyFieldException(GerenciadorDeIdiomas.get("Error.campoVazioLogin"));
            }
            ControllerGUI.usuarioLogado = controller.loginUsuario(userLogin, userPassword);
            System.out.println(userLogin + " logado com sucesso!");
            mudarTelaPrincipal();
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }


    }

    @FXML
    public void mudarTelaPrincipal() throws Exception {
        ControllerGUI.mudarPagina(
                Page.PERFIL,
                (Stage) logSenha.getScene().getWindow()
        );
    }



}
