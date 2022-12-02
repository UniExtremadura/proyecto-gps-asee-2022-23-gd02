package es.unex.fulltank.bd.elembd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.io.Serializable;

@Entity(tableName = "combustible_gasolinera", primaryKeys = {"latitud", "longitud", "cid"}, foreignKeys = {
        @ForeignKey(entity = Gasolinera.class, parentColumns = {"latitud", "longitud"}, childColumns = {"latitud", "longitud"}),
        @ForeignKey(entity = TipoCombustible.class, parentColumns = {"cid"}, childColumns = {"cid"})
})
public class CombustibleGasolinera implements Serializable {

    @ColumnInfo(name = "latitud")
    @NonNull
    private double latitud;
    @ColumnInfo(name = "longitud")
    @NonNull
    private double longitud;
    @ColumnInfo(name = "cid")
    @NonNull
    private int cid;
    @ColumnInfo(name = "precio")
    private double precio;

    public CombustibleGasolinera(double latitud,double longitud, int cid,double precio){
        this.latitud=latitud;
        this.longitud=longitud;
        this.cid=cid;
        this.precio=precio;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
