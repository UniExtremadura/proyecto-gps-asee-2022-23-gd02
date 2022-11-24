package es.unex.fulltank.bd.elembd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "gasolinera_resenha", primaryKeys = {"latitud", "longitud","uid","fecha"}, foreignKeys = {
        @ForeignKey(entity = Gasolinera.class, parentColumns = {"latitud", "longitud"}, childColumns = {"latitud", "longitud"}),
        @ForeignKey(entity = Usuario.class, parentColumns = {"uid"}, childColumns = {"uid"})
})
public class GasolineraResenha {

    @ColumnInfo(name = "fecha")
    @NonNull
    private String fecha;
    @ColumnInfo(name = "latitud")
    @NonNull
    private double latitud;
    @ColumnInfo(name = "longitud")
    @NonNull
    private double longitud;
    @ColumnInfo(name = "uid")
    @NonNull
    private int uid;

    @ColumnInfo(name = "comentario")
    private String comentario ;


    public GasolineraResenha(String fecha, double latitud, double longitud,int uid,String comentario) {
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = longitud;
        this.uid=uid;
        this.comentario = comentario;
    }

    @NonNull
    public String getFecha() {
        return fecha;
    }

    public void setFecha(@NonNull String fecha) {
        this.fecha = fecha;
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

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
