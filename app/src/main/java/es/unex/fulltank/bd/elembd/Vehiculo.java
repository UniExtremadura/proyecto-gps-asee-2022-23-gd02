package es.unex.fulltank.bd.elembd;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 * La clase Usuario representa todos los datos de un Vehículo real como la matrícula,
 * marca, el modelo y otros atributos que lo identifican.
 * Es una clase implementada con anotaciones de Room. Se ha configurado de tal manera que cada atributo
 * de esta clase se mapea a una columna de una tabla de la Base de datos de este proyecto,
 * utilizando anotaciones.
 * @author Grupo PGD02
 * @version 1.0
 */
@Entity(tableName = "tabla_vehiculo")
public class Vehiculo {

    @PrimaryKey
    @ColumnInfo(name = "vehiculoId")
    @NonNull
    private String vehiculoId;
    @ColumnInfo(name = "marca")
    private String marca;
    @ColumnInfo(name = "modelo")
    private String modelo;
    @ColumnInfo(name = "consumo")
    private double consumo;
    @ColumnInfo(name = "peso")
    private double peso;

    public Vehiculo(String vehiculoId, String marca, String modelo, double consumo, double peso) {
        this.vehiculoId = vehiculoId;
        this.marca = marca;
        this.modelo = modelo;
        this.consumo = consumo;
        this.peso = peso;
    }

    public String getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(String vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }
}
