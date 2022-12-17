package es.unex.fulltank.datos.roomdb;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.datos.modelo.TipoCombustible;

@Dao
public interface TipoCombustibleDao {
    /**
     * Devuelve una lista con todos los Usuarios de la tabla de usuarios.
     *
     * @return Una lista con todos los usuarios de la tabla de usuario.
     */
    @Query("SELECT * FROM tipo_combustible")
    List<TipoCombustible> getAll();


    @Query("SELECT * FROM tipo_combustible WHERE cid = :cid")
    TipoCombustible getById(int cid);


    @Query("SELECT * FROM tipo_combustible WHERE nombre = :nombre")
    TipoCombustible getByNombre(String nombre);


    @Query("SELECT * FROM tipo_combustible tc INNER JOIN (SELECT cid FROM combustible_gasolinera WHERE latitud=:latitud AND longitud=:longitud) cg ON (tc.cid=cg.cid)")
    LiveData<List<TipoCombustible>> getFilteredByCombustibleGasolinera(double latitud, double longitud);

    @Insert(onConflict = IGNORE)
    void insert(TipoCombustible tipoCombustible);

    @Insert(onConflict = IGNORE)
    void bulkInsert(List<TipoCombustible> lTipos);

    @Update
    void update(TipoCombustible tipoCombustible);

    @Query("SELECT count(*) FROM tipo_combustible")
    int getNumeroGasolineras();

    @Query("DELETE FROM tipo_combustible")
    void deleteAll();
}
