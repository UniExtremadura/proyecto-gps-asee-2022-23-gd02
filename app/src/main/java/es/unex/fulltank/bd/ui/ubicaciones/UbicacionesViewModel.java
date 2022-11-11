package es.unex.fulltank.bd.ui.ubicaciones;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UbicacionesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public UbicacionesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Mis Ubicaciones fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}