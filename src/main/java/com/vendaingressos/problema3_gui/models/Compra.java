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

import com.vendaingressos.problema3_gui.Enum.FormaDePagamento;
import com.vendaingressos.problema3_gui.interfaces.ComId;

import java.util.*;

public class Compra implements ComId {
    private final String email;
    private final Calendar data;
    private final List<String> idsIngressos;
    private final String usuario;
    private final Double valorTotal;
    private final FormaDePagamento pagamento;
    private final UUID id;



    public Compra(String email, Calendar data, String usuario, List<String> idsIngressos, Double valorTotal, FormaDePagamento pagamento) {
        this.email = email;
        this.data = data;
        this.usuario = usuario;
        this.pagamento = pagamento;
        this.valorTotal = valorTotal;
        this.idsIngressos = idsIngressos;
        this.id = UUID.randomUUID();
    }

    public Compra(String email, Calendar data, String usuario, List<String> idsIngressos, Double valorTotal) {
        this.email = email;
        this.data = data;
        this.usuario = usuario;
        this.pagamento = null;
        this.valorTotal = valorTotal;
        this.idsIngressos = idsIngressos;
        this.id = UUID.randomUUID();
    }

    public String getEmail() {
        return email;
    }

    public Calendar getData() {
        return data;
    }

    public List<String> getIdsIngressos() {
        return idsIngressos;
    }

    public String getUsuario() {
        return usuario;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    @Override
    public String getId() {
        return id.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compra compra = (Compra) o;
        return Objects.equals(idsIngressos, compra.idsIngressos) && Objects.equals(usuario, compra.usuario) && Objects.equals(valorTotal, compra.valorTotal) && Objects.equals(pagamento, compra.pagamento) && Objects.equals(id, compra.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idsIngressos, usuario, valorTotal, pagamento, id);
    }

    public String gerarRecibo() {
        if (valorTotal>=0){
            return  "Para: " + email +
                    "\nAgradecemos pela sua compra!" +
                    "\nData da compra: " + data +
                    "\nForam comprados " + idsIngressos.size() +
                    " ingressos pelo usuario: " + usuario +
                    "\nValor total: " + valorTotal +
                    "\nForma de pagamento: " + pagamento;
        }
        return  "Para: " + email +
                "\nCancelamento efetuado com sucesso!" +
                "\nData do cancelamento: " + data.toString() +
                "\nO ingresso " + idsIngressos.getFirst() +
                " foi cencelado pelo usuario: " + usuario +
                "\nValor devolvido: " + (valorTotal * -1);

    }
}
