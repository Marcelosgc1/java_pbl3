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

package com.vendaingressos.problema3_gui.controllers;

import com.vendaingressos.problema3_gui.Enum.FormaDePagamento;
import com.vendaingressos.problema3_gui.Enum.Page;
import com.vendaingressos.problema3_gui.exceptions.*;
import com.vendaingressos.problema3_gui.models.Compra;
import com.vendaingressos.problema3_gui.models.Evento;
import com.vendaingressos.problema3_gui.models.Ingresso;
import com.vendaingressos.problema3_gui.models.Usuario;
import com.vendaingressos.problema3_gui.repositories.RepositorioCompra;
import com.vendaingressos.problema3_gui.repositories.RepositorioEvento;
import com.vendaingressos.problema3_gui.repositories.RepositorioIngresso;
import com.vendaingressos.problema3_gui.repositories.RepositorioUsuario;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.vendaingressos.problema3_gui.Main.controller;

public class Controller {
    //atributos
    private HashSet<String> usuarios;
    private List<String> eventos;
    private final RepositorioIngresso ri = new RepositorioIngresso();
    private final RepositorioUsuario ru = new RepositorioUsuario();
    private final RepositorioCompra rc = new RepositorioCompra();
    private final RepositorioEvento re = new RepositorioEvento();
    private String path;

    public Controller(String path) {
        this.usuarios = new HashSet<>();
        this.eventos = new ArrayList<>();
        this.path = path;

    }
    public Controller() {
        this.usuarios = new HashSet<>();
        this.eventos = new ArrayList<>();
        this.path = "repository";

    }

    //getters e setters

    public HashSet<String> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(HashSet<String> usuarios) {
        this.usuarios = usuarios;
    }

    public List<String> getEventos() {
        return eventos;
    }

    public void setEventos(List<String> eventos) {
        this.eventos = eventos;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }


    //outros metodos

    public Usuario loginUsuario(String login, String senha) throws Exception {
        Usuario usuario = ru.carregarUsuario(path, login);
        if (usuario == null) {
            throw new UserNotFoundException("Usuario não encontrado");
        }
        if (usuario.login(login, senha)){
            return usuario;
        }
        throw new WrongPasswordException("Senha incorreta");

    }


    /**
     * Cria um usuário e o põe no HashMap que guarda todos os usuários
     * @param login do usuário a ser cadastrado
     * @param senha do usuário a ser cadastrado
     * @param nome do usuário a ser cadastrado
     * @param cpf do usuário a ser cadastrado
     * @param email do usuário a ser cadastrado
     * @param admin para saber se o usuário é um admin ou não.
     * @return usuário construído
     */
    public Usuario cadastrarUsuario(String login, String senha, String nome, String cpf, String email, Boolean admin) throws IOException {
        HashSet<String> usuarios = ru.carregarSet(path);
        if (usuarios == null) {
            usuarios = new HashSet<>();
        }
        if(!usuarios.contains(login)){
            Usuario usuario = new Usuario(login, senha, nome, cpf, email, admin);
            ru.salvarUsuario(path, usuario);
            usuarios.add(usuario.getLogin());
            ru.salvarSet(path, usuarios);
            return usuario;
        }
        return null;
    }

    /**
     * Cria um evento e o coloca na lista de todos os eventos.
     * @param usuario Usuário que irá criar o evento
     * @param nomeDoEvento para construção do evento
     * @param descricao para construção do evento
     * @param data para construção do evento
     * @param assentos para construção do evento
     * @param preco para construção do evento
     * @return Evento construído
     * @throws SecurityException caso o usuário que esteja criando não seja um Admin
     */
    public Evento cadastrarEvento(Usuario usuario, String nomeDoEvento, String descricao, Calendar data, Integer assentos, Double preco) throws SecurityException, IOException {
        if(!usuario.getAdmin()){
            throw new SecurityException("Somente administradores podem cadastrar eventos.");
        }

        Evento evento = new Evento(nomeDoEvento, descricao, data, assentos, preco);
        re.salvarEvento(path, evento);
        List<String> eventos = getEventos();
        eventos.add(evento.getId());
        setEventos(eventos);
        return evento;
    }


    /**
     * Adiciona um assento na lista de assentos disponíveis de algum evento
     * @param nomeDoEvento nome do evento no qual o assento será adicionado
     * @param assento o assento que irá ser adicionado ao evento
     */
    public void adicionarAssentoEvento(String nomeDoEvento, Integer assento) throws IOException {
        List<Evento> eventos = re.carregarTodosEventos(path);
        for (Evento evento : eventos){
            if (evento.getNome().equals(nomeDoEvento)){
                evento.adicionarAssento(assento);
                re.salvarEvento(path, evento);
                return;
            }
        }
    }



    /** Cancela ingresso que pertence a um usuário
     * @param usuario que terá o ingresso cancelado
     * @param ingresso que será cancelado
     * @param dataAtual dia presente
     * @return True, se for bem sucedido o cancelamento e False se mal sucedido.
     */
    public String cancelarCompra(Usuario usuario, Ingresso ingresso, Calendar dataAtual) throws IOException {
        Evento evento = re.carregarEvento(path, ingresso.getEvento());
        if(!ingresso.cancelar(dataAtual, evento.getData())){
            System.out.println("aa");
            return null;
        }
        if(!usuario.getIngressos().remove(ingresso.getId())){
            System.out.println("bb");
            return null;
        }


        evento.adicionarAssento(1);

        List<String> ingressoCancelado = new ArrayList<>();
        ingressoCancelado.add(ingresso.getId());
        Compra c = new Compra(usuario.getEmail(),dataAtual, usuario.getId(), ingressoCancelado, evento.getPreco() * -1);
        System.out.println("CANCELOU");
        rc.salvarCompra(path, c, usuario.getId());
        re.salvarEvento(path, evento);
        ri.salvarIngresso(path, ingresso, usuario.getId());
        ru.salvarUsuario(path, usuario);
        return c.gerarRecibo();
    }

    /**
     * @param usuario que terá seus ingressos listados
     * @return Lista de Ingressos
     */
    public List<Ingresso> listarIngressosComprados(Usuario usuario) throws IOException {
        return ri.carregarTodosIngressos(path, usuario.getLogin());
    }


    /**
     * @param usuario que terá seus ingressos listados
     * @return Lista de Ingressos
     */
    public List<Ingresso> listarIngressosAtualizado(Usuario usuario, Calendar dataAtual) throws IOException {
        List<Ingresso> ingressos = listarIngressosComprados(usuario);
        if (ingressos == null) {
            return null;
        }
        return ingressos.stream()
                .filter(ingresso -> !ingresso.getStatus().equals("cancelado"))
                .map(ingresso -> updateIngresso(ingresso, dataAtual, usuario.getId()))
                .toList();
    }

    private Ingresso updateIngresso(Ingresso ingresso, Calendar dataAtual, String login){
        try{
            Evento evento = controller.carregarEvento(ingresso.getEvento());
            Calendar clone = (Calendar) evento.getData().clone();
            if(!dataAtual.before(clone) && (ingresso.getStatus().equals("ativo") || ingresso.getStatus().equals("notificado"))){
                ingresso.setStatus("inativo");
                ControllerGUI.adicionarNotificacao(evento, Page.EVENTO_UNICO_DESATIVADO);

                ri.salvarIngresso(path, ingresso, login);
                return ingresso;
            }
            clone.add(Calendar.DAY_OF_YEAR, -7);
            if (!dataAtual.before(clone) && ingresso.getStatus().equals("ativo")) {
                ingresso.setStatus("notificado");
                ControllerGUI.adicionarNotificacao(ingresso, Page.TODOS_INGRESSOS);
            }
            ri.salvarIngresso(path, ingresso, login);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ingresso;
    }

    /**
     * @param dataAtual data presente
     * @return Lista de todos os eventos disponíveis
     */
    public List<Evento> listarEventosDisponiveis(Calendar dataAtual) throws IOException {
        List<Evento> eventos = re.carregarTodosEventos(path);
        //filtra a lista de evntos do controller, utilizando o metodo isAtivo(), então transforma de volta em uma lista utilizando .toList()
        return new ArrayList<>(eventos.stream().filter(evento -> evento.isAtivo(dataAtual)).toList());
    }

    /**
     * @return Lista de todos os eventos
     */
    public List<Evento> listarEventos(Calendar dia) throws IOException {
        List<Evento> eventos = re.carregarTodosEventos(path);
        eventos.forEach(evento -> {
            try {
                evento.isAtivo(dia);
                re.salvarEvento(path, evento);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        return eventos;
    }

    /**
     * Altera senha do usuário
     * @param usuario objeto que terá seu atributo alterado
     * @param senha novo atributo
     * @throws IOException em caso de não conseguir salvar o usuário
     */
    public void alterarSenha(Usuario usuario, String senha) throws IOException {
        usuario.setSenha(senha);
        ru.salvarUsuario(path, usuario);
    }

    /**
     * Altera cpf do usuário
     * @param usuario objeto que terá seu atributo alterado
     * @param cpf novo atributo
     * @throws IOException em caso de não conseguir salvar o usuário
     */
    public void alterarCpf(Usuario usuario, String cpf) throws IOException {
        usuario.setCpf(cpf);
        ru.salvarUsuario(path, usuario);
    }

    /**
     * Altera nome do usuário
     * @param usuario objeto que terá seu atributo alterado
     * @param nome novo atributo
     * @throws IOException em caso de não conseguir salvar o usuário
     */
    public void alterarNome(Usuario usuario, String nome) throws IOException {
        usuario.setNome(nome);
        ru.salvarUsuario(path, usuario);
    }

    /**
     * Altera email do usuário
     * @param usuario objeto que terá seu atributo alterado
     * @param email novo atributo
     * @throws IOException em caso de não conseguir salvar o usuário
     */
    public void alterarEmail(Usuario usuario, String email) throws IOException {
        usuario.setEmail(email);
        ru.salvarUsuario(path, usuario);
    }

    /**
     * Usuário realiza comentário sobre evento, caso tenha comprado ingresso dele e o evento já tenha passado.
     * @param usuario Usuário que vai realizar o comentário
     * @param nomeEvento nome do evento o qual será realizado comentário
     * @param comentario Conteúdo do comentário
     * @throws IOException caso não consiga carregar ingressos do Usuário
     */
    public Boolean realizarComentario(Usuario usuario, String nomeEvento, String comentario, Calendar dataAtual) throws IOException {
        List<Ingresso> ingressos = ri.carregarTodosIngressos(path, usuario.getId());
        if (ingressos == null) {
            return false;
        }

        List<Evento> eventos = re.carregarTodosEventos(path);
        if (eventos == null) {
            return false;
        }

        for (Evento evento : eventos) {
            if (!evento.getNome().equals(nomeEvento)) {
                continue;
            }
            for (Ingresso ingresso : ingressos) {
                if (!ingresso.getEvento().equals(evento.getId())) {
                    continue;
                }
                if (!evento.isAtivo(dataAtual)) {
                    evento.getComentarios().put(usuario.getId(), comentario);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public void realizarComentario(Usuario usuario, Evento evento, String comentario, Calendar dataAtual) throws IOException {
        List<Ingresso> ingressos = ri.carregarTodosIngressos(path, usuario.getId());
        System.out.println(ingressos);
        if (ingressos == null) {
            throw new NoTicketException();
        }
        for (Ingresso ingresso : ingressos) {
            System.out.println(ingresso);
            if (!ingresso.getEvento().equals(evento.getId())) {
                continue;
            }
            if (!evento.isAtivo(dataAtual)) {
                HashMap<String, String> comentarios = evento.getComentarios();
                if(comentarios.get(usuario.getId()) != null) {
                    throw new AlreadyExistingReviewException();
                }
                comentar(evento, usuario, comentario);
                return;
            }
            throw new NoTicketException();
        }

    }

    public void comentar(Evento evento, Usuario usuario, String comentario) throws IOException {
        evento.getComentarios().put(usuario.getId(), comentario);
        re.salvarEvento(path, evento);
    }

    /**
     * Realiza a compra do ingresso para um usuário
     * @param usuario que irá comprar o ingresso
     * @param evento evento o qual pertence o evento
     * @return Ingresso gerado
     */
    public Ingresso comprarIngresso(Usuario usuario, Evento evento) throws IOException {

        Ingresso ingresso = new Ingresso(evento, evento.getPreco());
        ri.salvarIngresso(path, ingresso, usuario.getId());
        usuario.getIngressos().add(ingresso.getId());
        ru.salvarUsuario(path, usuario);

        return ingresso;

    }


    /**
     * Realiza compra de ingresso
     * @param usuario usuário que irá comprar
     * @param nomeDoEvento evento que irá ter ingresso comprado
     * @param data dia atual, da compra
     * @param pagamento forma de pagamento
     * @return retorna recibo para enviar por email
     * @throws IOException erro em caso de não conseguir acessar as pastas de arquivo
     */
    public String realizarCompra(Usuario usuario, String nomeDoEvento, Calendar data, FormaDePagamento pagamento) throws IOException {
        return realizarCompra(usuario, nomeDoEvento, 1, data, pagamento);
    }

    /**
     * Realiza compra de ingresso
     * @param usuario usuário que irá comprar
     * @param nomeDoEvento evento que irá ter ingresso comprado
     * @param assento quantidade de ingressos para ser comprado
     * @param data dia atual, da compra
     * @param pagamento forma de pagamento
     * @return retorna recibo para enviar por email
     * @throws IOException erro em caso de não conseguir acessar as pastas de arquivo
     */
    public String realizarCompra(Usuario usuario, String nomeDoEvento, Integer assento, Calendar data, FormaDePagamento pagamento) throws IOException {
        List<Evento> todosEventos = re.carregarTodosEventos(path);

        for (Evento evento : todosEventos) {
            if (evento.getNome().equals(nomeDoEvento)) {
                return realizarCompra(usuario, evento, assento, data, pagamento);
            }
        }
        return null;
    }

    public String realizarCompra(Usuario usuario, Evento evento, Integer assento, Calendar data, FormaDePagamento pagamento) throws IOException {
        if(!evento.removerAssento(assento)) return null;

        List<String> ingressos = new ArrayList<>();
        Double preco = evento.getPreco();
        for (int i = 0; i < assento; i++) {
            Ingresso ingresso = new Ingresso(evento, preco);
            ri.salvarIngresso(path, ingresso, usuario.getId());
            ingressos.add(ingresso.getId());
        }

        Compra c = new Compra(usuario.getEmail(), data, usuario.getId(), ingressos, preco * assento, pagamento);
        rc.salvarCompra(path, c, usuario.getLogin());

        usuario.getIngressos().addAll(ingressos);
        usuario.getCompras().add(c.getId());
        ru.salvarUsuario(path, usuario);

        re.salvarEvento(path, evento);

        return c.gerarRecibo();
    }

    public List<Compra> listarCompras(Usuario usuarioLogado) throws IOException {
        return rc.carregarTodosCompra(path, usuarioLogado.getId());
    }

  public Evento carregarEvento(String id){
      try{
          return re.carregarEvento(path, id);
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
  }


}
