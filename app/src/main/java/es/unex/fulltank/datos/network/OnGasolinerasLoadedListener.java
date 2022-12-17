package es.unex.fulltank.datos.network;

import java.util.List;

import es.unex.fulltank.datos.modelo.GasolineraAPI;

public interface OnGasolinerasLoadedListener {
    public void onGasolinerasLoaded(List<GasolineraAPI> gasolineras);
}
