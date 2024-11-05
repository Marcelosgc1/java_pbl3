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

package com.vendaingressos.problema3_gui.repositories;

import com.vendaingressos.problema3_gui.abstracts.Repositorio;
import com.vendaingressos.problema3_gui.models.Evento;

import java.io.IOException;
import java.util.List;

public class RepositorioEvento extends Repositorio<Evento> {

    public RepositorioEvento() {
        super(Evento.class);
    }

    /**
     * Salva Evento
     * @param path caminho principal
     * @param obj objeto que será salvo
     * @return True se o salvamento ocorrer como esperado, ou false se não
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    public Boolean salvarEvento(String path, Evento obj) throws IOException {
        return super.salvar(path, obj);
    }

    /**
     * Carrega Eventos
     * @param path caminho principal
     * @param id identificador do Evento
     * @return Objeto do tipo Evento
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    public Evento carregarEvento(String path, String id) throws IOException {
        return super.carregar(path, id);
    }

    /**
     * Carrega todos os eventos
     * @param path caminho principal
     * @return Lista de Eventos
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    public List<Evento> carregarTodosEventos(String path) throws IOException {
        return super.carregarTodosObjetos(path);
    }
}
