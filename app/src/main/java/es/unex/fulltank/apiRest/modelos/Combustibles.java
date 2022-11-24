package es.unex.fulltank.apiRest.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Clase que define la estructura de un objeto Combustibles
 * @author Grupo PGD02
 * @version 1.0
 */
public class Combustibles {
    @SerializedName("IDProducto")
    private String IDProducto;
    @SerializedName("NombreProducto")
    private String nombreProducto;
    @SerializedName("NombreProductoAbreviatura")
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
}
