package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.models.Pagina;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ControllerTopBar {
    @FXML
    private MenuButton perfil;
    @FXML
    private MenuItem perfilMenu;
    @FXML
    private MenuItem logout;
    @FXML
    private Button backButton;


    @FXML
    public void initialize() {
        perfil.setText(ControllerGUI.usuarioLogado.getLogin());
        perfilMenu.setText("Ver Perfil");
        logout.setText("Logout");
        backButton.setText("Voltar");



    }

    public void voltarParaLogin() throws Exception {
        ControllerGUI.pageStack.clear();
        ControllerGUI.mudarPagina(
                Page.LOGIN,
                (Stage) perfil.getScene().getWindow()
        );
    }

    public void irParaPerfil() throws Exception {
        if (ControllerGUI.pageStack.peek().getPagina().equals(Page.PERFIL)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Você já está na página de perfil!");
            alert.showAndWait();
            return;
        }
        ControllerGUI.mudarPagina(
                Page.PERFIL,
                (Stage) perfil.getScene().getWindow()
        );
    }

    public void voltar() throws Exception {
        ControllerGUI.pageStack.pop();
        Pagina<?> lastPage = ControllerGUI.pageStack.pop();
        if (lastPage != null) {
            ControllerGUI.mudarPagina(lastPage, (Stage) perfil.getScene().getWindow());
            return;
        }
        ControllerGUI.mudarPagina(Page.LOGIN, (Stage) perfil.getScene().getWindow());


    }



}


