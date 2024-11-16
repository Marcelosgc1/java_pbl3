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

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import com.vendaingressos.problema3_gui.models.Compra;
import com.vendaingressos.problema3_gui.models.Evento;
import com.vendaingressos.problema3_gui.models.Ingresso;
import com.vendaingressos.problema3_gui.models.Usuario;
import com.vendaingressos.problema3_gui.repositories.RepositorioCompra;
import com.vendaingressos.problema3_gui.repositories.RepositorioEvento;
import com.vendaingressos.problema3_gui.repositories.RepositorioIngresso;
import com.vendaingressos.problema3_gui.repositories.RepositorioUsuario;
import org.junit.jupiter.api.Test;


import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class RepositorioTest {


    //PATH principal, caso queira que vá para outra pasta, altere aqui.
    private static String pathPrincipal = "";
    private static String pastaEventos;
    private static String pastaUsuario;
    private static String pastaCompra;
    private static String pastaIngresso;
    private static Calendar dataFuturo;
    private static Calendar dataAtual;
    private static Calendar dataPassado;


    @BeforeAll
    static void setUp() {

        dataFuturo = Calendar.getInstance();
        dataFuturo.set(2025, Calendar.OCTOBER, 5);

        dataAtual = Calendar.getInstance();
        dataAtual.set(2024, Calendar.OCTOBER, 5);

        dataPassado = Calendar.getInstance();
        dataPassado.set(2023, Calendar.OCTOBER, 5);

        pathPrincipal = pathPrincipal + "repositoryTests";

        //Criando pastas auxiliares
        pastaEventos = pathPrincipal + File.separator + "events";
        pastaUsuario = pathPrincipal+ File.separator  + "user";
        pastaCompra = pathPrincipal+ File.separator  + "purchases";
        pastaIngresso = pathPrincipal+ File.separator  + "ticket";


    }

    @Test
    public void testSalvarUsuario() throws IOException {
        RepositorioUsuario ru = new RepositorioUsuario();
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        Usuario admin = new Usuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        assertNotNull(usuario);
        assertNotNull(admin);


        ru.salvarUsuario(pastaUsuario, usuario);
        ru.salvarUsuario(pastaUsuario, admin);

        Usuario usuario2 = ru.carregarUsuario(pastaUsuario,"johndoe");
        Usuario admin2 = ru.carregarUsuario(pastaUsuario, "admin");
        assertEquals(usuario2, usuario);
        assertEquals(admin2, admin);

        Usuario usuario3 = ru.carregarUsuario(pastaUsuario, "johndoe");
        Usuario admin3 = ru.carregarUsuario(pastaCompra, "johndoe");
        assertEquals(usuario3, usuario);
        assertNull(admin3);
    }

    @Test
    public void testSalvarEvento() throws IOException {


        RepositorioEvento ru = new RepositorioEvento();
        Evento evento = new Evento("Show", "Rock maneiro uhul", dataFuturo, 100, 100.0);

        assertNotNull(evento);


        ru.salvarEvento(pastaEventos, evento);

        Evento evento1 = ru.carregarEvento(pastaEventos,evento.getId());
        ru.salvarEvento(pastaEventos, evento1);
        assertEquals(evento1, evento);

        Evento evento2 = ru.carregarEvento(pastaCompra, evento.getId());
        assertNull(evento2);
    }


    @Test
    public void testSalvarIngresso() throws IOException {

        RepositorioIngresso ru = new RepositorioIngresso();
        Evento evento = new Evento("Show", "Rock maneiro uhul", dataFuturo, 100, 100.0);
        Ingresso ingresso = new Ingresso(evento);

        assertNotNull(ingresso);

        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        ru.salvarIngresso(pastaIngresso, ingresso, usuario.getId());

        Ingresso ingresso1 = ru.carregarIngresso(pastaIngresso,ingresso.getId(), usuario.getId());
        ru.salvarIngresso(pastaIngresso, ingresso1, usuario.getId());
        assertEquals(ingresso1, ingresso);

        Ingresso ingresso2 = ru.carregarIngresso(pastaCompra, ingresso.getId(), usuario.getId());
        assertNull(ingresso2);


    }
    @Test
    public void testSalvarCompra() throws IOException {


        RepositorioCompra ru = new RepositorioCompra();
        Evento evento = new Evento("Show", "Rock maneiro uhul", dataFuturo, 100, 100.0);
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        Compra compra = new Compra("john.doe@example.com",dataAtual, "johndoe", List.of("ID_1", "ID_2"), 21.0);

        assertNotNull(compra);


        ru.salvarCompra(pastaCompra, compra, usuario.getId());
        Compra compra1 = ru.carregarCompra(pastaCompra,compra.getId(), usuario.getId());
        ru.salvarCompra(pastaCompra, compra1, usuario.getId());

        Compra compra2 = ru.carregarCompra(pastaEventos, compra.getId(), usuario.getId());
        assertNull(compra2);
    }

    @Test
    public void testSalvarUsuarioComCoisas() throws IOException {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 10);
        Date data = calendar.getTime();

        RepositorioUsuario ru = new RepositorioUsuario();

        Evento evento = new Evento("Show", "Rock maneiro uhul", dataFuturo, 100, 100.0);
        Ingresso ingresso = new Ingresso(evento);
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        List<String> c = new ArrayList<>();
        c.add(ingresso.getId());
        usuario.setIngressos(c);

        assertNotNull(usuario);

        ru.salvarUsuario(pastaUsuario, usuario);

        Usuario usuario2 = ru.carregarUsuario(pastaUsuario,"johndoe");

        assertEquals(usuario2, usuario);
        assertEquals(usuario2.getIngressos().getFirst(), usuario.getIngressos().getFirst());


    }


    @Test
    public void testSalvarUsuarios() throws IOException {
        RepositorioUsuario ru = new RepositorioUsuario();
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        Usuario admin = new Usuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        List<Usuario> x = new ArrayList<>();
        x.add(usuario);
        x.add(admin);

        ru.salvarUsuario(pastaUsuario, usuario);
        ru.salvarUsuario(pastaUsuario, admin);

        List<Usuario> usuarios = ru.carregarTodosUsuarios(pastaUsuario);
        assertTrue(usuarios.contains(usuario));
        assertTrue(usuarios.contains(admin));

    }


    @AfterEach
    public void deletarTestes() throws IOException {
        Path directory = Paths.get(pathPrincipal);

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
