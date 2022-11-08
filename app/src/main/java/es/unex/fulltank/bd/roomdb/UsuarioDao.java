package es.unex.fulltank.bd.roomdb;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.bd.elembd.Usuario;

@Dao
public interface UsuarioDao {
    @Query("SELECT * FROM tabla_usuario")
    List<Usuario> getAll();
    @Query("SELECT * FROM tabla_usuario WHERE uid = :uid")
    Usuario getById(int uid);
    @Query("SELECT * FROM tabla_usuario WHERE nombre = :nombre AND apellido1 = :apellido1 and apellido2 = :apellido2")
    Usuario getByFullName(String nombre, String apellido1, String apellido2);
    @Insert
    long insert (Usuario usuario);
    @Update
    int update(Usuario usuario);
    @Query("DELETE FROM tabla_usuario")
    void deleteAll();
}