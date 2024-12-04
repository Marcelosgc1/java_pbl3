package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.interfaces.GUI;
import com.vendaingressos.problema3_gui.models.Pagina;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class TopBar implements GUI {
    @FXML
    private MenuButton perfil;
    @FXML
    private MenuItem perfilMenu;
    @FXML
    private MenuItem logout;
    @FXML
    private Button backButton;


    @Override
    public void setLanguage() {
        perfil.setText(ControllerGUI.usuarioLogado.getLogin());
        perfilMenu.setText(ControllerGUI.get("MenuItem.perfil"));
        logout.setText(ControllerGUI.get("MenuItem.logout"));
        backButton.setText(ControllerGUI.get("Button.voltar"));
    }

    @FXML
    public void initialize() {
        setLanguage();
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
            Alert alert = new Alert(Alert.AlertType.INFORMATION, ControllerGUI.get("Error.jaEmPerfil"));
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
        Pagina lastPage = ControllerGUI.pageStack.pop();
        if (lastPage != null) {
            ControllerGUI.mudarPagina(lastPage, (Stage) perfil.getScene().getWindow());
            return;
        }
        ControllerGUI.mudarPagina(Page.LOGIN, (Stage) perfil.getScene().getWindow());


    }



}

