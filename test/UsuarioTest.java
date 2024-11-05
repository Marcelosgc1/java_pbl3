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

import com.vendaingressos.problema3_gui.models.Usuario;
import org.junit.jupiter.api.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


public class UsuarioTest {

    @Test
    public void testCadastrarUsuario() {
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        assertNotNull(usuario);
        assertEquals("johndoe", usuario.getLogin());
        assertEquals("John Doe", usuario.getNome());
        assertEquals("12345678901", usuario.getCpf());
        assertEquals("john.doe@example.com", usuario.getEmail());
        assertFalse(usuario.isAdmin());
    }

    @Test
    public void testCadastrarUsuarioAdmin() {
        Usuario admin = new Usuario("admin", "senha123", "Admin User", "00000000000", "admin@example.com", true);

        assertNotNull(admin);
        assertEquals("admin", admin.getLogin());
        assertEquals("Admin User", admin.getNome());
        assertEquals("00000000000", admin.getCpf());
        assertEquals("admin@example.com", admin.getEmail());
        assertTrue(admin.isAdmin());
    }

    @Test
    public void testLogin() {
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        assertTrue(usuario.login("johndoe", "senha123"));
        assertFalse(usuario.login("johndoe", "senhaErrada"));
    }

    @Test
    public void testAtualizarSenha() throws IOException {
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        usuario.setSenha("novaSenha123");

        assertTrue(usuario.login("johndoe", "novaSenha123"));
        assertFalse(usuario.login("johndoe", "senha123"));
    }

    @Test
    public void testDadosUsuario() {
        Usuario usuario = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);

        usuario.setNome("Jonathan Doe");
        usuario.setEmail("jon.doe@example.com");

        assertEquals("Jonathan Doe", usuario.getNome());
        assertEquals("jon.doe@example.com", usuario.getEmail());
    }

    @Test
    public void testUsuarioDuplicado() {
        Usuario usuario1 = new Usuario("johndoe", "senha123", "John Doe", "12345678901", "john.doe@example.com", false);
        Usuario usuario2 = new Usuario("johndoe", "senha456", "John Doe", "12345678901", "john.doe@example.com", false);

        assertEquals(usuario1, usuario2);
    }
}
