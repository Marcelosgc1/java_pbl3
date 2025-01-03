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

import org.junit.jupiter.api.*;

import java.util.Calendar;
import java.util.Date;
import com.vendaingressos.problema3_gui.models.Evento;
import com.vendaingressos.problema3_gui.models.Ingresso;

import static org.junit.jupiter.api.Assertions.*;

public class IngressoTest {

    @Test
    public void testCriarIngresso() {
        Calendar dataFuturo = Calendar.getInstance();
        dataFuturo.set(2025, Calendar.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);
        Ingresso ingresso = new Ingresso(evento);

        assertNotNull(ingresso);
        assertEquals(evento.getId(), ingresso.getEvento());
        assertEquals(100.0, ingresso.getPreco(), 0.0001);
        assertTrue(ingresso.isAtivo());
    }

    @Test
    public void testCancelarIngresso() {
        Calendar dataFuturo = Calendar.getInstance();
        dataFuturo.set(2025, Calendar.SEPTEMBER, 10);
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.set(2024, Calendar.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);
        Ingresso ingresso = new Ingresso(evento);

        assertTrue(ingresso.cancelar(dataAtual, evento.getData()));
        assertFalse(ingresso.isAtivo());
    }

    @Test
    public void testCancelarIngressoEventoPassado() {
        Calendar dataPassado = Calendar.getInstance();
        dataPassado.set(2023, Calendar.SEPTEMBER, 10);
        Calendar dataAtual = Calendar.getInstance();
        dataAtual.set(2024, Calendar.SEPTEMBER, 10);


        Evento evento = new Evento("Show de Rock", "Banda XYZ", dataPassado, 100, 100.0);
        Ingresso ingresso = new Ingresso(evento);


        assertFalse(ingresso.cancelar(dataAtual, evento.getData()));
        assertTrue(ingresso.isAtivo());
    }

    @Test
    public void testIngressosDiferentes() {
        Calendar dataFuturo = Calendar.getInstance();
        dataFuturo.set(2025, Calendar.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);
        Ingresso ingresso1 = new Ingresso(evento);
        Ingresso ingresso2 = new Ingresso(evento);

        assertNotEquals(ingresso1, ingresso2);
        assertNotEquals(ingresso1.hashCode(), ingresso2.hashCode());
    }
}
