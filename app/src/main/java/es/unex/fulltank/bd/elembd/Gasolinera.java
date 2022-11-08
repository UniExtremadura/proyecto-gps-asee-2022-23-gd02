package es.unex.fulltank.bd.elembd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabla_gasolinera")
public class Gasolinera {

    @PrimaryKey
    @ColumnInfo(name = "gasoId")
    private int gasoId;
    @ColumnInfo(name = "nombre")
    private String nombre;

    public Gasolinera(int gasoId, String nombre) {
        this.gasoId = gasoId;
        this.nombre = nombre;
    }

    public int getGasoId() {
        return gasoId;
    }

    public void setGasoId(int gasoId) {
        this.gasoId = gasoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
