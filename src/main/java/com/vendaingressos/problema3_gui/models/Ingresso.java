/******************************************************************************************
 Autor: Marcelo Barrêto Tavares
 Componente Curricular: EXA 863 - MI - Programação
 Concluído em: 14/10/2024
 Declaro que este código foi elaborado por mim de forma individual e não contêm nenhum
 trecho de código de outro colega ou de outro autor, tais como provindos de livros e
 apostilas, e páginas ou documentos eletrônicos da Internet. Qualquer trecho de código
 de outra autoria que não a minha está destacado com uma citação para o autor e a fonte
 do código, e estou ciente que estes trechos não serão considerados para fins de avaliação.
 *******************************************************************************************/

package com.vendaingressos.problema3_gui.models;

import com.vendaingressos.problema3_gui.interfaces.ComId;

import java.net.CacheRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class Ingresso implements ComId {
    // atributos
    private Double preco;
    private Boolean status;
    private String evento;
    private final UUID id;
    
    // construtor
    public Ingresso(Evento evento, Double preco) {
        this.evento = evento.getId();
        this.preco = preco;
        this.status = true;
        this.id = UUID.randomUUID();
    }

    // construtor para quando não houver valor do ingresso
    public Ingresso(Evento evento) {
        this.evento = evento.getId();
        this.preco = evento.getPreco();
        this.status = true;
        this.id = UUID.randomUUID();
    }


    // getters e setters

    @Override
    public String getId() {
        return id.toString();
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    //outros metodos
    /**
     * @return o Status do Ingresso
     */
    public boolean isAtivo(){
        return getStatus();
    }


    /**
     * Atualiza Status do ingresso se a data do evento já não houver passado
     * @param dataAtual data do dia
     * @param dataEvento data do evento
     * @return True se for bem sucedida a atualização e False se não.
     */
    public Boolean cancelar(Calendar dataAtual, Calendar dataEvento){
        if (dataAtual.before(dataEvento)) {
            setStatus(false);
            return true;
        }
        return false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingresso ingresso = (Ingresso) o;
        return Objects.equals(preco, ingresso.preco) && Objects.equals(status, ingresso.status) && Objects.equals(evento, ingresso.evento) && Objects.equals(id, ingresso.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preco, status, evento, id);
    }
}
