package es.unex.fulltank.bd.roomdb;

import static androidx.room.OnConflictStrategy.IGNORE;
import static androidx.room.OnConflictStrategy.REPLACE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.bd.elembd.Gasolinera;

/**
 * El DAO de la gasolinera define todas las operaciones necesarias para el acceso a la tabla de la
 * gasolinera de la Base de datos.
 * @author Grupo PGD02
 * @version 1.0
 */
@Dao
public interface GasolineraDao {
    /**
     * Devuelve todas las gasolineras.
     * @return Una lista con todas las gasolineras.
     */
    @Query("SELECT * FROM gasolinera")
    List<Gasolinera> getAll();

    /**
     * Devuelve una gasolinera dado su ID.
     * @return Una gasolinera con el ID que viene por parametro.
     */
    @Query("SELECT * FROM gasolinera WHERE latitud = :latitud AND longitud = :longitud")
    Gasolinera getByCoords(Double latitud, Double longitud);

    /**
     * Inserta una gasolinera en la BD.
     * @param gasolinera
     */
    @Insert (onConflict = REPLACE)
    void insert (Gasolinera gasolinera);

    /**
     * Actualiza la gasolinera dada por parámetro en la tabla de la base de datos.
     * @param gasolinera
     */
    @Update
    void update(Gasolinera gasolinera);

    /**
     * Borra una gasolinera de la tabla
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
