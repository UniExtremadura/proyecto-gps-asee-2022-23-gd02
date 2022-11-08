package es.unex.fulltank.bd.roomdb;

import android.content.Context;
import android.util.Log;

import java.util.Date;

import es.unex.fulltank.bd.elembd.HistorialRepostaje;
import es.unex.fulltank.bd.elembd.Usuario;

/**
 * En esta clase se definen todas esas operaciones que queramos que nuestra BD haga
 */
public class Api_BD{ //TODO Clase en pruebas NO USAR

    /**
     * Antes de insertar un usuario, se comprubeba si no está ya insertado
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

    public static Usuario getById(int uid, Context context) {
        UsuarioDao uDao = BD.getInstance(context).getUsuarioDao();
        return uDao.getById(uid);
    }

    public static HistorialRepostaje getByDate (Date date, Context context) {
        HistorialRepostajeDao hDao = BD.getInstance(context).getHistorialRepostajeDao();
        return hDao.getByTimestamp(DateConverter.toTimestamp(date));
    }

    public static void insert(HistorialRepostaje h, Context context){
        HistorialRepostajeDao hDao = BD.getInstance(context).getHistorialRepostajeDao();
        if(hDao.getByTimestamp(DateConverter.toTimestamp(h.getFecha())) == null) {
            Log.d("Api_BD", "Antes de la insercion, el historial es: " + h.getFecha());
            hDao.insert(h);
        } else {
            Log.e("Api_BD", "No se puede insertar un historial que ya está en la BD");
        }
    }
}
