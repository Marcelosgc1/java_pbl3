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

import java.util.Date;
import java.util.Calendar;
import com.vendaingressos.problema3_gui.models.Evento;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EventoTest {

    @Test
    public void testCriarEvento() {
        Calendar dataFuturo = Calendar.getInstance();
        dataFuturo.set(2025, Calendar.SEPTEMBER, 10);


        Evento evento = new Evento("Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);

        assertNotNull(evento);
        assertEquals("Show de Rock", evento.getNome());
        assertEquals("Banda XYZ", evento.getDescricao());
        assertEquals(dataFuturo, evento.getData());
    }

    @Test
    public void testAdicionarAssento() {
        Calendar dataFuturo = Calendar.getInstance();
        dataFuturo.set(2025, Calendar.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);
        evento.adicionarAssento(20);


        assertEquals(120, (int) evento.getAssentosDisponiveis());
    }

    @Test
    public void testRemoverAssento() {
        Calendar dataFuturo = Calendar.getInstance();
        dataFuturo.set(2025, Calendar.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);
        evento.adicionarAssento(20);
        evento.removerAssento(20);

        assertEquals(100, (int) evento.getAssentosDisponiveis());
    }

    @Test
    public void testAdicionarAssentoNegativo() {
        Calendar dataFuturo = Calendar.getInstance();
        dataFuturo.set(2025, Calendar.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);
        evento.adicionarAssento(-20);

        assertEquals(100, (int) evento.getAssentosDisponiveis());
    }

    @Test
    public void testRemoverAssentoImpossivel() {
        Calendar dataFuturo = Calendar.getInstance();
        dataFuturo.set(2025, Calendar.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);

        evento.removerAssento(-20);
        assertEquals(100, (int) evento.getAssentosDisponiveis());

        evento.removerAssento(200);
        assertEquals(100, (int) evento.getAssentosDisponiveis());
    }

    @Test
    public void testEventoAtivo() {
        Calendar dataFuturo = Calendar.getInstance();
        dataFuturo.set(2025, Calendar.SEPTEMBER, 10);

        Calendar dataAtual = Calendar.getInstance();
        dataAtual.set(2024, Calendar.SEPTEMBER, 10);


        Evento evento = new Evento("Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);

        assertTrue(evento.isAtivo(dataAtual));
    }

    @Test
    public void testEventoInativo() {
        Calendar dataPassado = Calendar.getInstance();
        dataPassado.set(2023, Calendar.SEPTEMBER, 10);

        Calendar dataAtual = Calendar.getInstance();
        dataAtual.set(2024, Calendar.SEPTEMBER, 10);

        Evento evento = new Evento("Show de Rock", "Banda XYZ", dataPassado, 100, 100.0);

        assertFalse(evento.isAtivo(dataAtual));
    }
}
