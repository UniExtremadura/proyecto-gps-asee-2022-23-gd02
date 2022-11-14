package es.unex.fulltank.bd.roomdb;

import android.content.Context;
import android.util.Log;

import java.util.Date;

import es.unex.fulltank.bd.elembd.Gasolinera;
import es.unex.fulltank.bd.elembd.HistorialRepostaje;
import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.elembd.Vehiculo;

/**
 * En esta clase se definen todas esas operaciones que queramos que nuestra BD haga
 * @author Grupo PGD02
 * @version 1.0
 */
public class Api_BD{ //TODO Clase en pruebas NO USAR

    /**
     * Inserta un usuario en la BD.
     * @param usuario
     * @param context
     */
    public static void insert(Usuario usuario, Context context) {
        UsuarioDao uDao = BD.getInstance(context).getUsuarioDao();
        if (uDao.getById(usuario.getUid()) == null) {
            Log.d("Api_BD", "Before insertion, the user is: " + usuario.getNombre());
            uDao.insert(usuario);
        } else {
            Log.e("Api_BD", "No se puede insertar un usuario que ya está en la BD");
        }
    }

    /**
     * Inserta un HistorialRepostaje en la BD.
     * @param h Elemento de HistorialRepostaje a insertar
     * @param context
     */
    public static void insert(HistorialRepostaje h, Context context){
        HistorialRepostajeDao hDao = BD.getInstance(context).getHistorialRepostajeDao();
        if(hDao.getByTimestamp(DateConverter.toTimestamp(h.getFecha())) == null) {
            Log.d("Api_BD", "Antes de la insercion, el historial es: " + h.getFecha());
            hDao.insert(h);
        } else {
            Log.e("Api_BD", "No se puede insertar un historial que ya está en la BD");
        }
    }

    /**
     * Inserta un vehiculo en la BD.
     * @param vehiculo
     * @param context
     */
    public static void insert(Vehiculo vehiculo, Context context) {
        VehiculoDao vDao = BD.getInstance(context).getVehiculoDao();
        if (vDao.getById(vehiculo.getVehiculoId()) == null) {
            Log.d("Api_BD", "Antes de la insercion, el id del vehiculo es: " + vehiculo.getVehiculoId());
            vDao.insert(vehiculo);
        } else {
            Log.e("Api_BD", "No se puede insertar un usuario que ya está en la BD");
        }
    }

    /**
     * Inserta una Gasolinera en la BD.
     * @param gasolinera
     * @param context
     */
    public static void insert(Gasolinera gasolinera, Context context) {
        GasolineraDao gDao = BD.getInstance(context).getGasolineraDao();
        if (gDao.getByCoords(gasolinera.getLatitud(), gasolinera.getLongitud()) == null) {
            Log.d("Api_BD", "Antes de la insercion, la direccion de la gasolinera es: " + gasolinera.getDireccion());
            gDao.insert(gasolinera);
        } else {
            Log.e("Api_BD", "No se puede insertar un usuario que ya está en la BD");
        }
    }

    /**
     * Consulta un usuario en la BD por su uid.
     * @param uid
     * @param context
     * @return
     */
    public static Usuario getByUid(int uid, Context context) {
        UsuarioDao uDao = BD.getInstance(context).getUsuarioDao();
        return uDao.getById(uid);
    }

    /**
     * Consulta un HistorialRepostaje de la BD por su fecha.
     * @param date
     * @param context
     * @return
     */
    public static HistorialRepostaje getHistoByDate (Date date, Context context) {
        HistorialRepostajeDao hDao = BD.getInstance(context).getHistorialRepostajeDao();
        return hDao.getByTimestamp(DateConverter.toTimestamp(date));
    }
}
