package com.vendaingressos.problema3_gui.Enum;

public enum Pagina {
    /**
     * Enumerador com páginas da interface
     */
    LOGIN("/com/vendaingressos/problema3_gui/loginScreen.fxml"),
    PERFIL("/com/vendaingressos/problema3_gui/mainScreen.fxml"),
    TODOS_EVENTOS("/com/vendaingressos/problema3_gui/allEvents.fxml");

    public final String path;
    Pagina(String link) {
        this.path = link;
    }
}
