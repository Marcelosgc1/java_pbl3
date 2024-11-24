package com.vendaingressos.problema3_gui.GUI;

import com.vendaingressos.problema3_gui.abstracts.SingleEvent;
import com.vendaingressos.problema3_gui.controllers.ControllerGUI;
import com.vendaingressos.problema3_gui.exceptions.AlreadyExistingReviewException;
import com.vendaingressos.problema3_gui.exceptions.NoTicketException;
import com.vendaingressos.problema3_gui.interfaces.GUI;
import com.vendaingressos.problema3_gui.models.Evento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.vendaingressos.problema3_gui.Main.controller;
import static com.vendaingressos.problema3_gui.controllers.ControllerGUI.usuarioLogado;

public class SingleEventNotActive extends SingleEvent implements GUI {

    @FXML
    private ListView<String> comentarios;
    @FXML
    private TextArea novocomet;
    @FXML
    private Button comentar;


    public SingleEventNotActive(Evento evento) {
        super(evento);
    }

    @FXML
    public void initialize() {
        setLanguage();

        ObservableList<String> lista = evento.getComentarios().entrySet().stream().map(a -> a.getKey() + ":\n" + a.getValue()).collect(Collectors.toCollection(FXCollections::observableArrayList));
        comentarios.setItems(lista);
    }

    public void comentar() throws IOException {
        String comentario = novocomet.getText();
        if (comentario.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, ControllerGUI.get("Error.NoContentReview"));
            alert.showAndWait();
            return;
        }
        try {
            controller.realizarComentario(usuarioLogado, evento, comentario, Calendar.getInstance());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.showAndWait();
            novocomet.clear();
        }catch (AlreadyExistingReviewException e){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, ControllerGUI.get("Error.AlreadyReview"), ButtonType.OK, ButtonType.CANCEL);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.isPresent() && buttonType.get() == ButtonType.OK) {
                controller.comentar(evento, usuarioLogado, comentario);
                novocomet.clear();
            }
        }catch (NoTicketException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, ControllerGUI.get("Error.NoTicket"));
            alert.showAndWait();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, ControllerGUI.get("Error.Exception"));
            alert.showAndWait();
        }finally {
            initialize();
        }
    }

    public void setLanguage() {
        super.setLanguage();
        novocomet.setPromptText(ControllerGUI.get("TextArea.comentario"));
        comentar.setText(ControllerGUI.get("Button.comentar"));
    }
}
