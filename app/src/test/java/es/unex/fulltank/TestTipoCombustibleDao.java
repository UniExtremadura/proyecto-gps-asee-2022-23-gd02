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

import es.unex.fulltank.bd.elembd.TipoCombustible;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.TipoCombustibleDao;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestTipoCombustibleDao {

    private BD volatileBD;
    private TipoCombustibleDao dao_to_test;

    @Before
    public void createVolatileDB() {
        Context context = ApplicationProvider.getApplicationContext();
        volatileBD = Room.inMemoryDatabaseBuilder(context, BD.class).allowMainThreadQueries().build();
        dao_to_test = volatileBD.getTipoCombustibleDao();
    }

    @Test
    public void testGetDao() {
        dao_to_test = volatileBD.getTipoCombustibleDao();
        assertNotNull(dao_to_test);
    }

    @Test
    public void testGetAll() {
        TipoCombustible t1 = new TipoCombustible(12, "combustible1");
        TipoCombustible t2 = new TipoCombustible(13, "combustible2");
        dao_to_test.insert(t1);
        dao_to_test.insert(t2);

        List<TipoCombustible> lTipoComb = dao_to_test.getAll();
        assertEquals(2, lTipoComb.size());

        assertEquals(12, lTipoComb.get(0).getCid());
        assertEquals(13, lTipoComb.get(1).getCid());
        assertEquals("combustible1", lTipoComb.get(0).getNombre());
        assertEquals("combustible2", lTipoComb.get(1).getNombre());
    }

    @Test
    public void testInsert() {
        TipoCombustible t1 = new TipoCombustible(12, "combustible1");
        TipoCombustible t2 = new TipoCombustible(13, "combustible2");
        TipoCombustible t3 = new TipoCombustible(13, "combustible3"); //No deberia insertarse
        dao_to_test.insert(t1);
        dao_to_test.insert(t2);
        dao_to_test.insert(t3);

        List<TipoCombustible> lTipoComb = dao_to_test.getAll();
        assertEquals(2, lTipoComb.size());

        assertEquals(12, lTipoComb.get(0).getCid());
        assertEquals(13, lTipoComb.get(1).getCid());
        assertEquals("combustible1", lTipoComb.get(0).getNombre());
        assertEquals("combustible2", lTipoComb.get(1).getNombre());
    }


    @After
    public void closeVolatileBD() {
        volatileBD.close();
    }
}