package com.vendaingressos.problema3_gui.models;

import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.interfaces.ComId;

public class Notificacao {
    public ComId modelo;
    public final Page page;

    public Notificacao(ComId modelo, Page page) {
        this.modelo = modelo;
        this.page = page;
    }


    @Override
    public String toString() {
        switch (page) {
            case TODOS_INGRESSOS -> {
                return "Evento está aproximando!";
            }
            case EVENTO_UNICO_DESATIVADO -> {
                return "Evento finalizado!";
            }
            default -> {return "Compra realizada com sucesso!";}
        }
    }
}
