package es.unex.fulltank;

import static org.junit.Assert.assertEquals;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import es.unex.fulltank.bd.elembd.Gasolinera;
import es.unex.fulltank.bd.elembd.GasolineraResenha;
import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.GasolineraDao;
import es.unex.fulltank.bd.roomdb.UsuarioDao;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestGasolineraResenha {
    private BD volatileBD;
    private UsuarioDao usuarioDao;
    private GasolineraDao gasolineraDao;
    private GasolineraResenha gr;

    @Before
    public void createResenhaBD() {
        Context context = ApplicationProvider.getApplicationContext();
        volatileBD = Room.inMemoryDatabaseBuilder(context, BD.class).allowMainThreadQueries().build();

        usuarioDao = volatileBD.getUsuarioDao();
        gasolineraDao = volatileBD.getGasolineraDao();

        Gasolinera gasolinera = new Gasolinera(1.1, 1.1, 6228, "C/Leon Leal", "12 am",
                "Hornachos", "Hornachos", "Badajoz", "CEPSA");
        gasolineraDao.insert(gasolinera);

        Usuario user = new Usuario("user", "correoUser", "12345");
        usuarioDao.insert(user);

        gr = new GasolineraResenha("28/11/2019", 1.1, 1.1, usuarioDao.getByUsuario("user").getUid(), "comentario 1");
    }

    @Test
    public void comprobarDao() {
        Assert.assertNotNull(usuarioDao);
        Assert.assertNotNull(gasolineraDao);
    }

    @Test
    public void testGetAndSetFecha() {
        assertEquals("28/11/2019", gr.getFecha());
        gr.setFecha("12/10/2020");
        assertEquals("12/10/2020", gr.getFecha());
    }

    @Test
    public void testGetAndSetLatitud() {
        assertEquals(1.1, gr.getLatitud(), 0.001);
        gr.setLatitud(2.2);
        assertEquals(2.2, gr.getLatitud(), 0.001);
    }

    @Test
    public void testGetAndSetLongitud() {
        assertEquals(1.1, gr.getLongitud(), 0.001);
        gr.setLongitud(2.2);
        assertEquals(2.2, gr.getLongitud(), 0.001);
    }

    @Test
    public void testGetUsuario() {
        assertEquals(usuarioDao.getByUsuario("user").getUid(), gr.getUid());
    }

    @Test
    public void testGetAndSetComentario() {
        assertEquals("comentario 1", gr.getComentario());
        gr.setComentario("comentario 2");
        assertEquals("comentario 2", gr.getComentario());
    }

    @After
    public void closeVolatileBD() {
        volatileBD.close();
    }


}