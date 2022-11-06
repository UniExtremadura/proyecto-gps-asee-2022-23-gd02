package es.unex.giis.asee.fulltank.elembd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.unex.giis.asee.fulltank.roomdb.DateConverter;

@Entity(tableName = "tabla_historial_repostaje", foreignKeys = {
        @ForeignKey(entity = Vehiculo.class, parentColumns = "vehiculoId", childColumns = "vehiculoId"),
        @ForeignKey(entity = Gasolinera.class, parentColumns = "gasoId", childColumns = "gasoId")
})
public class HistorialRepostaje {
    @PrimaryKey
    @TypeConverters(DateConverter.class)
    private Date fecha;
    @ColumnInfo(name = "litros")
    private int litros;  //Litros de gasolina repostados
    @ColumnInfo(name = "vehiculoId")
    private int vehiculoId;
    @ColumnInfo(name = "gasoId")
    private int gasoId;

    @Ignore
    public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.US);

    public HistorialRepostaje(Date fecha, int litros, int vehiculoId, int gasoId) {
        this.fecha = fecha;
        this.litros = litros;
        this.vehiculoId = vehiculoId;
        this.gasoId = gasoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getLitros() {
        return litros;
    }

    public void setLitros(int litros) {
        this.litros = litros;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public int getGasoId() {
        return gasoId;
    }

    public void setGasoId(int gasoId) {
        this.gasoId = gasoId;
    }
}
