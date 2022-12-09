package es.unex.fulltank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.fulltank.bd.elembd.TipoCombustible;

public class TestTipoCombustible {

    private TipoCombustible tipo;

    @Before
    public void createGasolineraBD() {
        tipo = new TipoCombustible(1, "combustible1");
    }

    @Test
    public void testGetAndSetCid() {
        assertEquals(1, tipo.getCid());
        tipo.setCid(2);
        assertEquals(2, tipo.getCid());
    }

    @Test
    public void testGetAndSetNombre() {
        assertEquals("combustible1", tipo.getNombre());
        tipo.setNombre("combustibleCAMBIADO");
        assertEquals("combustibleCAMBIADO", tipo.getNombre());
    }
}
