package com.vendaingressos.problema3_gui.Enum;

import com.vendaingressos.problema3_gui.GUI.*;

import java.io.File;

public enum Page {

    LOGIN(File.separator + "com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "loginScreen.fxml", Login.class),
    PERFIL(File.separator + "com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "mainScreen.fxml", MainPage.class),
    TODOS_EVENTOS(File.separator + "com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "allEvents.fxml", AllEvents.class),
    EVENTO_UNICO_ATIVO(File.separator + "com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "singleEvent.fxml", SingleEventActive.class),
    COMPRAR(File.separator + "com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "purchase.fxml", Purchase.class),
    TODAS_COMPRAS(File.separator + "com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "allPurchases.fxml", AllPurchases.class),
    EVENTO_UNICO_DESATIVADO(File.separator + "com" + File.separator + "vendaingressos" + File.separator + "problema3_gui" + File.separator + "singleEventInactive.fxml", SingleEventNotActive.class),


    ;
    public final String path;
    public final Class<?> controllerClass;

    Page(String path, Class<?> controllerClass) {
        this.path = path;
        this.controllerClass = controllerClass;
    }
}
