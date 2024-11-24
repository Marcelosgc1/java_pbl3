package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.Enum.Idiomas;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.models.Pagina;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

public class Languages {
    @FXML
    private ComboBox<Idiomas> language;

    @FXML
    public void initialize() {
        language.getItems().addAll(Idiomas.values());
        language.setValue(ControllerGUI.idioma);
    }


    @FXML
    private void setLanguage() {
        ControllerGUI.setLanguage(this.language.getValue());
        Pagina peek = ControllerGUI.pageStack.peek();
        peek.getController().setLanguage();
    }


}
