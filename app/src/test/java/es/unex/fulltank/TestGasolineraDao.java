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
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.GasolineraDao;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestGasolineraDao {


    private BD volatileBD;
    private GasolineraDao dao_to_test;

    @Before
    public void createVolatileDB() {
        Context context = ApplicationProvider.getApplicationContext();
        volatileBD = Room.inMemoryDatabaseBuilder(context, BD.class).allowMainThreadQueries().build();
        dao_to_test = volatileBD.getGasolineraDao();
        Gasolinera g0 = new Gasolinera(1.1, 1.1, 20034, "C/ Calle prueba", "20:00 - 22:00",
                "localidad", "municipio", "provincia", "Cepsa");
        dao_to_test.insert(g0);
    }

    @Test
    public void testGetDao() {
        dao_to_test = volatileBD.getGasolineraDao();
        assertNotNull(dao_to_test);
    }

    @Test
    public void testGetByCoords() {
        Gasolinera g = dao_to_test.getByCoords(1.1, 1.1);
        assertEquals(1.1, g.getLatitud(), 0.0001);
        assertEquals(1.1, g.getLongitud(), 0.0001);
        assertEquals(20034, g.getCp());
    }

    @After
    public void closeVolatileBD() {
        volatileBD.close();
    }
}