package es.unex.fulltank.ui.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.fulltank.datos.HistoricoRepository;
import es.unex.fulltank.datos.modelo.HistorialRepostaje;
import es.unex.fulltank.ui.gasolineras.GasolinerasFragment;

/**
 * {@link ViewModel} for {@link GasolinerasFragment}
 */
public class HistoricoViewModel extends ViewModel {
    private final HistoricoRepository mRepository;
    private final LiveData<List<HistorialRepostaje>> mHistorial;

    public HistoricoViewModel(HistoricoRepository repository) {
        this.mRepository = repository;
        this.mHistorial = mRepository.getCurrentHistorial();
    }

    public LiveData<List<HistorialRepostaje>> getCurrentHistorial() {
        return mHistorial;
    }

    public void setIdu(int identificador){
        mRepository.setIdu(identificador);
    }
}
