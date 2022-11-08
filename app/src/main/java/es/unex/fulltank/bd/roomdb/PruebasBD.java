package es.unex.fulltank.bd.roomdb;

import android.content.Context;
import android.util.Log;

import java.util.Date;

import es.unex.fulltank.bd.elembd.HistorialRepostaje;
import es.unex.fulltank.bd.elembd.Usuario;

public class PruebasBD {
    private Context context;

    public PruebasBD(Context context){
        this.context = context;
    }

    public void pruebaUsuario() {
        Usuario u1 = new Usuario(1234, "RafaGuapo", "Cabanillas", "Murillo",
                "1234", "12BA", 1);
        //Probando si me deja insertar 2 veces el mismo elemento (no deberia)
        Api_BD.insert(u1, context);
        Api_BD.insert(u1, context);
        Log.i(Api_BD.getById(1234, context).getNombre(), "Nombre conseguido");
    }

    public void pruebaHistorial(){
        Date d = new Date(System.currentTimeMillis());
        HistorialRepostaje h1 = new HistorialRepostaje(d, 12, 1, 1);
        Api_BD.insert(h1, context);
        Log.i("MainActivity", "Litros: " + Api_BD.getByDate(d, context).getLitros());
    }

    /**
     * Este metodo debe ejecutarse en un hilo DISTINTO al hilo principal
     */
    public void ejecutarPruebas(){
        // TODO Crear un Tag como propiedad de la clase que, usando introspección, guarde el nombre de la clase
        //Log.d(Tag, "Mensaje");
        pruebaUsuario();
        //pruebaHistorial();
    }
}
