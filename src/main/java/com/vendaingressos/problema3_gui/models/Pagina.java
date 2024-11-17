package com.vendaingressos.problema3_gui.models;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.interfaces.ComId;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Pagina<Tipo extends ComId> {
    private final Page pagina;
    private Object[] objeto = null;

    public Pagina(Page pagina, Object[] objeto) {
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
            loader.setController(
                    this.pagina.controllerClass
                            .getDeclaredConstructors()[0]
                            .newInstance(objeto)
            );

        }

        Scene cenaAtual = new Scene(loader.load());
        stage.setScene(cenaAtual);
        stage.show();
    }


}
