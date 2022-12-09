package es.unex.fulltank;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

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

    //APERTURA DE LA CONEXION
    @Before
    public void createVolatileBD() {
        Context context = ApplicationProvider.getApplicationContext();
        volatileBD = Room.inMemoryDatabaseBuilder(context, BD.class).allowMainThreadQueries().build();
        gasolineraResenhaDao = volatileBD.getGasolineraResenhaDao();
        usuarioDao = volatileBD.getUsuarioDao();
        gasolineraDao = volatileBD.getGasolineraDao();

    }

    //DATOS DE LOS QUE DISPONE LA BD
    public void addUsuario() {
        Usuario user = new Usuario("user", "correoUser", "12345");
        usuarioDao.insert(user);
        Usuario u = new Usuario("unex", "unex@alumnos.unex.es", "12345");
        usuarioDao.insert(u);
    }

    public void addGasolinera() {
        Gasolinera gasolinera = new Gasolinera(1.1, 1.1, 6228, "C/Leon Leal", "12 am",
                "Hornachos", "Hornachos", "Badajoz", "CEPSA");
        gasolineraDao.insert(gasolinera);
        Gasolinera g = new Gasolinera(2.8, 1.0, 10003, "san blas", "10:00-13:00", "Cáceres", "Cáceres", "Cáceres", "CEPSA");
        gasolineraDao.insert(g);
    }

    public void addGasolinerasResenhas() {
        GasolineraResenha g1 = new GasolineraResenha("12/10/2019", 1.1, 1.1, usuarioDao.getByUsuario("user").getUid(), "comentario 1");
        gasolineraResenhaDao.insert(g1);

        GasolineraResenha g2 = new GasolineraResenha("28/11/2019", 1.1, 1.1, usuarioDao.getByUsuario("user").getUid(), "comentario 2");
        gasolineraResenhaDao.insert(g2);
    }

    @Test
    public void comprobarDao() {
        Assert.assertNotNull(gasolineraResenhaDao);
        Assert.assertNotNull(usuarioDao);
        Assert.assertNotNull(gasolineraDao);
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


    @Test
    public void getByCoords() {
        addUsuario();
        addGasolinera();
        addGasolinerasResenhas();

        List<GasolineraResenha> lGasolineraResenha = gasolineraResenhaDao.getByCoords(1.1, 1.1);
        assertEquals(lGasolineraResenha.size(), 2);
        assertEquals(lGasolineraResenha.get(0).getFecha(), "12/10/2019");
        assertEquals(lGasolineraResenha.get(0).getLatitud(), 1.1, 0.001);
        assertEquals(lGasolineraResenha.get(0).getLongitud(), 1.1, 0.001);
        assertEquals(lGasolineraResenha.get(0).getUid(), usuarioDao.getByUsuario("user").getUid());
        assertEquals(lGasolineraResenha.get(0).getComentario(), "comentario 1");

        assertEquals(lGasolineraResenha.get(1).getFecha(), "28/11/2019");
        assertEquals(lGasolineraResenha.get(1).getLatitud(), 1.1, 0.001);
        assertEquals(lGasolineraResenha.get(1).getLongitud(), 1.1, 0.001);
        assertEquals(lGasolineraResenha.get(1).getUid(), usuarioDao.getByUsuario("user").getUid());
        assertEquals(lGasolineraResenha.get(1).getComentario(), "comentario 2");
    }

    //CIERRE DE LA CONEXION
    @After
    public void closeVolatileBD() {
        volatileBD.close();
    }
}