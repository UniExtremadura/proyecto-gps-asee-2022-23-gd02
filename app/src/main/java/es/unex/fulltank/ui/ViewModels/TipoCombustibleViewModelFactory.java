package es.unex.fulltank.ui.ViewModels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.fulltank.datos.GasolineraRepository;
import es.unex.fulltank.datos.TipoCombustibleRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link GasolineraRepository}
 */
public class TipoCombustibleViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final TipoCombustibleRepository mRepository;

    public TipoCombustibleViewModelFactory(TipoCombustibleRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new TipoCombustibleViewModel(mRepository);
    }
}
