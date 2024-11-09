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

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import com.vendaingressos.problema3_gui.controllers.Controller;
import com.vendaingressos.problema3_gui.Enum.FormaDePagamento;
import com.vendaingressos.problema3_gui.models.Evento;
import com.vendaingressos.problema3_gui.models.Ingresso;
import com.vendaingressos.problema3_gui.models.Usuario;


public class ControllerTest {

    @Test
    public void testCadastrarEventoPorAdmin() throws Exception {
        Controller controller = new Controller();
        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date dataFuturo = calendar.getTime();

        Evento evento = controller.cadastrarEvento(admin,"Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);

        assertNotNull(evento);
        assertEquals("Show de Rock", evento.getNome());
        assertEquals("Banda XYZ", evento.getDescricao());
        assertEquals(dataFuturo, evento.getData());
    }

    @Test
    public void testCadastrarEventoPorUsuarioComum() throws Exception {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date dataFuturo = calendar.getTime();

        Exception exception = assertThrows(SecurityException.class, () -> {
            controller.cadastrarEvento(usuario, "Peça de Teatro", "Grupo ABC", dataFuturo, 100, 100.0);
        });

        assertEquals("Somente administradores podem cadastrar eventos.", exception.getMessage());
    }

    @Test
    public void testComprarIngresso() throws Exception {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date dataFuturo = calendar.getTime();

        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date dataAtual = calendar.getTime();

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        Evento evento = controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);
        controller.adicionarAssentoEvento("Show de Rock", 1);

        Ingresso ingresso = controller.comprarIngresso(usuario, evento);
        Evento eventoCarregado = controller.re.carregarEvento("repository", ingresso.getEvento());

        assertNotNull(ingresso);
        assertEquals("Show de Rock", eventoCarregado.getNome());
        assertTrue(usuario.getIngressos().contains(ingresso.getId()));
    }

    @Test
    public void testCancelarCompra() throws IOException {
        Controller controller = new Controller();
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date dataFuturo = calendar.getTime();

        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date dataAtual = calendar.getTime();

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        Evento evento = controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);
        Ingresso ingresso = controller.comprarIngresso(usuario, evento);


        String cancelado = controller.cancelarCompra(usuario, ingresso, dataAtual);
        System.out.println(cancelado);
        assertNotNull(cancelado);
        assertFalse(ingresso.isAtivo());
        assertTrue(cancelado.contains("Cancelamento"));
    }

    @Test
    public void testListarEventosDisponiveis() throws IOException {
        Controller controller = new Controller();
        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2024, Calendar.SEPTEMBER, 10);
        Date data1 = calendar1.getTime();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2024, Calendar.SEPTEMBER, 15);
        Date data2 = calendar2.getTime();

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(2024, Calendar.SEPTEMBER, 1);
        Date data3 = calendar3.getTime();

        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", data1, 1000, 1.0);
        controller.cadastrarEvento(admin, "Peça de Teatro", "Grupo ABC", data2, 2, 2.0);

        List<Evento> eventos = controller.listarEventosDisponiveis(data3);

        assertEquals(2, eventos.size());
    }

    @Test
    public void testListarIngressosComprados() throws IOException {
        Controller controller = new Controller();
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date dataFuturo = calendar.getTime();

        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date dataPresente = calendar.getTime();


        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        Evento evento = controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);
        controller.comprarIngresso(usuario, evento);

        List<Ingresso> ingressos = controller.listarIngressosComprados(usuario);

        assertEquals(1, ingressos.size());
    }

    @Test
    public void testRealizarCompra() throws IOException {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date dataFuturo = calendar.getTime();

        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date dataAtual = calendar.getTime();

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);
        controller.adicionarAssentoEvento("Show de Rock", 1);

        String recibo = controller.realizarCompra(usuario, "Show de Rock", dataAtual, FormaDePagamento.DEBITO);

        assertNotNull(recibo);
        System.out.println(recibo);
        assertTrue(recibo.contains("Agradecemos"));
    }

    @Test
    public void testRealizarCompraVariosIngressos() throws IOException {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date dataFuturo = calendar.getTime();

        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date dataAtual = calendar.getTime();

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);

        String recibo = controller.realizarCompra(usuario, "Show de Rock", 5, dataAtual, FormaDePagamento.CREDITO);

        assertNotNull(recibo);
        System.out.println(recibo);
        assertTrue(recibo.contains("Agradecemos"));
    }


    @Test
    public void testAlterarNome() throws IOException {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        controller.alterarNome(usuario, "Doe John");

        assertEquals(usuario.getNome(), "Doe John");
    }

    @Test
    public void testAlterarEmail() throws IOException {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        controller.alterarEmail(usuario, "doe.john@example.com");

        assertEquals(usuario.getEmail(), "doe.john@example.com");
    }

    @Test
    public void testAlterarCpf() throws IOException {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        controller.alterarCpf(usuario, "00000000");

        assertEquals(usuario.getCpf(), "00000000");
    }

    @Test
    public void testAlterarSenha() throws IOException {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        controller.alterarSenha(usuario, "novaSenha123");

        assertEquals(usuario.getSenha(), "novaSenha123");
        Boolean login = usuario.login("johndoe", "novaSenha123");
        assertTrue(login);
    }

    @Test
    public void testRealizarComentario() throws IOException {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.SEPTEMBER, 10);
        Date dataPassado = calendar.getTime();

        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date dataAtual = calendar.getTime();

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", dataPassado, 100, 100.0);
        controller.realizarCompra(usuario, "Show de Rock", dataAtual, FormaDePagamento.BOLETO);

        Boolean realizado = controller.realizarComentario(usuario, "Show de Rock", "Foi ótimo!", dataAtual);
        assertTrue(realizado);
    }

    @Test
    public void testRealizarComentarioAntesDaData() throws IOException {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.SEPTEMBER, 10);
        Date dataFuturo = calendar.getTime();

        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date dataAtual = calendar.getTime();

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", dataFuturo, 100, 100.0);
        controller.realizarCompra(usuario, "Show de Rock", dataAtual, FormaDePagamento.BOLETO);

        Boolean realizado = controller.realizarComentario(usuario, "Show de Rock", "Ainda não fui mas vai ser muito bom!!", dataAtual);
        assertFalse(realizado);
    }

    @Test
    public void testRealizarComentarioSemIngresso() throws IOException {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.SEPTEMBER, 10);
        Date dataPassado = calendar.getTime();

        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date dataAtual = calendar.getTime();

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", dataPassado, 100, 100.0);

        Boolean realizado = controller.realizarComentario(usuario, "Show de Rock", "maneiro", dataAtual);
        assertFalse(realizado);

    }

    @Test
    public void testRealizarComentarioIngressoOutroEvento() throws IOException {
        Controller controller = new Controller();
        Usuario usuario = controller.cadastrarUsuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2023, Calendar.SEPTEMBER, 10);
        Date dataPassado = calendar.getTime();

        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date dataAtual = calendar.getTime();

        Usuario admin = controller.cadastrarUsuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);
        controller.cadastrarEvento(admin, "Show de Rock", "Banda XYZ", dataPassado, 100, 100.0);
        controller.cadastrarEvento(admin, "Rock in Rio", "My Bloody Valentine", dataPassado, 20, 50.0);
        controller.realizarCompra(usuario, "Rock in Rio", dataAtual, FormaDePagamento.PIX);

        Boolean realizado = controller.realizarComentario(usuario, "Show de Rock", "maneiro", dataAtual);
        assertFalse(realizado);
        Boolean realizado2 = controller.realizarComentario(usuario, "Rock in Rio", "amo 'when you sleep'!", dataAtual);
        assertTrue(realizado2);
    }

    @AfterEach
    public void deletarArquivos() throws IOException {
        Path directory = Paths.get("repository");
        if (Files.exists(directory)) {
            Files.walkFileTree(directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }
                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        }

    }

}
