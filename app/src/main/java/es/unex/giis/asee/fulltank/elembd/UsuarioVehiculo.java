package es.unex.giis.asee.fulltank.elembd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

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
