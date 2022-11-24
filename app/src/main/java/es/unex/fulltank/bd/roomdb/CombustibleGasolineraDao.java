package es.unex.fulltank.bd.roomdb;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.bd.elembd.CombustibleGasolinera;

@Dao
public interface CombustibleGasolineraDao {

    @Query("SELECT * FROM combustible_gasolinera")
    List<CombustibleGasolinera> getAll();


    @Query("SELECT * FROM combustible_gasolinera WHERE cid = :cid")
    List<CombustibleGasolinera> getById(int cid);

    @Query("SELECT * FROM combustible_gasolinera WHERE latitud = :latitud and longitud = :longitud")
    List<CombustibleGasolinera>  getByCoords(double latitud, double longitud);

    @Query("SELECT * FROM combustible_gasolinera WHERE latitud = :latitud and longitud = :longitud and cid=:cid")
    CombustibleGasolinera  getByPrimaryKey(double latitud, double longitud, int cid);

    @Insert (onConflict = REPLACE)
    void insert (CombustibleGasolinera combustibleGasolinera);


    @Update
    void update(CombustibleGasolinera combustibleGasolinera);


    @Delete
    void delete(CombustibleGasolinera combustibleGasolinera);


    @Query("DELETE FROM combustible_gasolinera")
    void deleteAll();
}
