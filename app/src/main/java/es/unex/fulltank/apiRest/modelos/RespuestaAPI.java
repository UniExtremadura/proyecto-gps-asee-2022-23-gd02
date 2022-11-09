package es.unex.fulltank.apiRest.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Clase que define la estructura de un objeto RespuestaApi
 * @author Grupo PGD02
 * @version 1.0
 */

public class RespuestaAPI {
    //Se utiliza la anotación SerializedName que indica que este miembro debe serializarse
    // en JSON con el valor de nombre proporcionado como su nombre de campo.
    @SerializedName("Fecha")
    private String fecha;
    @SerializedName("ListaEESSPrecio")
    private ArrayList<Gasolinera> ListaGasolineras;
    @SerializedName("Nota")
    private String nota;
    @SerializedName("ResultadoConsulta")
    private String resultadoConsulta;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<Gasolinera> getListaGasolineras() {
        return ListaGasolineras;
    }

    public void setListaGasolineras(ArrayList<Gasolinera> listaGasolineras) {
        ListaGasolineras = listaGasolineras;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public String getResultadoConsulta() {
        return resultadoConsulta;
    }

    public void setResultadoConsulta(String resultadoConsulta) {
        this.resultadoConsulta = resultadoConsulta;
    }
}
