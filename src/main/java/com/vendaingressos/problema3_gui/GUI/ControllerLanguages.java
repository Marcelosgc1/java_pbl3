package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.Idiomas;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.controllers.GerenciadorDeIdiomas;
import com.vendaingressos.problema3_gui.models.Pagina;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

public class ControllerLanguages {
    @FXML
    private ComboBox<Idiomas> language;

    @FXML
    void initialize() {
        language.getItems().addAll(Idiomas.values());

        language.setConverter(new StringConverter<>() {
            @Override
            public String toString(Idiomas language) {
                return language != null ? language.texto : "";
            }

            @Override
            public Idiomas fromString(String s) {
                return null;
            }
        });
        language.setValue(Idiomas.PTBR);
    }


    @FXML
    private void setLanguage() {
        GerenciadorDeIdiomas.setLanguage(this.language.getValue().localizacao);
        Pagina<?> peek = ControllerGUI.pageStack.peek();
        peek.getController().setLanguage();
    }


}
