package es.unex.fulltank.bd.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.bd.elembd.Usuario;

/**
 * El DAO de Usuario define todas las operaciones necesarias para el acceso a la tabla de
 * usuarios de la Base de datos.
 * @author Grupo PGD02
 * @version 1.0
 */
@Dao
public interface UsuarioDao {
    /**
     * Devuelve una lista con todos los Usuarios de la tabla de usuarios.
     * @return Una lista con todos los usuarios de la tabla de usuario.
     */
    @Query("SELECT * FROM tabla_usuario")
    List<Usuario> getAll();

    /**
     * Devuelve un Usuario dado su id.
     * @param uid
     */
    @Query("SELECT * FROM tabla_usuario WHERE uid = :uid")
    Usuario getById(int uid);

    /**
     * Devuelve un usuario dado su nombre y apellidos.
     * @param nombre
     * @param apellido1
     * @param apellido2
     */
    @Query("SELECT * FROM tabla_usuario WHERE nombre = :nombre AND apellido1 = :apellido1 and apellido2 = :apellido2")
    Usuario getByFullName(String nombre, String apellido1, String apellido2);

    /**
     * Inserta un usuario en la tabla de usuarios.
     * @param usuario
     */
    @Insert
    void insert (Usuario usuario);

    /**
     * Actualiza un Usuario de la tabla de usuarios.
     * @param usuario
     */
    @Update
    void update(Usuario usuario);

    /**
     * Borra todos los usuarios de la tabla de usuarios.
     */
    @Query("DELETE FROM tabla_usuario")
    void deleteAll();
}