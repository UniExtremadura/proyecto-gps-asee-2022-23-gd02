package es.unex.fulltank.bd.elembd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabla_usuario", foreignKeys = @ForeignKey(entity = Vehiculo.class, parentColumns = "vehiculoId", childColumns = "vehiculoId"))
public class Usuario {

    @PrimaryKey
    @ColumnInfo(name = "uid")
    private int uid;
    @ColumnInfo(name = "nombre")
    private String nombre;
    @ColumnInfo(name = "apellido1")
    private String apellido1;
    @ColumnInfo(name = "apellido2")
    private String apellido2;
    @ColumnInfo(name = "password")
    private String password;
    @ColumnInfo(name = "puntos", defaultValue = "0")
    private int puntos;
    @ColumnInfo(name = "vehiculoId")
    private String vehiculoId;

    public Usuario(int uid, String nombre, String apellido1, String apellido2, String password, String vehiculoId, int puntos) {
        this.uid = uid;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.password = password;
        this.vehiculoId = vehiculoId;
        this.puntos = puntos;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setVehiculoId(String vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public int getUid() {
        return uid;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public String getPassword() {
        return password;
    }

    public String getVehiculoId() {
        return vehiculoId;
    }
}
