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

package com.vendaingressos.problema3_gui.abstracts;

import com.google.gson.Gson;
import com.vendaingressos.problema3_gui.interfaces.ComId;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class Repositorio<Tipo extends ComId> {

    protected final Class<Tipo> classe;

    public Repositorio(Class<Tipo> classe) {
        this.classe = classe;
    }

    /**
     * Gera caminho para o arquivo que guarda as infos de certo objeto
     * @param path caminho da pasta princial
     * @param id identificador do objeto
     * @return retorna caminho do arquivo com informações do objeto
     */
    public String gerarCaminho(String path, String id){
        return path + "\\" + classe.getSimpleName() + "\\" + id + ".json";
    }

    /**
     * Gera caminho para a pasta onde se encontra o arquivo com as informações do objeto
     * @param path caminho da pasta principal
     * @param id identificador do objeto
     * @return caminho da pasta com as infos do objeto
     */
    public String gerarCaminhoPasta(String path, String id){
        return path + "\\" + classe.getSimpleName();
    }

    /**
     * Salva objeto de tipo genérico
     * @param path caminho principal
     * @param obj objeto que será salvo
     * @return True se salvamento ocorrer como esperado, ou false se não
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    protected Boolean salvar(String path, Tipo obj) throws IOException {
        Gson gson = new Gson();

        String pasta = gerarCaminhoPasta(path, obj.getId());

        if (pasta != null) {
            Path caminho = Paths.get(pasta);
            if (!Files.exists(caminho)) {
                Files.createDirectories(caminho);
            }
            String caminhoComId = gerarCaminho(path, obj.getId());
            try (FileWriter writer = new FileWriter(caminhoComId)) {
                gson.toJson(obj, writer);
                return true;
            }
        }

        return false;
    }

    /**
     * Carrega objeto genérico
     * @param path caminho principal
     * @param id identificador do objeto
     * @return Objeto do tipo genérico
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    protected Tipo carregar(String path, String id) throws IOException{
        Gson gson = new Gson();

        String caminhoComId = encontrarId(path, id);
        if (caminhoComId != null) {
            try (FileReader reader = new FileReader(caminhoComId)) {
                return gson.fromJson(reader, classe);
            }
        }
        return null;
    }

    /**
     * Verifica se existe algum arquivo ou pasta com aquele ID
     * @param path caminho principal
     * @param id identificador do arquivo
     * @return Caminho se houver achado, ou null se não
     */
    public String encontrarId(String path, String id) {
        String caminho = gerarCaminho(path, id);

        if (caminho!=null){
            Path arquivo = Paths.get(caminho);
            return (Files.exists(arquivo)) ? caminho : null;
        }
        return null;
    }

    /**
     * Carrega todos os objetos de dada pasta
     * @param path caminho onde se encontram os arquivos
     * @return Lista de tipo genérico
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    protected List<Tipo> carregarTodosObjetos(String path) throws IOException {
        Path caminho = Paths.get(gerarCaminhoPasta(path, null));
        if (!Files.exists(caminho)) {
            return null;
        }
        try (Stream<Path> stream = Files.list(caminho)) {
            return stream.map(id -> {
                try {
                    return carregar(path, id.getFileName().toString().substring(0, id.getFileName().toString().length() - 5));
                }catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).filter(Objects::nonNull).toList();
        }


    }


}
