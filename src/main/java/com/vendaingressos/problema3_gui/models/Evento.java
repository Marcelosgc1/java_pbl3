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

import java.util.*;

public class Evento implements ComId {
    //atributos
    private String nome;
    private String descricao;
    private final Calendar data;
    private Boolean status;
    private Integer assentosDisponiveis;
    private Double preco;
    private final UUID id;
    private HashMap<String,String> comentarios;

    //construtor
    public Evento(String nome, String descricao, Calendar data, Integer assentosDisponiveis) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.status = true;
        this.assentosDisponiveis = assentosDisponiveis;
        this.preco = 0.0;
        this.id = UUID.randomUUID();
        this.comentarios = new HashMap<>();
    }

    public Evento(String nome, String descricao, Calendar data, Integer assentosDisponiveis, Double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.data = data;
        this.status = true;
        this.assentosDisponiveis = assentosDisponiveis;
        this.preco = preco;
        this.id = UUID.randomUUID();
        this.comentarios = new HashMap<>();
    }

    //getters e setters
    @Override
    public String getId() {
        return id.toString();
    }

    public HashMap<String, String> getComentarios() {
        return comentarios;
    }

    public void setComentarios(HashMap<String, String> comentarios) {
        this.comentarios = comentarios;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Calendar getData() {
        return data;
    }

//    public void setData(Calendar data) {
//        this.data = data;
//    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getAssentosDisponiveis() {
        return assentosDisponiveis;
    }

    public void setAssentosDisponiveis(Integer assentosDisponiveis) {
        this.assentosDisponiveis = assentosDisponiveis;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    //outros metodos
    /**
     * adiciona assento ao evento
     * @param assento quantidade a ser adicionada
     * @return Se operação bem sucedida
     */
    public Boolean adicionarAssento(Integer assento){
        if (assento > 0){
            assentosDisponiveis += assento;
            return true;
        }
        return false;
    }

    /**
     * remove o assento do evento
     * @param assento quantidade a ser removida
     * @return True se for bem sucedida a remoção e False se não.
     */
    public Boolean removerAssento(Integer assento){
        if (assento <= 0 || assentosDisponiveis - assento < 0) {
            return false;
        }
        assentosDisponiveis -= assento;
        return true;
    }


    /**
     * @param dataAtual dia presente
     * @return True se evento está ativo ou false se não
     */
    public Boolean isAtivo(Calendar dataAtual){
        if (!getStatus()){
            return false;
        }
        setStatus(data.after(dataAtual));
        return status;
    }

    @Override
    public String toString() {
        return this.nome + " - " + this.descricao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Evento evento = (Evento) o;
        return Objects.equals(nome, evento.nome) && Objects.equals(descricao, evento.descricao) && Objects.equals(status, evento.status) && Objects.equals(assentosDisponiveis, evento.assentosDisponiveis) && Objects.equals(id, evento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, descricao, status, assentosDisponiveis, id);
    }
}
