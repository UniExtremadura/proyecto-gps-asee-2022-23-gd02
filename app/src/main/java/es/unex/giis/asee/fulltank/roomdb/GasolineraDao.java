package es.unex.giis.asee.fulltank.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.giis.asee.fulltank.elembd.Gasolinera;

@Dao
public interface GasolineraDao {
    @Query("SELECT * FROM tabla_gasolinera")
    List<Gasolinera> getAll();
    @Query("SELECT * FROM tabla_gasolinera WHERE gasoId = :gasolinera")
    Gasolinera getById(int gasolinera);
    @Query("SELECT * FROM tabla_gasolinera WHERE nombre = :nombre")
    Gasolinera getByName(String nombre);
    @Insert
    long insert (Gasolinera gasolinera);
    @Update
    int update(Gasolinera gasolinera);
    @Delete
    int delete(Gasolinera gasolinera);
    @Query("DELETE FROM tabla_gasolinera")
    void deleteAll();
}
