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
public class TestFiltrarGasolinerasDao {
    private BD volatileBD;
    private GasolineraDao gasolineraDao;
    private CombustibleGasolineraDao combustibleGasolineraDao;
    private TipoCombustibleDao tipoCombustibleDao;

    //APERTURA DE LA CONEXION
    @Before
    public void createVolatileBD() {
        Context context = ApplicationProvider.getApplicationContext();
        volatileBD = Room.inMemoryDatabaseBuilder(context, BD.class).allowMainThreadQueries().build();
        gasolineraDao = volatileBD.getGasolineraDao();
        tipoCombustibleDao = volatileBD.getTipoCombustibleDao();
        combustibleGasolineraDao = volatileBD.getCombustibleGasolineraDao();

        addGasolinera();
    }

    //DATOS DE LOS QUE DISPONE LA BD
    public void addGasolinera() {
        Gasolinera g1 = new Gasolinera(1.1, 1.1, 6228, "C/Leon Leal", "12 am",
                "Hornachos", "Hornachos", "Badajoz", "CEPSA");
        gasolineraDao.insert(g1);

        Gasolinera g2 = new Gasolinera(2.2, 2.2, 6221, "C/Mendez", "10 am",
                "Puebla", "Puebla", "Badajoz", "CEPSA");
        gasolineraDao.insert(g2);
    }

    public void addTipoCombustible() {
        TipoCombustible t1 = new TipoCombustible(1, "Gasolina");
        tipoCombustibleDao.insert(t1);

        TipoCombustible t2 = new TipoCombustible(2, "Diesel");
        tipoCombustibleDao.insert(t2);
    }

    public void addCombustibleGasolinera() {
        CombustibleGasolinera cg1 = new CombustibleGasolinera(1.1, 1.1, 1, 1.89);
        combustibleGasolineraDao.insert(cg1);

        CombustibleGasolinera cg2 = new CombustibleGasolinera(1.1, 1.1, 2, 1.29);
        combustibleGasolineraDao.insert(cg2);

        CombustibleGasolinera cg3 = new CombustibleGasolinera(2.2, 2.2, 2, 1.98);
        combustibleGasolineraDao.insert(cg3);
    }

    @Test
    public void comprobarDao() {
        Assert.assertNotNull(gasolineraDao);
        Assert.assertNotNull(combustibleGasolineraDao);
    }


    @Test
    public void getAll() {
        addTipoCombustible();
        addCombustibleGasolinera();

        List<CombustibleGasolinera> lcombustibleGasolineras = combustibleGasolineraDao.getAll();
        assertEquals(lcombustibleGasolineras.size(), 3);

        assertEquals(lcombustibleGasolineras.get(0).getLatitud(), 1.1, 0.001);
        assertEquals(lcombustibleGasolineras.get(0).getLongitud(), 1.1, 0.001);
        assertEquals(lcombustibleGasolineras.get(0).getCid(), 1);
        assertEquals(lcombustibleGasolineras.get(0).getPrecio(), 1.89, 0.001);

        assertEquals(lcombustibleGasolineras.get(1).getLatitud(), 1.1, 0.001);
        assertEquals(lcombustibleGasolineras.get(1).getLongitud(), 1.1, 0.001);
        assertEquals(lcombustibleGasolineras.get(1).getCid(), 2);
        assertEquals(lcombustibleGasolineras.get(1).getPrecio(), 1.29, 0.001);

        assertEquals(lcombustibleGasolineras.get(2).getLatitud(), 2.2, 0.001);
        assertEquals(lcombustibleGasolineras.get(2).getLongitud(), 2.2, 0.001);
        assertEquals(lcombustibleGasolineras.get(2).getCid(), 2);
        assertEquals(lcombustibleGasolineras.get(2).getPrecio(), 1.98, 0.001);
    }

    @Test
    public void getByCoords() {

        Gasolinera g1 = gasolineraDao.getByCoords(1.1, 1.1);
        assertEquals(g1.getLatitud(), 1.1, 0.001);
        assertEquals(g1.getLongitud(), 1.1, 0.001);
        assertEquals(g1.getCp(), 6228);
        assertEquals(g1.getDireccion(), "C/Leon Leal");
        assertEquals(g1.getMunicipio(), "Hornachos");
        assertEquals(g1.getHorario(), "12 am");
        assertEquals(g1.getProvincia(), "Badajoz");
        assertEquals(g1.getLocalidad(), "Hornachos");
        assertEquals(g1.getRotulo(), "CEPSA");

        Gasolinera g2 = gasolineraDao.getByCoords(2.2, 2.2);
        assertEquals(g2.getLatitud(), 2.2, 0.001);
        assertEquals(g2.getLongitud(), 2.2, 0.001);
        assertEquals(g2.getCp(), 6221);
        assertEquals(g2.getDireccion(), "C/Mendez");
        assertEquals(g2.getMunicipio(), "Puebla");
        assertEquals(g2.getHorario(), "10 am");
        assertEquals(g2.getProvincia(), "Badajoz");
        assertEquals(g2.getLocalidad(), "Puebla");
        assertEquals(g2.getRotulo(), "CEPSA");
    }

    //CIERRE DE LA CONEXION
    @After
    public void closeVolatileBD() {
        volatileBD.close();
    }
}