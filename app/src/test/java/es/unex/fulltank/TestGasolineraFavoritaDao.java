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
import es.unex.fulltank.bd.elembd.GasolineraFavorita;
import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.GasolineraDao;
import es.unex.fulltank.bd.roomdb.GasolineraFavoritaDao;
import es.unex.fulltank.bd.roomdb.UsuarioDao;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestGasolineraFavoritaDao {
    private BD volatileBD;
    private GasolineraFavoritaDao gasolineraFavoritaDao;
    private UsuarioDao usuarioDao;
    private GasolineraDao gasolineraDao;

    @Before
    public void crearVolatileBD() {
        Context context = ApplicationProvider.getApplicationContext();
        volatileBD = Room.inMemoryDatabaseBuilder(context, BD.class).allowMainThreadQueries().build();

        gasolineraFavoritaDao = volatileBD.getGasolineraFavoritaDao();
        usuarioDao = volatileBD.getUsuarioDao();
        gasolineraDao = volatileBD.getGasolineraDao();
    }

    //PRUEBA DE OBTENCIÓN DE DAO
    @Test
    public void comprobarDao() {
        assertNotNull(gasolineraFavoritaDao);
        assertNotNull(usuarioDao);
        assertNotNull(gasolineraDao);
    }

    //PRUEBA PARA INSERTAR EN LA BASE DE DATO UNA NUEVA GASOLINERA FAVORITA
    @Test
    public void addGasolineraFavorita() {
        addGasolinera();
        addUsuario();

        GasolineraFavorita gf = new GasolineraFavorita(2.8, 1.0, usuarioDao.getByUsuario("unex").getUid());
        gasolineraFavoritaDao.insert(gf);

        GasolineraFavorita gfInsertada = gasolineraFavoritaDao.getByPrimaryKey(2.8, 1.0, usuarioDao.getByUsuario("unex").getUid());

        assertNotNull(gfInsertada);
        assertEquals(gfInsertada.getLatitud(), gf.getLatitud(), 0.001);
        assertEquals(gfInsertada.getLongitud(), gf.getLongitud(), 0.001);
        assertEquals(gfInsertada.getUid(), gf.getUid());
    }

    //DATOS QUE YA DISPONDRÍA LA BD
    public void addGasolinera() {
        Gasolinera g = new Gasolinera(2.8, 1.0, 10003, "san blas", "10:00-13:00", "Cáceres", "Cáceres", "Cáceres", "CEPSA");
        gasolineraDao.insert(g);
    }

    //DATOS QUE YA DISPONDRÍA LA BD
    public void addUsuario() {
        Usuario u = new Usuario("unex", "unex@alumnos.unex.es", "12345");
        usuarioDao.insert(u);
    }

    @After
    public void cerrarVolatileBD() {
        volatileBD.close();
    }
}