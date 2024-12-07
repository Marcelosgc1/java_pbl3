package com.vendaingressos.problema3_gui.Enum;

import com.vendaingressos.problema3_gui.GUI.*;

import java.io.File;

public enum Page {

    /**
     * Enumerador das páginas do sistema
     * Primeiro campo se trata do local dos arquivos no projeto
     * Segundo campo se trata da classe relacionada à página
     */
    LOGIN("loginScreen.fxml", Login.class),
    PERFIL("mainScreen.fxml", MainPage.class),
    TODOS_EVENTOS("allEvents.fxml", AllEvents.class),
    EVENTO_UNICO_ATIVO("singleEvent.fxml", SingleEventActive.class),
    COMPRAR("purchase.fxml", Purchase.class),
    TODAS_COMPRAS("allPurchases.fxml", AllPurchases.class),
    EVENTO_UNICO_DESATIVADO("singleEventInactive.fxml", SingleEventNotActive.class),
    TODOS_INGRESSOS("allTickets.fxml", AllTickets.class)

    ;

    public final String path;
    public final Class<?> controllerClass;

    Page(String path, Class<?> controllerClass) {
        this.path = path;
        this.controllerClass = controllerClass;
    }
}
