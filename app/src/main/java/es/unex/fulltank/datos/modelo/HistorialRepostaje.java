package es.unex.fulltank.datos.modelo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

/**
 * La idea de esta clase es representar un historial para registrar cada vez que se hace un repostaje
 * en la aplicación. Se registran datos de la Gasolinera en la que se reposta, la fecha de repostaje,
 * el Vehículo que hace el repostaje y otros campos.
 * Es una clase implementada con anotaciones de Room. Se ha configurado de tal manera que cada atributo
 * de esta clase se mapea a una columna de una tabla de la Base de datos de este proyecto,
 * utilizando anotaciones.
 *
 * @author Grupo PGD02
 * @version 1.0
 */
@Entity(tableName = "historial_repostaje", primaryKeys = {"latitud", "longitud", "uid", "fecha"}, foreignKeys = {
        @ForeignKey(entity = Gasolinera.class, parentColumns = {"latitud", "longitud"}, childColumns = {"latitud", "longitud"}),
        @ForeignKey(entity = Usuario.class, parentColumns = {"uid"}, childColumns = {"uid"})
})
public class HistorialRepostaje {
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

    @ColumnInfo(name = "litros")
    private double litros;  //Litros de gasolina repostados

    @ColumnInfo(name = "precio")
    private double precio;


    public HistorialRepostaje(String fecha, double latitud, double longitud, int uid, double precio, double litros) {
        this.fecha = fecha;
        this.latitud = latitud;
        this.longitud = longitud;
        this.uid = uid;
        this.litros = litros;
        this.precio = precio;


    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
