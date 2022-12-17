package es.unex.fulltank.ui.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.fulltank.datos.CombustibleGasolineraRepository;
import es.unex.fulltank.datos.GasolinerasFavoritasRepository;
import es.unex.fulltank.datos.HistoricoRepository;
import es.unex.fulltank.datos.TipoCombustibleRepository;
import es.unex.fulltank.datos.modelo.CombustibleGasolinera;
import es.unex.fulltank.datos.modelo.TipoCombustible;

/**
 * {@link ViewModel} for {@link es.unex.fulltank.ui.GasolineraDetalleActivity}
 */
public class GasolineraDetalleViewModel extends ViewModel {
    private final CombustibleGasolineraRepository mRepositoryComb;
    private final TipoCombustibleRepository mRepositoryTipo;
    private final GasolinerasFavoritasRepository mRepositoryFav;
    private final HistoricoRepository mRepositoryHistorico;
    private final LiveData<List<TipoCombustible>> liveTipoComb;
    private final LiveData<List<CombustibleGasolinera>> liveComb;

    public GasolineraDetalleViewModel(CombustibleGasolineraRepository repositoryComb, GasolinerasFavoritasRepository repositoryFav, HistoricoRepository repositoryHistorico, TipoCombustibleRepository repositoryTipo) {
        this.mRepositoryComb = repositoryComb;
        this.mRepositoryTipo = repositoryTipo;
        this.mRepositoryFav = repositoryFav;
        this.mRepositoryHistorico = repositoryHistorico;
        this.liveComb = mRepositoryComb.getCurrentCombustibleGasolineraByCoords();
        this.liveTipoComb = mRepositoryTipo.getCurrentTipoCombustibleFilteredByCombustibleGasolinera();
    }

    public LiveData<List<CombustibleGasolinera>> getCurrentCombustibleGasolineraByCoords(double latitud, double longitud) {
        mRepositoryComb.setCoords(latitud, longitud);
        return liveComb;
    }

    public LiveData<List<TipoCombustible>> getCurrentTipoCombustibleFilteredByCombustibleGasolinera(double latitud, double longitud) {
        mRepositoryTipo.setCoords(latitud, longitud);
        return liveTipoComb;
    }

    public void accionFavorito(double latitud, double longitud, int identificador) {
        mRepositoryFav.accionFavorito(latitud, longitud, identificador);
    }

    public void accionRepostar(double latitud, double longitud, int identificador) {
        mRepositoryHistorico.accionRepostar(latitud, longitud, identificador);
    }

}
