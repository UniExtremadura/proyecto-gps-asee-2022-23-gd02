package es.unex.fulltank.datos.roomdb;

import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.datos.modelo.Gasolinera;

/**
 * El DAO de la gasolinera define todas las operaciones necesarias para el acceso a la tabla de la
 * gasolinera de la Base de datos.
 *
 * @author Grupo PGD02
 * @version 1.0
 */
@Dao
public interface GasolineraDao {
    /**
     * Devuelve todas las gasolineras.
     *
     * @return Una lista con todas las gasolineras.
     */
    @Query("SELECT * FROM gasolinera")
    LiveData<List<Gasolinera>> getAll();

    @Query("SELECT * FROM gasolinera")
    List<Gasolinera> getAllTest();

    /**
     * Devuelve una gasolinera dado su ID.
     *
     * @return Una gasolinera con el ID que viene por parametro.
     */
    @Query("SELECT * FROM gasolinera WHERE latitud = :latitud AND longitud = :longitud")
    Gasolinera getByCoords(Double latitud, Double longitud);

    // WHERE (latitud  BETWEEN :latitud-0.5 AND :latitud+0.5) AND (longitud BETWEEN :longitud-0.5 AND :longitud+0.5)
    //Double latitud, Double longitud
    @Query("SELECT * FROM gasolinera WHERE (latitud  BETWEEN :lat-0.5 AND :lat+0.5) AND (longitud BETWEEN :lon-0.5 AND :lon+0.5)" +
            "ORDER BY (ABS(latitud - :lat) + ABS(longitud - :lon)) ASC ")
    LiveData<List<Gasolinera>> getFilterGasolinerasByCoords(Double lat, Double lon);


    @Query("SELECT * FROM gasolinera INNER JOIN gasolinera_favorita " +
            "ON (gasolinera.longitud = gasolinera_favorita.longitud AND gasolinera.latitud = gasolinera_favorita.latitud) " +
            "WHERE gasolinera_favorita.uid = :idu  ")
    LiveData<List<Gasolinera>> getGasolinerasFavoritas(int idu);

    @Query("SELECT * FROM gasolinera g INNER JOIN combustible_gasolinera cg ON (g.latitud=cg.latitud AND g.longitud=cg.longitud)" +
            "WHERE  (g.latitud  BETWEEN :lat-0.5 AND :lat+0.5) AND (g.longitud BETWEEN :lon-0.5 AND :lon+0.5) AND cg.cid=:idc  ORDER BY cg.precio")
    LiveData<List<Gasolinera>> getGasolinerasCombustible(int idc, Double lat, Double lon);

    @Query("SELECT count(*) FROM gasolinera")
    int getNumeroGasolineras();

    /**
     * Inserta una gasolinera en la BD.
     *
     * @param gasolinera
     */
    @Insert(onConflict = REPLACE)
    void insert(Gasolinera gasolinera);

    @Insert(onConflict = REPLACE)
    void bulkInsert(List<Gasolinera> gasolinera);

    /**
     * Actualiza la gasolinera dada por parámetro en la tabla de la base de datos.
     *
     * @param gasolinera
     */
    @Update
    void update(Gasolinera gasolinera);

    /**
     * Borra una gasolinera de la tabla
     *
     * @param gasolinera
     */
    @Delete
    void delete(Gasolinera gasolinera);

    /**
     * Borra todos los datos de la tabla de gasolineras.
     */
    @Query("DELETE FROM gasolinera")
    void deleteAll();
}
