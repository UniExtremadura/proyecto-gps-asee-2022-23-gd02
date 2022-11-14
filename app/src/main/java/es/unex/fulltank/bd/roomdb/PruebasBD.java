package es.unex.fulltank.bd.roomdb;

import android.content.Context;
import android.util.Log;

import java.util.Date;

import es.unex.fulltank.bd.elembd.Gasolinera;
import es.unex.fulltank.bd.elembd.HistorialRepostaje;
import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.elembd.Vehiculo;

/**
 * Esta clase son las pruebas para la BD.
 * @author Grupo PGD02
 * @version 1.0
 */
public class PruebasBD {
    private final Context context;

    public PruebasBD(Context context){
        this.context = context;
    }

    /**
     * Se intenta insertar un usuario y recuperar ese usuario de la BD. Primero se inserta
     * el vehiculo de ese usuario debido a la restricción de Foreign key.
     */
    public void pruebaUsuario() {
        Vehiculo v1 = new Vehiculo("12BA", "Audi", "A3", 5.0, 1234.9);
        Usuario u1 = new Usuario(1234, "Alberto", "Lopez", "Rodriguez",
                "1234", "12BA", 1);
        Api_BD.insert(v1, context);
        Api_BD.insert(u1, context);
        Log.i(Api_BD.getByUid(1234, context).getNombre(), "Nombre conseguido");
    }

    /**
     * La idea es insertar un HistorialRepostaje para luego recuperarlo de la BD y ver si se ha
     * insertado correctamente.
     * Debido a la restricción de Foreign key, se insertan el vehiculo y la gasolinera utilizados
     * por el historial. Luego se inserta el historial.
     */
    public void pruebaHistorial(){
        Date d = new Date(50000);
        Vehiculo v1 = new Vehiculo("12BA", "Audi", "A3", 5.0, 1234.9);
        Gasolinera g1 = new Gasolinera("37.245", "24.789");
        HistorialRepostaje h1 = new HistorialRepostaje(d, 12, "12BA", "37.245", "24.789");
        Api_BD.insert(v1, context);
        Api_BD.insert(g1, context);
        Api_BD.insert(h1, context);
        Log.i("MainActivity", "Litros: " + Api_BD.getHistoByDate(d, context).getLitros());
    }

    /**
     * Este metodo debe ejecutarse en un hilo DISTINTO al hilo principal
     */
    public void ejecutarPruebas(){
        // TODO Crear un Tag como propiedad de la clase que, usando introspección, guarde el nombre de la clase
        //Log.d(Tag, "Mensaje");
        pruebaUsuario();
        pruebaHistorial();
    }
}
