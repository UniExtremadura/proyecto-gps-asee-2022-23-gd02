package es.unex.fulltank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.UsuarioDao;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class TestUsuarioDao {
    private BD volatileBD;
    private UsuarioDao usuarioDao;
    @Mock
    UsuarioDao usuarioMock;


    @Test
    public void registroUsuario() {
        Usuario usuario = new Usuario("unex", "unex@alumnos.unex.es", "12345");
        usuario.setUid(28);

        when(usuarioMock.getByLogin("unex", "12345")).thenReturn(usuario);

        assertNotEquals(usuarioMock.getByLogin("unex", "12345"), null);

        assertEquals(usuarioMock.getByLogin("unex", "12345").getUsuario(), "unex");
        assertEquals(usuarioMock.getByLogin("unex", "12345").getUid(), 28);
        assertEquals(usuarioMock.getByLogin("unex", "12345").getContra(), "12345");
        assertEquals(usuarioMock.getByLogin("unex", "12345").getCorreo(), "unex@alumnos.unex.es");

    }


}