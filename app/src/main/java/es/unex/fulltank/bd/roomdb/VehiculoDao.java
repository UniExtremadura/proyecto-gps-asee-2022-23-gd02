package es.unex.fulltank.bd.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.bd.elembd.Vehiculo;

/**
 * El DAO de Vehiculo define todas las operaciones necesarias para el acceso a la tabla de la
 * vehiculos de la Base de datos.
 * @author Grupo PGD02
 * @version 1.0
 */
@Dao
public interface VehiculoDao {
    /**
     * Retorna una lista con todos los vehiculos de la tabla.
     */
    @Query("SELECT * FROM tabla_vehiculo")
    List<Vehiculo> getAll();

    /**
     * Retorna un Vehiculo, dado por parámetro su ID
     * @param vehiculoId La
     */
    @Query("SELECT * FROM tabla_vehiculo WHERE vehiculoId = :vehiculoId")
    Vehiculo getById(String vehiculoId);

    /**
     * Inserta un vehiculo en la tabla de Vehiculos.
     * @param v El vehiculo a insertar
     */
    @Insert
    void insert (Vehiculo v);

    /**
     * Actualiza un Vehículo de la tabla de vehículos.
     * @param v El vehiculo a actualizar
     */
    @Update
    void update(Vehiculo v);

    /**
     * Borra un Vehiculo de la tabla de vehículos.
     * @param v El vehiculo a borrar
     */
    @Delete
    void delete(Vehiculo v);

    /**
     * Borra todos los elementos de la base de datos.
     */
    @Query("DELETE FROM tabla_vehiculo")
    void deleteAll();
}
