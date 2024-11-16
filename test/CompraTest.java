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

import com.vendaingressos.problema3_gui.Enum.FormaDePagamento;
import com.vendaingressos.problema3_gui.models.Compra;
import org.junit.jupiter.api.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompraTest {
    @Test
    public void testCriarCompra() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);

        Compra c = new Compra("john.doe@example.com", calendar, "johnDoe", List.of("id1", "id2"), 20.0, FormaDePagamento.PIX);
        assertEquals(c.getData(), calendar);
        assertEquals(20.0, c.getValorTotal(), 0.001);
        assertEquals("johnDoe", c.getUsuario());
        assertEquals("john.doe@example.com", c.getEmail());
        assertEquals(2, c.getIdsIngressos().size());
    }

    @Test
    public void testReciboVenda() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);

        Compra c = new Compra("john.doe@example.com",calendar, "johnDoe", List.of("id1", "id2"), 20.0, FormaDePagamento.PIX);

        assertTrue(c.gerarRecibo().contains("Agradecemos"));
    }
    @Test
    public void testReciboNaoVenda() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date dataAtual = calendar.getTime();

        Compra c = new Compra("john.doe@example.com",calendar, "johnDoe", List.of("id1", "id2"), -20.0);

        assertFalse(c.gerarRecibo().contains("Agradecemos"));
    }

    @Test
    public void testReciboReembolso() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);

        Compra c = new Compra("john.doe@example.com",calendar, "johnDoe", List.of("id1", "id2"), -20.0);

        assertTrue(c.gerarRecibo().contains("Cancelamento"));
    }
    @Test
    public void testReciboNaoReembolso() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);

        Compra c = new Compra("john.doe@example.com",calendar, "johnDoe", List.of("id1", "id2"), 20.0, FormaDePagamento.PIX);

        assertFalse(c.gerarRecibo().contains("Cancelamento"));
    }
}
