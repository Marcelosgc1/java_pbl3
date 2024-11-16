package com.vendaingressos.problema3_gui.models;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.interfaces.ComId;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Pagina<Tipo extends ComId> {
    private final Page pagina;
    private Tipo objeto = null;

    public Pagina(Page pagina, Tipo objeto) {
        this.pagina = pagina;
        this.objeto = objeto;
    }
    public Pagina(Page pagina){
        this.pagina = pagina;
    }

    public Page getPagina() {
        return pagina;
    }

    public void mudarDePagina(Stage stage) throws Exception{
        FXMLLoader loader = new FXMLLoader(ControllerGUI.class.getResource(pagina.path));

        if (objeto != null) {
            loader.setController(this.pagina.controllerClass.getDeclaredConstructor(objeto.getClass()).newInstance(objeto));
        }

        Scene cenaAtual = new Scene(loader.load());
        stage.setScene(cenaAtual);
        stage.show();
    }


}
