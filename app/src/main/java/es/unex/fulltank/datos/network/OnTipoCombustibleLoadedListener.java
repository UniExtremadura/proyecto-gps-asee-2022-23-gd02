package es.unex.fulltank.datos.network;

import java.util.List;

import es.unex.fulltank.datos.modelo.CombustibleAPI;

public interface OnTipoCombustibleLoadedListener {
    public void onTipoCombustibleLoaded(List<CombustibleAPI> TipoCombustible);
}
