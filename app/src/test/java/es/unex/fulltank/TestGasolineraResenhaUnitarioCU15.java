package es.unex.fulltank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.fulltank.bd.elembd.GasolineraResenha;

public class TestGasolineraResenhaUnitarioCU15 {
    private GasolineraResenha g;

    @Before
    public void createGasolineraBD() {
        g = new GasolineraResenha("2020-04-05", 1.1, 1.1, 1, "Comentario");
    }

    @Test
    public void testGetAndSetFecha() {
        assertEquals("2020-04-05", g.getFecha());
        g.setFecha("2020-04-06");
        assertEquals("2020-04-06", g.getFecha());
    }

    @Test
    public void testGetAndSetLatitud() {
        assertEquals(1.1, g.getLatitud(), 0.0001);
        g.setLatitud(384.9234);
        assertEquals(384.9234, g.getLatitud(), 0.0001);
    }

    @Test
    public void testGetAndSetLongitud() {
        assertEquals(1.1, g.getLongitud(), 0.0001);
        g.setLongitud(784.9234);
        assertEquals(784.9234, g.getLongitud(), 0.0001);
    }

    @Test
    public void testGetAndSetUid() {
        assertEquals(1, g.getUid());
        g.setUid(2);
        assertEquals(2, g.getUid());
    }

    @Test
    public void testGetAndSetComentario() {
        assertEquals("Comentario", g.getComentario());
        g.setComentario("Comentario2");
        assertEquals("Comentario2", g.getComentario());
    }
}
