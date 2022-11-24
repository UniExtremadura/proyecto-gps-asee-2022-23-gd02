package es.unex.fulltank.bd.elembd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;


/**
 * La clase Gasolinera contiene los atributos relacionados con una gasolinera de la vida real,
 * como el id y el nombre. Por ejemplo, la gasolinera Repsol, con ID = 3476.
 * Es una clase implementada con anotaciones de Room. Se ha configurado de tal manera que cada atributo
 * de esta clase se mapea a una columna de una tabla de gasolineras de la Base de datos de este
 * proyecto, utilizando anotaciones.
 * @author Grupo PGD02
 * @version 1.0
 */
@Entity(tableName = "gasolinera", primaryKeys = {"latitud", "longitud"})
public class Gasolinera{
    public Gasolinera(double latitud, double longitud,int cp,String direccion,String horario,String localidad,String municipio,String provincia,String rotulo) {
        this.latitud = latitud;
        this.longitud = longitud;
        this.cp=cp;
        this.direccion=direccion;
        this.horario=horario;
        this.localidad=localidad;
        this.municipio=municipio;
        this.provincia=provincia;
        this.rotulo=rotulo;
    }

    @ColumnInfo(name = "latitud")
    @NonNull
    private double latitud;

    @ColumnInfo(name = "longitud")
    @NonNull
    private double longitud;

    @ColumnInfo(name = "cp")
    private int cp;
    @ColumnInfo(name = "direccion")
    private String direccion;
    @ColumnInfo(name = "horario")
    private String horario;
    @ColumnInfo(name = "localidad")
    private String localidad;
    @ColumnInfo(name = "municipio")
    private String municipio;
    @ColumnInfo(name = "provincia")
    private String provincia;
    @ColumnInfo(name = "rotulo")
    private String rotulo;

    @NonNull
    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(@NonNull double latitud) {
        this.latitud = latitud;
    }

    @NonNull
    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(@NonNull double longitud) {
        this.longitud = longitud;
    }

    public int getCp() {
        return cp;
    }

    public void setCp(int cp) {
        this.cp = cp;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }
}
