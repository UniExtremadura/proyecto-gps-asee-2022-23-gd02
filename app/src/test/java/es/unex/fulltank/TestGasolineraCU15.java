package es.unex.fulltank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.fulltank.bd.elembd.Gasolinera;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class TestGasolineraCU15 {

    private Gasolinera gBD;

    @Before
    public void createGasolineraBD() {
        gBD = new Gasolinera(1, 1, 20034, "C/ Calle prueba", "20:00 - 22:00",
                "LOCALIDAD", "municipio", "provincia", "ROTULO");
    }

    @Test
    public void testGetAndSetRotulo() {
        assertEquals("ROTULO", gBD.getRotulo());
        gBD.setRotulo("ROTULO_CAMBIADO");
        assertEquals("ROTULO_CAMBIADO", gBD.getRotulo());
    }

    @Test
    public void testGetAndSetDireccion() {
        assertEquals("C/ Calle prueba", gBD.getDireccion());
        gBD.setDireccion("C/ Calle prueba CAMBIADA");
        assertEquals("C/ Calle prueba CAMBIADA", gBD.getDireccion());
    }

    @Test
    public void testGetAndSetLocalidad() {
        assertEquals("LOCALIDAD", gBD.getLocalidad());
        gBD.setLocalidad("LOCALIDAD_CAMBIADA");
        assertEquals("LOCALIDAD_CAMBIADA", gBD.getLocalidad());
    }
}