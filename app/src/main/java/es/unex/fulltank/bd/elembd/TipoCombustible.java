package es.unex.fulltank.bd.elembd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tipo_combustible")
public class TipoCombustible implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "cid")
    private int cid;
    @ColumnInfo(name = "nombre")
    private String nombre;

    public TipoCombustible(int cid, String nombre) {
        this.cid = cid;
        this.nombre=nombre;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
