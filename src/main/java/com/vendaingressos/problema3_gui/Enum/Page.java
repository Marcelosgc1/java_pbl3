package com.vendaingressos.problema3_gui.Enum;

import com.vendaingressos.problema3_gui.controllers.*;

import java.io.File;

public enum Page {
    LOGIN("/com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "loginScreen.fxml", ControllerLogin.class),
    PERFIL("/com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "mainScreen.fxml", ControllerMainPage.class),
    TODOS_EVENTOS("/com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "allEvents.fxml", ControllerAllEvents.class),
    EVENTO_UNICO("/com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "singleEvent.fxml", ControllerSingleEvent.class),
    COMPRAR("/com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "purchase.fxml", ControllerPurchase.class),
    TODAS_COMPRAS("/com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "allPurchases.fxml", ControllerAllPurchases.class)

    ;

    public final String path;
    public final Class<?> controllerClass;

    Page(String path, Class<?> controllerClass) {
        this.path = path;
        this.controllerClass = controllerClass;
    }
}
