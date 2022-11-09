package es.unex.fulltank.bd.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.bd.elembd.UsuarioVehiculo;

/**
 * El DAO de la UsuarioVehiculo define todas las operaciones necesarias para el acceso a la tabla de la
 * relación Usuario - Vehiculo de la Base de datos.
 * @author Grupo PGD02
 * @version 1.0
 */
@Dao
public interface UsuarioVehiculoDao {
    /**
     * Devuelve una lista de todos los UsuarioVehiculo de la tabla.
     */
    @Query("SELECT * FROM tabla_usuario_vehiculo")
    List<UsuarioVehiculo> getAll();

    /**
     * Devuelve una lista de todos los Vehiculos de un Usuario en concreto dado su ID.
     * @param uid
     */
    @Query("SELECT * FROM tabla_usuario_vehiculo WHERE uid = :uid")
    List<UsuarioVehiculo> getById(int uid);

    /**
     * Inserta una Instancia de UsuarioVehiculo en la tabla.
     * @param usuario
     */
    @Insert
    void insert (UsuarioVehiculo usuario);

    /**
     * Actualiza un elemento de UsuarioVehiculo de la tabla.
     * @param usuario
     */
    @Update
    void update(UsuarioVehiculo usuario);

    /**
     * Borra toda la tabla de UsuarioVehiculo.
     */
    @Query("DELETE FROM tabla_usuario_vehiculo")
    void deleteAll();
}
