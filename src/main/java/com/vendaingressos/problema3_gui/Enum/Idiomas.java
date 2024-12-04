package com.vendaingressos.problema3_gui.Enum;

public enum Idiomas {


    PTBR("Português BR", "pt"),
    EN("English", "en"),
    ES("Español", "es"),
    ;


    public final String texto;
    public final String localizacao;

    Idiomas(String texto, String localizacao) {
        this.texto = texto;
        this.localizacao = localizacao;
    }
    public String toString() {
        return texto;
    }
}
