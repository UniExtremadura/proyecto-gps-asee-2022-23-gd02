package es.unex.fulltank.ui.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import es.unex.fulltank.datos.CombustibleGasolineraRepository;
import es.unex.fulltank.datos.GasolinerasFavoritasRepository;
import es.unex.fulltank.datos.HistoricoRepository;
import es.unex.fulltank.datos.TipoCombustibleRepository;
import es.unex.fulltank.datos.modelo.CombustibleGasolinera;
import es.unex.fulltank.datos.modelo.TipoCombustible;

/**
 * Factory method that allows us to create a ViewModel of
 * {@link GasolineraDetalleViewModel}
 */
public class GasolineraDetalleViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final CombustibleGasolineraRepository mRepositoryComb;
    private final TipoCombustibleRepository mRepositoryTipo;
    private final GasolinerasFavoritasRepository mRepositoryFav;
    private final HistoricoRepository mRepositoryHistorico;
    private final LiveData<List<TipoCombustible>> liveTipoComb;
    private final LiveData<List<CombustibleGasolinera>> liveComb;

    public GasolineraDetalleViewModelFactory(CombustibleGasolineraRepository repositoryComb, GasolinerasFavoritasRepository repositoryFav, HistoricoRepository repositoryHistorico, TipoCombustibleRepository repositoryTipo) {
        this.mRepositoryComb = repositoryComb;
        this.mRepositoryTipo = repositoryTipo;
        this.mRepositoryFav = repositoryFav;
        this.mRepositoryHistorico = repositoryHistorico;
        this.liveComb = mRepositoryComb.getCurrentCombustibleGasolineraByCoords();
        this.liveTipoComb = mRepositoryTipo.getCurrentTipoCombustibleFilteredByCombustibleGasolinera();
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new GasolineraDetalleViewModel(mRepositoryComb, mRepositoryFav, mRepositoryHistorico, mRepositoryTipo);
    }
}
