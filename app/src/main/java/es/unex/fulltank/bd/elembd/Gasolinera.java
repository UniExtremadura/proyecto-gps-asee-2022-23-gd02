package es.unex.fulltank.bd.elembd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * La clase Gasolinera contiene los atributos relacionados con una gasolinera de la vida real,
 * como el id y el nombre. Por ejemplo, la gasolinera Repsol, con ID = 3476.
 * Es una clase implementada con anotaciones de Room. Se ha configurado de tal manera que cada atributo
 * de esta clase se mapea a una columna de una tabla de gasolineras de la Base de datos de este
 * proyecto, utilizando anotaciones.
 * @author Grupo PGD02
 * @version 1.0
 */
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
