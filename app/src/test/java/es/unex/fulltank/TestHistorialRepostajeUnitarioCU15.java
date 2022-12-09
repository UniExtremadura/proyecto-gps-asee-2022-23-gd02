package es.unex.fulltank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.fulltank.bd.elembd.HistorialRepostaje;

public class TestHistorialRepostajeUnitarioCU15 {

    private HistorialRepostaje h;

    @Before
    public void createGasolineraBD() {
        h = new HistorialRepostaje("2019-01-01", 1.1, 1.1, 1, 1.98, 12);
    }

    @Test
    public void testGetAndSetFecha() {
        assertEquals("2019-01-01", h.getFecha());
        h.setFecha("2019-01-02");
        assertEquals("2019-01-02", h.getFecha());
    }

    @Test
    public void testGetAndSetLatitud() {
        assertEquals(1.1, h.getLatitud(), 0.0001);
        h.setLatitud(384.9234);
        assertEquals(384.9234, h.getLatitud(), 0.0001);
    }

    @Test
    public void testGetAndSetLongitud() {
        assertEquals(1.1, h.getLongitud(), 0.0001);
        h.setLongitud(784.9234);
        assertEquals(784.9234, h.getLongitud(), 0.0001);
    }

    @Test
    public void testGetAndSetUid() {
        assertEquals(1, h.getUid());
        h.setUid(2);
        assertEquals(2, h.getUid());
    }

    @Test
    public void testGetAndSetPrecio() {
        assertEquals(1.98, h.getPrecio(), 0.0001);
        h.setPrecio(2.98);
        assertEquals(2.98, h.getPrecio(), 0.0001);
    }


}
