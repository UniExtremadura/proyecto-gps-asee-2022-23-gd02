package es.unex.fulltank.datos.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.datos.modelo.CombustibleAPI;


public class TipoCombustibleNetworkDataSource {
    private static final String LOG_TAG = TipoCombustibleNetworkDataSource.class.getSimpleName();
    private static TipoCombustibleNetworkDataSource sInstance;

    // LiveData storing the latest downloaded weather forecasts
    private final MutableLiveData<CombustibleAPI[]> mDownloadedTipoCombustible;

    private TipoCombustibleNetworkDataSource() {
        mDownloadedTipoCombustible = new MutableLiveData<>();
    }


    public synchronized static TipoCombustibleNetworkDataSource getInstance() {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            sInstance = new TipoCombustibleNetworkDataSource();
            Log.d(LOG_TAG, "Made new network data source");
        }
        return sInstance;
    }

    //Devolvemos al reposito un LiveData
    public LiveData<CombustibleAPI[]> getCurrentTipoCombustible() {
        return mDownloadedTipoCombustible;
    }

    /**
     * Método que permite obtener las gasolineras
     */
    public void fetchTipoCombustible() {
        Log.d(LOG_TAG, "La carga de las tipos de combustible a comenzado");
        // Get gata from network and pass it to LiveData
        AppExecutors.getInstance().networkIO().execute(new TipoCombustibleNetworkLoaderRunnable(gasoline -> mDownloadedTipoCombustible.postValue(gasoline.toArray(new CombustibleAPI[0]))));
    }

}
