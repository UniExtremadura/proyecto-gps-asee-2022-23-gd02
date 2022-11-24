package es.unex.fulltank.bd.roomdb;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.bd.elembd.GasolineraResenha;
import es.unex.fulltank.bd.elembd.HistorialRepostaje;

@Dao
public interface GasolineraResenhaDao {



    @Query("SELECT * FROM gasolinera_resenha")
    List<GasolineraResenha> getAll();

    @Query("SELECT * FROM gasolinera_resenha WHERE latitud = :latitud and longitud= :longitud")
    List<GasolineraResenha> getByCoords(double latitud,double longitud);

    @Query("SELECT * FROM gasolinera_resenha WHERE fecha = :timestamp")
    GasolineraResenha getByTimestamp(String timestamp);

    @Query("SELECT * FROM gasolinera_resenha WHERE latitud = :latitud and longitud = :longitud and fecha = :timestamp")
    GasolineraResenha getByPrimaryKey(double latitud, double longitud,String timestamp);

    @Insert (onConflict = IGNORE)
    void insert (GasolineraResenha gasolineraResenha);


    @Update
    void update(GasolineraResenha gasolineraResenha);


    @Delete
    void delete(GasolineraResenha gasolineraResenha);


    @Query("DELETE FROM gasolinera_resenha")
    void deleteAll();
}
