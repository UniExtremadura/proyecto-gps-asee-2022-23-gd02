package es.unex.fulltank.bd.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.bd.elembd.Vehiculo;

@Dao
public interface VehiculoDao {
    @Query("SELECT * FROM tabla_vehiculo")
    List<Vehiculo> getAll();
    @Query("SELECT * FROM tabla_vehiculo WHERE vehiculoId = :vehiculo")
    Vehiculo getById(int vehiculo);
    @Insert
    long insert (Vehiculo v);
    @Update
    int update(Vehiculo v);
    @Delete
    int delete(Vehiculo v);
    @Query("DELETE FROM tabla_vehiculo")
    void deleteAll();
}
