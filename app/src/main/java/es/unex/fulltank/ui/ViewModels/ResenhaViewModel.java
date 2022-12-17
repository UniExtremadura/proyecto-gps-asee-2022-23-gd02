package es.unex.fulltank.ui.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.fulltank.datos.ResenhaRepository;
import es.unex.fulltank.datos.modelo.GasolineraResenha;
import es.unex.fulltank.ui.gasolineras.GasolinerasFragment;

/**
 * {@link ViewModel} for {@link GasolinerasFragment}
 */
public class ResenhaViewModel extends ViewModel {
    private final ResenhaRepository mRepository;
    private final LiveData<List<GasolineraResenha>> mHistorial;

    public ResenhaViewModel(ResenhaRepository repository) {
        this.mRepository = repository;
        this.mHistorial = mRepository.getCurrentResenha();
    }

    public LiveData<List<GasolineraResenha>> getCurrentResenha() {
        return mHistorial;
    }

    public void setCoords(double latitud, double longitud){
        mRepository.setCoords(latitud, longitud);
    }

    public void crearResenha(GasolineraResenha resenha){
        mRepository.crearResenha(resenha);
    }

}
