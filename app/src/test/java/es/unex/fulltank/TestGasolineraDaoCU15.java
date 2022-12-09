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
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.GasolineraDao;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestGasolineraDaoCU15 {

    private BD volatileBD;
    private GasolineraDao dao_to_test;

    @Before
    public void createVolatileDB() {
        Context context = ApplicationProvider.getApplicationContext();
        volatileBD = Room.inMemoryDatabaseBuilder(context, BD.class).allowMainThreadQueries().build();
        dao_to_test = volatileBD.getGasolineraDao();
    }

    @Test
    public void testGetDao() {
        dao_to_test = volatileBD.getGasolineraDao();
        assertNotNull(dao_to_test);
    }

    @Test
    public void testGetAll() {
        Gasolinera g0 = new Gasolinera(0, 0, 20034, "C/ Calle prueba", "20:00 - 22:00",
                "localidad", "municipio", "provincia", "Cepsa");
        Gasolinera g1 = new Gasolinera(1, 1, 20534, "C/ Calle prueba", "20:00 - 22:00",
                "localidad", "municipio", "provincia", "Repsol");
        dao_to_test.insert(g0);
        dao_to_test.insert(g1);
        List<Gasolinera> result = dao_to_test.getAll();
        assertEquals(2, result.size());

        assertEquals(g0.getLatitud(), result.get(0).getLatitud(), 0.0001); //Precision loss
        assertEquals(g0.getLongitud(), result.get(0).getLongitud(), 0.0001); //Precision loss
        assertEquals(g0.getCp(), result.get(0).getCp(), 0.0001); //Precision loss
        assertEquals(g0.getDireccion(), result.get(0).getDireccion());
        assertEquals(g0.getHorario(), result.get(0).getHorario());
        assertEquals(g0.getLocalidad(), result.get(0).getLocalidad());
        assertEquals(g0.getMunicipio(), result.get(0).getMunicipio());
        assertEquals(g0.getProvincia(), result.get(0).getProvincia());
        assertEquals(g0.getRotulo(), result.get(0).getRotulo());


        assertEquals(g1.getLatitud(), result.get(1).getLatitud(), 0.0001); //Precision loss
        assertEquals(g1.getLongitud(), result.get(1).getLongitud(), 0.0001); //Precision loss
        assertEquals(g1.getCp(), result.get(1).getCp(), 0.0001); //Precision loss
        assertEquals(g1.getDireccion(), result.get(1).getDireccion());
        assertEquals(g1.getHorario(), result.get(1).getHorario());
        assertEquals(g1.getLocalidad(), result.get(1).getLocalidad());
        assertEquals(g1.getMunicipio(), result.get(1).getMunicipio());
        assertEquals(g1.getProvincia(), result.get(1).getProvincia());
        assertEquals(g1.getRotulo(), result.get(1).getRotulo());
    }

    @Test
    public void testInsert() {
        Gasolinera g0 = new Gasolinera(0, 0, 20034, "C/ Calle prueba", "20:00 - 22:00",
                "localidad", "municipio", "provincia", "Cepsa");
        Gasolinera g1 = new Gasolinera(1, 1, 20534, "C/ Calle prueba", "20:00 - 22:00",
                "localidad", "municipio", "provincia", "Repsol");
        Gasolinera g2 = new Gasolinera(1, 1, 12345, "C/ Calle prueba", "20:00 - 22:00",
                "localidad", "municipio", "provincia", "Repsol");
        dao_to_test.insert(g0);
        dao_to_test.insert(g1);
        dao_to_test.insert(g2);
        List<Gasolinera> result = dao_to_test.getAll();
        assertEquals(2, result.size());

        //G2 deberia haber reemplazado a g1
        assertEquals(g2.getCp(), result.get(1).getCp());
    }

    @After
    public void closeVolatileBD() {
        volatileBD.close();
    }
}