package es.unex.fulltank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.UsuarioDao;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestUsuarioDao {
    private BD volatileBD;
    private UsuarioDao usuarioDao;

    @Before
    public void crearVolatileBD() {
        Context context = ApplicationProvider.getApplicationContext();
        volatileBD = Room.inMemoryDatabaseBuilder(context, BD.class).allowMainThreadQueries().build();

        usuarioDao = volatileBD.getUsuarioDao();
    }

    @Test
    public void comprobarDao() {
        assertNotNull(usuarioDao);
    }

    @Test
    public void registroUsuario() {
        usuarioDao = volatileBD.getUsuarioDao();
        Usuario u = new Usuario("unex", "unex@alumnos.unex.es", "12345");
        usuarioDao.insert(u);

        Usuario usuarioInsertado = usuarioDao.getByLogin("unex", "12345");

        assertNotNull(usuarioInsertado);
        assertEquals(usuarioInsertado.getUsuario(), u.getUsuario());
        assertEquals(usuarioDao.getByUsuario("unex").getUid(), 1);
        assertEquals(usuarioInsertado.getContra(), u.getContra());
        assertEquals(usuarioInsertado.getCorreo(), u.getCorreo());
    }

    @After
    public void cerrarVolatileBD() {
        volatileBD.close();
    }
}