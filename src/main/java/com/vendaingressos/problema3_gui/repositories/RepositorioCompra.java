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
import com.vendaingressos.problema3_gui.models.Compra;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RepositorioCompra extends Repositorio<Compra> {

    public RepositorioCompra() {
        super(Compra.class);
    }

    /**
     * Salva Compra
     * @param path caminho principal
     * @param obj objeto que será salvo
     * @param login login do usuário a qual a compra pertence
     * @return True se o salvamento ocorrer como esperado, ou false se não
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    public Boolean salvarCompra(String path, Compra obj, String login) throws IOException {
        return super.salvar(path + File.separator + login, obj);
    }

    /**
     * Carrega Compra
     * @param path caminho principal
     * @param id identificador da compra
     * @param login do usuário o qual a compra pertence
     * @return Objeto do tipo Compra
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    public Compra carregarCompra(String path, String id, String login) throws IOException {
        return super.carregar(path + File.separator + login, id);
    }

    /**
     * Carrega todas as compras de um usuário
     * @param path caminho principal
     * @param login do usuário que quer encontrar todas suas compras
     * @return Lista de Compras do usuário
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    public List<Compra> carregarTodosCompra(String path, String login) throws IOException{
        return super.carregarTodosObjetos(path + File.separator + login);
    }
}
