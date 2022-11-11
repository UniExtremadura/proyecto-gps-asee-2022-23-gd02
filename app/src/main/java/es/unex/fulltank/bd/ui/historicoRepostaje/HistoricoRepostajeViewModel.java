package es.unex.fulltank.bd.ui.historicoRepostaje;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoricoRepostajeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HistoricoRepostajeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Historico de Repostaje fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}