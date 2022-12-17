package es.unex.fulltank.datos.roomdb;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.datos.modelo.CombustibleGasolinera;

@Dao
public interface CombustibleGasolineraDao {

    @Query("SELECT * FROM combustible_gasolinera")
    LiveData<List<CombustibleGasolinera>> getAll();

    @Query("SELECT * FROM combustible_gasolinera")
    List<CombustibleGasolinera> getAllTest();

    @Query("SELECT * FROM combustible_gasolinera WHERE cid = :cid")
    List<CombustibleGasolinera> getById(int cid);

    @Query("SELECT * FROM combustible_gasolinera WHERE latitud = :latitud and longitud = :longitud")
    LiveData<List<CombustibleGasolinera>> getByCoords(double latitud, double longitud);

    @Query("SELECT * FROM combustible_gasolinera WHERE latitud = :latitud and longitud = :longitud")
    List<CombustibleGasolinera> getByCoordsTest(double latitud, double longitud);

    @Query("SELECT * FROM combustible_gasolinera WHERE latitud = :latitud and longitud = :longitud and cid=:cid")
    CombustibleGasolinera getByPrimaryKey(double latitud, double longitud, int cid);

    @Insert(onConflict = REPLACE)
    void insert(CombustibleGasolinera combustibleGasolinera);

    @Insert(onConflict = REPLACE)
    void bulkInsert(List<CombustibleGasolinera> combustibleGasolinera);

    @Update
    void update(CombustibleGasolinera combustibleGasolinera);


    @Delete
    void delete(CombustibleGasolinera combustibleGasolinera);


    @Query("DELETE FROM combustible_gasolinera")
    void deleteAll();
}
