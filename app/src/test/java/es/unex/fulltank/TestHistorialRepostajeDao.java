package es.unex.fulltank;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import es.unex.fulltank.bd.elembd.Gasolinera;
import es.unex.fulltank.bd.elembd.HistorialRepostaje;
import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.GasolineraDao;
import es.unex.fulltank.bd.roomdb.HistorialRepostajeDao;
import es.unex.fulltank.bd.roomdb.UsuarioDao;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestHistorialRepostajeDao {
    private BD volatileBD;
    private HistorialRepostajeDao historialRepostajeDao;
    private UsuarioDao usuarioDao;
    private GasolineraDao gasolineraDao;

    @Before
    public void crearVolatileBD(){
        Context context = ApplicationProvider.getApplicationContext();
        volatileBD = Room.inMemoryDatabaseBuilder(context,BD.class).allowMainThreadQueries().build();

        historialRepostajeDao = volatileBD.getHistorialRepostajeDao();
        usuarioDao = volatileBD.getUsuarioDao();
        gasolineraDao = volatileBD.getGasolineraDao();
    }

    //PRUEBA DE OBTENCIÓN DE DAO
    @Test
    public void comprobarDao(){
        assertNotNull(historialRepostajeDao);
        assertNotNull(usuarioDao);
        assertNotNull(gasolineraDao);
    }

    //PRUEBA PARA INSERTAR EN LA BASE DE DATO UN NUEVO DATO HISTORICO DE REPOSTAJE
    @Test
    public void addHistorialRepostaje() {
        addGasolinera();
        addUsuario();

        HistorialRepostaje hr = new HistorialRepostaje("10/10/2010",2.8,1.0,usuarioDao.getByUsuario("unex").getUid(),10,5);
        historialRepostajeDao.insert(hr);

        HistorialRepostaje hrInsertado = historialRepostajeDao.getByPrimaryKey(2.8,1.0,usuarioDao.getByUsuario("unex").getUid(),"10/10/2010");

        assertNotNull(hrInsertado);
        assertEquals(hrInsertado.getFecha(),hr.getFecha());
        assertEquals(hrInsertado.getUid(),hr.getUid());
        assertEquals(hrInsertado.getLatitud(),hr.getLatitud(),0.001);
        assertEquals(hrInsertado.getLongitud(),hr.getLongitud(),0.001);
        assertEquals(hrInsertado.getLitros(),hr.getLitros(),0.001);
        assertEquals(hrInsertado.getPrecio(),hr.getPrecio(),0.001);
    }

    //DATOS QUE YA DISPONDRÍA LA BD
    public void addGasolinera (){
        Gasolinera g = new Gasolinera(2.8,1.0,10003,"san blas","10:00-13:00","Cáceres","Cáceres","Cáceres","CEPSA");
        gasolineraDao.insert(g);
    }

    //DATOS QUE YA DISPONDRÍA LA BD
    public void addUsuario (){
        Usuario u = new Usuario("unex","unex@alumnos.unex.es","12345");
        usuarioDao.insert(u);
    }

    @After
    public void cerrarVolatileBD(){
        volatileBD.close();
    }
}