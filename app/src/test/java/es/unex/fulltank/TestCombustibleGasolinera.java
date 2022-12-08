package es.unex.fulltank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.fulltank.bd.elembd.CombustibleGasolinera;

public class TestCombustibleGasolinera {
    private CombustibleGasolinera tipo;

    @Before
    public void createGasolineraBD() {
        tipo = new CombustibleGasolinera(1.1, 1.1, 1, 1.78);
    }

    @Test
    public void testGetAndSetCid() {
        assertEquals(1, tipo.getCid());
        tipo.setCid(2);
        assertEquals(2, tipo.getCid());
    }

    @Test
    public void testGetAndSetPrecio() {
        assertEquals(1.78, tipo.getPrecio(), 0.0001);
        tipo.setPrecio(531.3);
        assertEquals(531.3, tipo.getPrecio(), 0.0001);
    }
}
