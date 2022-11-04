package es.unex.giis.asee.fulltank.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.giis.asee.fulltank.elembd.UsuarioVehiculo;

@Dao
public interface UsuarioVehiculoDao {
    @Query("SELECT * FROM tabla_usuario_vehiculo")
    List<UsuarioVehiculo> getAll();
    @Query("SELECT * FROM tabla_usuario_vehiculo WHERE uid = :uid")
    List<UsuarioVehiculo> getById(int uid);
    @Insert
    long insert (UsuarioVehiculo usuario);
    @Update
    int update(UsuarioVehiculo usuario);
    @Query("DELETE FROM tabla_usuario_vehiculo")
    void deleteAll();
}
