package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.exceptions.AlreadyExistingUserException;
import com.vendaingressos.problema3_gui.exceptions.EmptyFieldException;
import com.vendaingressos.problema3_gui.exceptions.WrongPasswordException;
import com.vendaingressos.problema3_gui.models.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import static com.vendaingressos.problema3_gui.Main.controller;

public class ControllerLogin {

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
    private Label username;

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
                throw new EmptyFieldException("Preencha todos os campos para cadastro!");
            }
            if(!userPassword.equals(userPasswordConfirm)) {
                throw new WrongPasswordException("As senhas são diferentes!");
            }

            Usuario a = controller.cadastrarUsuario(userLogin, userPassword, userNome, userCpf, userEmail, false);

            if(a == null) {
                throw new AlreadyExistingUserException("Há usuário com este login já cadastrado!");
            }

            login.clear();
            senha.clear();
            nome.clear();
            cpf.clear();
            email.clear();
            senhaConfirm.clear();

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Usuario Cadastrado com sucesso!", ButtonType.OK);
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
                throw new EmptyFieldException("Preencha os campos de login e senha!");
            }
            Usuario usuarioLogado = controller.loginUsuario(userLogin, userPassword);
            System.out.println(userLogin + " loggado com sucesso!");
            mudarTelaPrincipal(usuarioLogado);
        }
        catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }


    }

    @FXML
    public void mudarTelaPrincipal(Usuario usuarioLogado) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/vendaingressos/problema3_gui/mainScreen.fxml"));
            Scene mainScene = new Scene(loader.load());

            ControllerMainPage newController = loader.getController();
            newController.initialize(usuarioLogado);

            Stage stage = (Stage) logSenha.getScene().getWindow();
            stage.setScene(mainScene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
