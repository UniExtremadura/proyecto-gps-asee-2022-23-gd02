package es.unex.fulltank.bd.roomdb;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.bd.elembd.HistorialRepostaje;

/**
 * El DAO de la gasolinera define todas las operaciones necesarias para el acceso a la tabla de la
 * gasolinera de la Base de datos.
 * @author Grupo PGD02
 * @version 1.0
 */
@Dao
public interface HistorialRepostajeDao {

    /**
     * Devuelve todos los elementos HistorialRepostaje de la tabla de historiales de la BD.
     * @return todos los elementos de la tabla de HistorialRepostaje.
     */
    @Query("SELECT * FROM historial_repostaje")
    List<HistorialRepostaje> getAll();

    /**
     * Devuelve un historial de repostaje dado un timestamp, el cual se corresponde con su clave
     * primaria
     *
     * @param timestamp
     * @return Un elemento de la clase HistorialRepostaje
     */
    @Query("SELECT * FROM historial_repostaje WHERE fecha = :timestamp")
    HistorialRepostaje getByTimestamp(String timestamp);

    @Query("SELECT * FROM historial_repostaje WHERE uid = :uid")
    List<HistorialRepostaje> getById(int uid);

    @Query("SELECT * FROM historial_repostaje WHERE latitud=:latitud and longitud=:longitud and uid=:uid and fecha = :fecha")
    HistorialRepostaje getByPrimaryKey(double latitud, double longitud, int uid, String fecha);

    /**
     * Inserta un HistorialRepostaje en la BD.
     *
     * @param historial
     */
    @Insert(onConflict = IGNORE)
    void insert(HistorialRepostaje historial);

    /**
     * Actualiza el elemento de la tabla de historiales que se corresponda con el HistorialRepostaje
     * dado por parámetro.
     * @param historial
     */
    @Update
    void update(HistorialRepostaje historial);

    /**
     * Borra un elemento de la clase HistorialRepostaje en la BD.
     * @param historial
     */
    @Delete
    void delete(HistorialRepostaje historial);

    /**
     * Borra todos los elementos de la tabla de historial de repostaje.
     */
    @Query("DELETE FROM historial_repostaje")
    void deleteAll();
}
