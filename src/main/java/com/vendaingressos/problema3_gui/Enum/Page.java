package com.vendaingressos.problema3_gui.Enum;

import com.vendaingressos.problema3_gui.controllers.*;

public enum Page {
    LOGIN("/com/vendaingressos/problema3_gui/loginScreen.fxml", ControllerLogin.class),
    PERFIL("/com/vendaingressos/problema3_gui/mainScreen.fxml", ControllerMainPage.class),
    TODOS_EVENTOS("/com/vendaingressos/problema3_gui/allEvents.fxml", ControllerAllEvents.class),
    EVENTO_UNICO("/com/vendaingressos/problema3_gui/singleEvent.fxml", ControllerSingleEvent.class),
    COMPRA("/com/vendaingressos/problema3_gui/purchase.fxml", ControllerPurchase.class)

    ;

    public final String path;
    public final Class<?> controllerClass;

    Page(String path, Class<?> controllerClass) {
        this.path = path;
        this.controllerClass = controllerClass;
    }
}
