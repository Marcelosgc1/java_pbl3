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

package vendaingressos.models;

import vendaingressos.interfaces.ComId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario implements ComId {
    //atributos
    private String login;
    private String senha;
    private String cpf;
    private String nome;
    private String email;
    private Boolean admin;
    private List<String> ingressos;
    private List<String> compras;

    //construtor
    public Usuario(String login, String senha, String nome, String cpf, String email, Boolean admin) {
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.admin = admin;
        this.ingressos = new ArrayList<>();
        this.compras = new ArrayList<>();
    }


    //getters e setters
    public List<String> getCompras() {
        return compras;
    }

    public void setCompras(List<String> compras) {
        this.compras = compras;
    }

    public String getLogin() {
        return login;
    };

    public void setLogin(String login) {
        this.login = login;
    };

    public String getSenha() {
        return senha;
    };

    public void setSenha(String senha) {
        this.senha = senha;
    };

    public String getCpf() {
        return cpf;
    };

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    };

    public void setNome(String nome) {
        this.nome = nome;
    };

    public String getEmail() {
        return email;
    };

    public void setEmail(String email) {
        this.email = email;
    };

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<String> getIngressos() {
        return ingressos;
    }

    public void setIngressos(List<String> ingressos) {
        this.ingressos = ingressos;
    }

    public String getId() {
        return getLogin();
    }


    //outros metodos
    /**
     * @return Atributo do usuário para saber se é admin ou não
     */
    public Boolean isAdmin(){
        return getAdmin();
    }

    /**
     * Verifica se o login e a senha são compatíveis
     * @param login do usuário
     * @param senha do usuário
     * @return True para se os dados correspondem ou False caso contrário
     */
    public Boolean login(String login, String senha){
        return getLogin().equals(login) && getSenha().equals(senha);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(login, usuario.login) && Objects.equals(cpf, usuario.cpf) && Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(admin, usuario.admin) && Objects.equals(ingressos, usuario.ingressos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, cpf, nome, email, admin, ingressos);
    }


}
