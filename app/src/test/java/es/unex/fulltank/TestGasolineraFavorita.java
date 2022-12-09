package es.unex.fulltank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.fulltank.bd.elembd.GasolineraFavorita;

public class TestGasolineraFavorita {

    private GasolineraFavorita gF;

    @Before
    public void createGasolineraBD() {
        gF = new GasolineraFavorita(1.1, 1.1, 1);
    }

    @Test
    public void testGetAndSetLatitud() {
        assertEquals(1.1, gF.getLatitud(), 0.0001);
        gF.setLatitud(384.9234);
        assertEquals(384.9234, gF.getLatitud(), 0.0001);
    }

    @Test
    public void testGetAndSetLongitud() {
        assertEquals(1.1, gF.getLongitud(), 0.0001);
        gF.setLongitud(784.9234);
        assertEquals(784.9234, gF.getLongitud(), 0.0001);
    }
}
