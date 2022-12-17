package es.unex.fulltank.ui.ViewModels;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.unex.fulltank.datos.GasolineraRepository;
import es.unex.fulltank.datos.ResenhaRepository;

/**
 * Factory method that allows us to create a ViewModel with a constructor that takes a
 * {@link GasolineraRepository}
 */
public class ResenhaViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private final ResenhaRepository mRepository;

    public ResenhaViewModelFactory(ResenhaRepository repository) {
        this.mRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        //noinspection unchecked
        return (T) new ResenhaViewModel(mRepository);
    }
}
