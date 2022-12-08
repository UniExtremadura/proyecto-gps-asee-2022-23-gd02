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

import es.unex.fulltank.bd.elembd.Gasolinera;
import es.unex.fulltank.bd.elembd.GasolineraResenha;
import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.GasolineraDao;
import es.unex.fulltank.bd.roomdb.GasolineraResenhaDao;
import es.unex.fulltank.bd.roomdb.UsuarioDao;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestGasolineraResenhaDao {
    private BD volatileBD;
    private GasolineraResenhaDao gasolineraResenhaDao;
    private UsuarioDao usuarioDao;
    private GasolineraDao gasolineraDao;

    @Before
    public void crearVolatileBD() {
        Context context = ApplicationProvider.getApplicationContext();
        volatileBD = Room.inMemoryDatabaseBuilder(context, BD.class).allowMainThreadQueries().build();

        gasolineraResenhaDao = volatileBD.getGasolineraResenhaDao();
        usuarioDao = volatileBD.getUsuarioDao();
        gasolineraDao = volatileBD.getGasolineraDao();
    }

    @Test
    public void comprobarDao() {
        assertNotNull(gasolineraResenhaDao);
        assertNotNull(usuarioDao);
        assertNotNull(gasolineraDao);
    }

    @Test
    public void addGasolineraResenha() {
        gasolineraResenhaDao = volatileBD.getGasolineraResenhaDao();

        addGasolinera();
        addUsuario();

        GasolineraResenha gr = new GasolineraResenha("10/10/2010", 2.8, 1.0, usuarioDao.getByUsuario("unex").getUid(), "good");
        gasolineraResenhaDao.insert(gr);

        GasolineraResenha grInsertado = gasolineraResenhaDao.getByPrimaryKey(2.8, 1.0, "10/10/2010");

        assertNotNull(grInsertado);
        assertEquals(grInsertado.getFecha(), gr.getFecha());
        assertEquals(grInsertado.getLatitud(), gr.getLatitud(), 0.001);
        assertEquals(grInsertado.getLongitud(), gr.getLongitud(), 0.001);
        assertEquals(grInsertado.getUid(), gr.getUid());
        assertEquals(grInsertado.getComentario(), gr.getComentario());
    }


    public void addGasolinera() {
        gasolineraDao = volatileBD.getGasolineraDao();
        Gasolinera g = new Gasolinera(2.8, 1.0, 10003, "san blas", "10:00-13:00", "Cáceres", "Cáceres", "Cáceres", "CEPSA");
        gasolineraDao.insert(g);
    }

    public void addUsuario() {
        usuarioDao = volatileBD.getUsuarioDao();
        Usuario u = new Usuario("unex", "unex@alumnos.unex.es", "12345");
        usuarioDao.insert(u);
    }

    @After
    public void cerrarVolatileBD() {
        volatileBD.close();
    }
}
