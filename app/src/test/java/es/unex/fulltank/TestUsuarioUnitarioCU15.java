package es.unex.fulltank;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unex.fulltank.bd.elembd.Usuario;

public class TestUsuarioUnitarioCU15 {

    private Usuario u;

    @Before
    public void createGasolineraBD() {
        u = new Usuario("user", "correo", "pass");
    }

    @Test
    public void testGetAndSetNombre() {
        assertEquals("user", u.getUsuario());
        u.setUsuario("user2");
        assertEquals("user2", u.getUsuario());
    }

    @Test
    public void testGetAndSetCorreo() {
        assertEquals("correo", u.getCorreo());
        u.setCorreo("correo2");
        assertEquals("correo2", u.getCorreo());
    }

    @Test
    public void testGetAndSetPassword() {
        assertEquals("pass", u.getContra());
        u.setContra("pass2");
        assertEquals("pass2", u.getContra());
    }
}
