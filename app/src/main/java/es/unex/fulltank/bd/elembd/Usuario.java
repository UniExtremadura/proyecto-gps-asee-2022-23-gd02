package es.unex.fulltank.bd.elembd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * La clase Usuario representa todos los datos de un Usuario real.
 * Es una clase implementada con anotaciones de Room. Se ha configurado de tal manera que cada atributo
 * de esta clase se mapea a una columna de una tabla de la Base de datos de este proyecto,
 * utilizando anotaciones.
 * @author Grupo PGD02
 * @version 1.0
 */
@Entity(tableName = "usuario")
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uid")
    private int uid;
    @ColumnInfo(name = "usuario")
    private String usuario;
    @ColumnInfo(name = "correo")
    private String correo;
    @ColumnInfo(name = "contra")
    private String contra;

    public Usuario( String usuario, String correo, String contra) {
        this.contra=contra;
        this.usuario=usuario;
        this.correo=correo;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContra() {
        return contra;
    }

    public void setContra(String contra) {
        this.contra = contra;
    }
}
