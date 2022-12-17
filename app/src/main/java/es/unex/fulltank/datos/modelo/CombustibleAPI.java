package es.unex.fulltank.datos.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Clase que define la estructura de un objeto Combustibles
 *
 * @author Grupo PGD02
 * @version 1.0
 */
public class CombustibleAPI {

    @SerializedName("IDProducto")
    @Expose
    private String IDProducto;

    @SerializedName("NombreProducto")
    @Expose
    private String nombreProducto;

    @SerializedName("NombreProductoAbreviatura")
    @Expose
    private String nombreProductoAbreviatura;

    public String getIDProducto() {
        return IDProducto;
    }

    public void setIDProducto(String IDProducto) {
        this.IDProducto = IDProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getNombreProductoAbreviatura() {
        return nombreProductoAbreviatura;
    }

    public void setNombreProductoAbreviatura(String nombreProductoAbreviatura) {
        this.nombreProductoAbreviatura = nombreProductoAbreviatura;
    }

    public TipoCombustible getTipo() {
        return new TipoCombustible(Integer.parseInt(IDProducto), nombreProducto);
    }
}
