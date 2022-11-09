package es.unex.fulltank.bd.elembd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

/**
 * La clase UsuarioVehiculo representa una relación entre Usuario y Vehículo, de tal manera que
 * se representa qué vehículo pertenece a cada Usuario.
 * Es una clase implementada con anotaciones de Room. Se ha configurado de tal manera que cada atributo
 * de esta clase se mapea a una columna de una tabla de la Base de datos de este proyecto,
 * utilizando anotaciones.
 * @author Grupo PGD02
 * @version 1.0
 */
@Entity(tableName = "tabla_usuario_vehiculo", primaryKeys = {"uid", "vehiculoId"}, foreignKeys = {
        @ForeignKey(entity = Usuario.class, parentColumns = "uid", childColumns = "uid"),
        @ForeignKey(entity = Vehiculo.class, parentColumns = "vehiculoId", childColumns = "vehiculoId")
})
public class UsuarioVehiculo {

    @ColumnInfo(name = "uid")
    private int uid;

    @ColumnInfo(name = "vehiculoId")
    private int vehiculoId;

    public UsuarioVehiculo(int uid, int vehiculoId) {
        this.uid = uid;
        this.vehiculoId = vehiculoId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }
}
