package es.unex.giis.asee.fulltank.roomdb;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.unex.giis.asee.fulltank.elembd.Gasolinera;
import es.unex.giis.asee.fulltank.elembd.HistorialRepostaje;
import es.unex.giis.asee.fulltank.elembd.Usuario;
import es.unex.giis.asee.fulltank.elembd.UsuarioVehiculo;
import es.unex.giis.asee.fulltank.elembd.Vehiculo;

@Database(entities = {Gasolinera.class, HistorialRepostaje.class, Usuario.class, Vehiculo.class, UsuarioVehiculo.class}, version = 1) //TODO ooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
public abstract class BD extends RoomDatabase {
    private static BD instance;

    public static BD getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context, BD.class, "baseDeDatos.db").build(); //TODO El  nombre del ficherooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo
        }
        return instance;
    }

    public abstract UsuarioDao getUsuarioDao();
    public abstract VehiculoDao getVehiculoDao();
    public abstract HistorialRepostajeDao getHistorialRepostajeDao();
    public abstract GasolineraDao getGasolineraDao();
    public abstract UsuarioVehiculoDao getUsuarioVehiculoDao();
}
