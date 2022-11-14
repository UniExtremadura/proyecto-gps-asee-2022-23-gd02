package es.unex.fulltank.bd.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.unex.fulltank.bd.elembd.UsuarioVehiculo;
import es.unex.fulltank.bd.elembd.Gasolinera;
import es.unex.fulltank.bd.elembd.HistorialRepostaje;
import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.elembd.Vehiculo;

/**
 * La base de datos implementa un patrón singleton.
 *
 */
@Database(entities = {Gasolinera.class, HistorialRepostaje.class, Usuario.class, Vehiculo.class, UsuarioVehiculo.class}, version = 2)
public abstract class BD extends RoomDatabase {
    private static BD instance;

    /**
     * La instancia de la BD se devolverá en caso de que esté creada, en caso contrario, se creará
     * y se devolverá
     * @param context El contexto donde se debe construir la Base de datos
     * @return La instancia de la BD.
     */
    public static BD getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context, BD.class, "baseDeDatos.db")
                    .fallbackToDestructiveMigration()
                    .build();
            //Con lo del fallback se ha solucionado el problema de la excepcion esta de las versiones:
            /*java.lang.IllegalStateException: Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number.*/
        }
        return instance;
    }

    /*Los siguientes métodos devuelven los DAO correspondientes a cada tabla de la BD.*/
    public abstract UsuarioDao getUsuarioDao();
    public abstract VehiculoDao getVehiculoDao();
    public abstract HistorialRepostajeDao getHistorialRepostajeDao();
    public abstract GasolineraDao getGasolineraDao();
    public abstract UsuarioVehiculoDao getUsuarioVehiculoDao();
}
