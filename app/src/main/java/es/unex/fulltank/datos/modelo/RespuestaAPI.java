package es.unex.fulltank.datos.modelo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Clase que define la estructura de un objeto RespuestaApi
 *
 * @author Grupo PGD02
 * @version 1.0
 */

public class RespuestaAPI {
    //Se utiliza la anotación SerializedName que indica que este miembro debe serializarse
    // en JSON con el valor de nombre proporcionado como su nombre de campo.
    @SerializedName("Fecha")
    @Expose
    private String fecha;
    @SerializedName("ListaEESSPrecio")
    @Expose
    private ArrayList<GasolineraAPI> ListaGasolineras;
    @SerializedName("Nota")
    @Expose
    private String nota;
    @SerializedName("ResultadoConsulta")
    @Expose
    private String resultadoConsulta;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ArrayList<GasolineraAPI> getListaGasolineras() {
        return ListaGasolineras;
    }

    public void setListaGasolineras(ArrayList<GasolineraAPI> listaGasolineras) {
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
