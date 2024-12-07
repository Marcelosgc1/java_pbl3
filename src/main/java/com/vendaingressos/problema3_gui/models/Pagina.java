package com.vendaingressos.problema3_gui.models;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.interfaces.GUI;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Pagina {
    private final Page pagina;
    private GUI controller;

    public Pagina(Page pagina){
        this.pagina = pagina;
    }

    public Page getPagina() {
        return pagina;
    }

    public GUI getController() {
        return controller;
    }

    public void setController(GUI controller) {
        this.controller = controller;
    }

    /**
     * Muda a página para a nova
     * @param stage Stage do sistema
     * @param objeto Array de objetos utilizado para inicializar o controller da página que será acessada
     */
    public void mudarDePagina(Stage stage, Object[] objeto) throws Exception{
        FXMLLoader loader = new FXMLLoader(ControllerGUI.class.getResource(pagina.path));

        loader.setController(
                this.pagina.controllerClass
                        .getDeclaredConstructors()[0]
                        .newInstance(objeto == null ? new Object[]{} : objeto)
        );
        controller = loader.getController();
        Scene cenaAtual = new Scene(loader.load());
        stage.setScene(cenaAtual);
        stage.show();
    }

    /**
     * Caso a página já exista na stack, esse método é chamado para ir à nova página
     * @param stage Stage do sistema
     */
    public void mudarDePagina(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(ControllerGUI.class.getResource(pagina.path));
        loader.setController(
                this.controller
        );
        this.controller.initialize();
        Scene cenaAtual = new Scene(loader.load());
        stage.setScene(cenaAtual);
        stage.show();
    }



}
