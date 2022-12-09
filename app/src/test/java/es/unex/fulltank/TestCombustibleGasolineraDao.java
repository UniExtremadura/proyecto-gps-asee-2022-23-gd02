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

import es.unex.fulltank.bd.elembd.CombustibleGasolinera;
import es.unex.fulltank.bd.elembd.Gasolinera;
import es.unex.fulltank.bd.elembd.TipoCombustible;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.CombustibleGasolineraDao;
import es.unex.fulltank.bd.roomdb.GasolineraDao;
import es.unex.fulltank.bd.roomdb.TipoCombustibleDao;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestCombustibleGasolineraDao {

    private BD volatileBD;
    private CombustibleGasolineraDao dao_to_test;
    private TipoCombustibleDao tDao;
    private GasolineraDao gDao;

    private void initDatos() {
        TipoCombustible t0 = new TipoCombustible(12, "combustible1");
        TipoCombustible t1 = new TipoCombustible(13, "combustible2");
        TipoCombustible t2 = new TipoCombustible(15, "combustible3");
        tDao.insert(t0);
        tDao.insert(t1);
        tDao.insert(t2);

        Gasolinera g0 = new Gasolinera(1.1, 1.1, 20034, "C/ Calle prueba", "20:00 - 22:00",
                "localidad", "municipio", "provincia", "Cepsa");
        Gasolinera g1 = new Gasolinera(2.98, 3.2, 54212, "C/ Calle prueba", "20:00 - 22:00",
                "localidad", "municipio", "provincia", "Repsol");
        gDao.insert(g0);
        gDao.insert(g1);

        CombustibleGasolinera c0 = new CombustibleGasolinera(1.1, 1.1, 12, 1.78);
        CombustibleGasolinera c1 = new CombustibleGasolinera(1.1, 1.1, 13, 1.94);
        dao_to_test.insert(c0);
        dao_to_test.insert(c1);
    }

    @Before
    public void createVolatileDB() {
        Context context = ApplicationProvider.getApplicationContext();
        volatileBD = Room.inMemoryDatabaseBuilder(context, BD.class).allowMainThreadQueries().build();
        dao_to_test = volatileBD.getCombustibleGasolineraDao();
        tDao = volatileBD.getTipoCombustibleDao();
        gDao = volatileBD.getGasolineraDao();
        initDatos();
    }

    @Test
    public void testGetDao() {
        dao_to_test = volatileBD.getCombustibleGasolineraDao();
        assertNotNull(dao_to_test);
    }

    @Test
    public void testGetByCoords() {

        List<CombustibleGasolinera> lComb = dao_to_test.getByCoords(1.1, 1.1);
        assertEquals(2, lComb.size());

        assertEquals(12, lComb.get(0).getCid());
        assertEquals(13, lComb.get(1).getCid());
        assertEquals(1.78, lComb.get(0).getPrecio(), 0.0001);
        assertEquals(1.94, lComb.get(1).getPrecio(), 0.0001);
    }


    @After
    public void closeVolatileBD() {
        volatileBD.close();
    }
}