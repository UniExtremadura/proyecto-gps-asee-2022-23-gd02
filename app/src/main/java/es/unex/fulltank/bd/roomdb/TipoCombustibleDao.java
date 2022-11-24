package es.unex.fulltank.bd.roomdb;

import static androidx.room.OnConflictStrategy.ABORT;
import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.bd.elembd.TipoCombustible;
import es.unex.fulltank.bd.elembd.Usuario;

@Dao
public interface TipoCombustibleDao {
    /**
     * Devuelve una lista con todos los Usuarios de la tabla de usuarios.
     * @return Una lista con todos los usuarios de la tabla de usuario.
     */
    @Query("SELECT * FROM tipo_combustible")
    List<TipoCombustible> getAll();


    @Query("SELECT * FROM tipo_combustible WHERE cid = :cid")
    TipoCombustible getById(int cid);



    @Query("SELECT * FROM tipo_combustible WHERE nombre = :nombre")
    TipoCombustible getByNombre(String nombre);



    @Insert (onConflict = IGNORE)
    void insert (TipoCombustible tipoCombustible);


    @Update
    void update(TipoCombustible tipoCombustible);


    @Query("DELETE FROM tipo_combustible")
    void deleteAll();
}
