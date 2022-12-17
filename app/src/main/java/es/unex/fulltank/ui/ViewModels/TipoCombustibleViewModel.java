package es.unex.fulltank.ui.ViewModels;

import androidx.lifecycle.ViewModel;

import es.unex.fulltank.datos.TipoCombustibleRepository;
import es.unex.fulltank.ui.gasolineras.GasolinerasFragment;

/**
 * {@link ViewModel} for {@link GasolinerasFragment}
 */
public class TipoCombustibleViewModel extends ViewModel {
    private final TipoCombustibleRepository mRepository;



    public TipoCombustibleViewModel(TipoCombustibleRepository repository) {
        this.mRepository = repository;
    }

    public  void getTipoCombustible(){
        mRepository.getTipoCombustible();
    }


}
