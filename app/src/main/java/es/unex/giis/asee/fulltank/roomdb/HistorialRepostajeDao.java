package es.unex.giis.asee.fulltank.roomdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

import es.unex.giis.asee.fulltank.elembd.HistorialRepostaje;

@Dao
public interface HistorialRepostajeDao {

    @Query("SELECT * FROM tabla_historial_repostaje")
    List<HistorialRepostaje> getAll();

    @Query("SELECT * FROM tabla_historial_repostaje WHERE fecha = :timestamp")
    HistorialRepostaje getByTimestamp(Long timestamp);

    @Insert
    long insert (HistorialRepostaje historial);

    @Update
    int update(HistorialRepostaje historial);

    @Delete
    int delete(HistorialRepostaje historial);

    @Query("DELETE FROM tabla_historial_repostaje")
    void deleteAll();
}
