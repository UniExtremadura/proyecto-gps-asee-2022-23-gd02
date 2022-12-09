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

import java.util.List;

import es.unex.fulltank.bd.elembd.Gasolinera;
import es.unex.fulltank.bd.elembd.GasolineraFavorita;
import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.GasolineraDao;
import es.unex.fulltank.bd.roomdb.GasolineraFavoritaDao;
import es.unex.fulltank.bd.roomdb.UsuarioDao;

@RunWith(AndroidJUnit4.class)
public class TestGasolinerasFavoritaDao {

    private BD volatileBD;
    private GasolineraFavoritaDao dao_to_test;
    private UsuarioDao uDao;
    private GasolineraDao gDao;

    private void initDatos() {
        Usuario u = new Usuario("userTest", "correoTest", "12345678");
        uDao.insert(u);

        Gasolinera g0 = new Gasolinera(1.1, 1.1, 20034, "C/ Calle prueba", "20:00 - 22:00",
                "localidad", "municipio", "provincia", "Cepsa");
        Gasolinera g1 = new Gasolinera(2.2, 2.2, 12345, "C/ Calle prueba", "20:00 - 22:00",
                "localidad", "municipio", "provincia", "Repsol");
        gDao.insert(g0);
        gDao.insert(g1);

        GasolineraFavorita gF1 = new GasolineraFavorita(1.1, 1.1, 1);
        GasolineraFavorita gF2 = new GasolineraFavorita(2.2, 2.2, 1);
        dao_to_test.insert(gF1);
        dao_to_test.insert(gF2);
    }

    @Before
    public void createVolatileBD() {
        Context context = ApplicationProvider.getApplicationContext();
        volatileBD = Room.inMemoryDatabaseBuilder(context, BD.class).allowMainThreadQueries().build();
        dao_to_test = volatileBD.getGasolineraFavoritaDao();
        uDao = volatileBD.getUsuarioDao();
        gDao = volatileBD.getGasolineraDao();
        initDatos();
    }

    @Test
    public void testGetDao() {
        dao_to_test = volatileBD.getGasolineraFavoritaDao();
        assertNotNull(dao_to_test);
        gDao = volatileBD.getGasolineraDao();
        assertNotNull(gDao);
    }

    @Test
    public void testGetByUid() {
        List<GasolineraFavorita> lGf = dao_to_test.getByUid(1);
        assertEquals(2, lGf.size());

        assertEquals(1.1, lGf.get(0).getLatitud(), 0.0001);
        assertEquals(1.1, lGf.get(0).getLongitud(), 0.0001);
        assertEquals(1, lGf.get(0).getUid());

        assertEquals(2.2, lGf.get(1).getLatitud(), 0.0001);
        assertEquals(2.2, lGf.get(1).getLongitud(), 0.0001);
        assertEquals(1, lGf.get(1).getUid());
    }

    @After
    public void closeVolatileBD() {
        volatileBD.close();
    }
}
