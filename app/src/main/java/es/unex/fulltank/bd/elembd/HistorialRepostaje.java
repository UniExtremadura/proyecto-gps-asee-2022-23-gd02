package es.unex.fulltank.bd.elembd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.unex.fulltank.bd.roomdb.DateConverter;

/**
 * La idea de esta clase es representar un historial para registrar cada vez que se hace un repostaje
 * en la aplicación. Se registran datos de la Gasolinera en la que se reposta, la fecha de repostaje,
 * el Vehículo que hace el repostaje y otros campos.
 * Es una clase implementada con anotaciones de Room. Se ha configurado de tal manera que cada atributo
 * de esta clase se mapea a una columna de una tabla de la Base de datos de este proyecto,
 * utilizando anotaciones.
 * @author Grupo PGD02
 * @version 1.0
 */
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
    private String vehiculoId;
    @ColumnInfo(name = "gasoId")
    private int gasoId;

    @Ignore
    public final static SimpleDateFormat FORMAT = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.US);

    public HistorialRepostaje(Date fecha, int litros, String vehiculoId, int gasoId) {
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

    public String getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(String vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public int getGasoId() {
        return gasoId;
    }

    public void setGasoId(int gasoId) {
        this.gasoId = gasoId;
    }
}
