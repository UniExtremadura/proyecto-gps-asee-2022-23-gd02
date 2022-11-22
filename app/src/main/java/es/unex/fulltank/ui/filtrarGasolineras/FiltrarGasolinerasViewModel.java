package es.unex.fulltank.ui.filtrarGasolineras;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FiltrarGasolinerasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public FiltrarGasolinerasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Filtrar Gasolineras fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}