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

package vendaingressos.repositories;

import com.google.gson.Gson;
import vendaingressos.models.Usuario;
import vendaingressos.abstracts.Repositorio;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;


public class RepositorioUsuario extends Repositorio<Usuario> {


    public RepositorioUsuario() {
        super(Usuario.class);
    }

    /**
     * Gera caminho para o arquivo que guarda as infos de certo usuário
     * @param path caminho da pasta princial
     * @param id identificador do usuário
     * @return retorna caminho do arquivo com informações do usuário
     */
    @Override
    public String gerarCaminho(String path, String id){
        return path + "\\" + id + "\\" + classe.getSimpleName() + ".json";
    }

    /**
     * Gera caminho para a pasta onde se encontra as informações do usuário
     * @param path caminho da pasta principal
     * @param id identificador do usuário
     * @return caminho da pasta com as infos do usuário
     */
    @Override
    public String gerarCaminhoPasta(String path, String id) {
        return path + "\\" + id;
    }

    /**
     * Salva usuário
     * @param path caminho da pasta principal
     * @param obj objeto que será salvo
     * @return True se salvamento ocorrer como esperado, false se não
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    public Boolean salvarUsuario(String path, Usuario obj) throws IOException {
        return super.salvar(path + "\\" + "Usuarios", obj);
    }

    /**
     * Carrega usuário
     * @param path caminho da pasta principal
     * @param id identificador do objeto que será carregado
     * @return Objeto do tipo Usuario
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    public Usuario carregarUsuario(String path, String id) throws IOException {
        return super.carregar(path + "\\" + "Usuarios", id);
    }

    /**
     * Carrega usuário
     * @param path caminho da pasta principal
     * @return Lista com todos os usuários
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    public List<Usuario> carregarTodosUsuarios(String path) throws IOException {
        return carregarTodosObjetos(path + "\\Usuarios");
    }

    /**
     * Carrega todos os objetos em uma pasta
     * @param path pasta de onde serão carregados os objetos
     * @return Lista de usuários
     * @throws IOException caso haja problema na hora de acessar as pastas
     */
    @Override
    protected List<Usuario> carregarTodosObjetos(String path) throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get(path))) {
            return stream.map(id -> {
                try {
                    return carregar(path, id.getFileName().toString());
                }catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).filter(Objects::nonNull).toList();
        }


    }

    /**
     * Salva HashSet que contem logins dos usuários
     * @param path caminho onde será salvo o arquivo
     * @param obj HashSet que será salvo
     */
    public void salvarSet(String path, HashSet<String> obj){
        try (FileWriter writer = new FileWriter(path + "\\setUsuarios.json")) {
            new Gson().toJson(obj, writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Carrega o HashSet que tem logins dos usuários
     * @param path caminho onde se encontra o arquivo com os logins
     * @return HashSet dos logins dos usuários
     */
    public HashSet<String> carregarSet(String path){
        try (FileReader reader = new FileReader(path + "\\setUsuarios.json")){
            return new Gson().fromJson(reader, HashSet.class);
        } catch (IOException e){
            return null;
        }
    }




}
