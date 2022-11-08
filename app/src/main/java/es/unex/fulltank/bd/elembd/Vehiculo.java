package es.unex.fulltank.bd.elembd;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tabla_vehiculo")
public class Vehiculo {

    @PrimaryKey
    @ColumnInfo(name = "vehiculoId")
    private int vehiculoId;
    @ColumnInfo(name = "marca")
    private String marca;
    @ColumnInfo(name = "modelo")
    private String modelo;
    @ColumnInfo(name = "consumo")
    private double consumo;
    @ColumnInfo(name = "peso")
    private double peso;

    public Vehiculo(int vehiculoId, String marca, String modelo, double consumo, double peso) {
        this.vehiculoId = vehiculoId;
        this.marca = marca;
        this.modelo = modelo;
        this.consumo = consumo;
        this.peso = peso;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
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
