package es.unex.fulltank.bd.roomdb;

import static androidx.room.OnConflictStrategy.IGNORE;

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
    @Query("SELECT * FROM usuario")
    List<Usuario> getAll();

    /**
     * Devuelve un Usuario dado su id.
     * @param uid
     */
    @Query("SELECT * FROM usuario WHERE uid = :uid")
    Usuario getById(int uid);


    /**
     * Devuelve un Usuario dado su nombre de usuario.
     * @param usuario
     */
    @Query("SELECT * FROM usuario WHERE usuario = :usuario")
    Usuario getByUsuario(String usuario);

    /**
     * Devuelve un Usuario dado su correo.
     * @param correo
     */
    @Query("SELECT * FROM usuario WHERE correo = :correo")
    Usuario getByCorreo(String correo);

    /**
     * Devuelve un Usuario dado su usuario y contraseña.
     *
     * @param usuario
     * @param contra
     */
    @Query("SELECT * FROM usuario WHERE usuario = :usuario and contra = :contra")
    Usuario getByLogin(String usuario, String contra);

    /**
     * Inserta un usuario en la tabla de usuarios.
     * @param usuario
     */
    @Insert (onConflict = IGNORE)
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
    @Query("DELETE FROM usuario")
    void deleteAll();
}