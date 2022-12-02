package es.unex.fulltank.bd.roomdb;

import static androidx.room.OnConflictStrategy.IGNORE;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unex.fulltank.bd.elembd.GasolineraFavorita;


@Dao
public interface GasolineraFavoritaDao {

    @Query("SELECT * FROM gasolinera_favorita")
    List<GasolineraFavorita> getAll();


    @Query("SELECT * FROM gasolinera_favorita WHERE uid = :uid")
    List<GasolineraFavorita>  getByUid(int uid);

    @Query("SELECT * FROM gasolinera_favorita WHERE latitud = :latitud and longitud = :longitud")
    List<GasolineraFavorita>  getByCoords(double latitud, double longitud);


    @Query("SELECT * FROM gasolinera_favorita WHERE latitud = :latitud and longitud =:longitud and uid =:uid")
    GasolineraFavorita getByPrimaryKey(double latitud,double longitud, int uid);

    @Insert (onConflict = IGNORE)
    void insert (GasolineraFavorita gasolineraFavorita);


    @Update
    void update(GasolineraFavorita gasolineraFavorita);


    @Delete
    void delete(GasolineraFavorita gasolineraFavorita);


    @Query("DELETE FROM gasolinera_favorita")
    void deleteAll();

    @Query("DELETE FROM gasolinera_favorita WHERE latitud=:latitud and longitud=:longitud and uid=:uid")
    void deleteByPrimaryKey(double latitud, double longitud, double uid);
}
