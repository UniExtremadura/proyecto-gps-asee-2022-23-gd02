package es.unex.fulltank.bd.elembd;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(tableName = "gasolinera_favorita", primaryKeys = {"latitud", "longitud","uid"}, foreignKeys = {
        @ForeignKey(entity = Gasolinera.class, parentColumns = {"latitud", "longitud"}, childColumns = {"latitud", "longitud"}),
        @ForeignKey(entity = Usuario.class, parentColumns = {"uid"}, childColumns = {"uid"})
})
public class GasolineraFavorita {

    @ColumnInfo(name = "latitud")
    @NonNull
    private double latitud;
    @ColumnInfo(name = "longitud")
    @NonNull
    private double longitud;
    @ColumnInfo(name = "uid")
    @NonNull
    private int uid;

    public GasolineraFavorita(double latitud,double longitud,int uid){
        this.latitud=latitud;
        this.longitud=longitud;
        this.uid=uid;

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
}
