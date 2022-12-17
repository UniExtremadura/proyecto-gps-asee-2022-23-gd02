package es.unex.fulltank.datos.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.datos.modelo.GasolineraAPI;


public class GasolineraNetworkDataSource {
    private static final String LOG_TAG = GasolineraNetworkDataSource.class.getSimpleName();
    private static GasolineraNetworkDataSource sInstance;

    // LiveData storing the latest downloaded weather forecasts
    private final MutableLiveData<GasolineraAPI[]> mDownloadedGasolineras; //Contiene las ultimas gasolineras obtenidas

    private GasolineraNetworkDataSource() {
        mDownloadedGasolineras = new MutableLiveData<>();
    }


    public synchronized static GasolineraNetworkDataSource getInstance() {
        Log.d(LOG_TAG, "Getting the network data source");
        if (sInstance == null) {
            sInstance = new GasolineraNetworkDataSource();
            Log.d(LOG_TAG, "Made new network data source");
        }
        return sInstance;
    }

    //Devolvemos al reposito un LiveData
    public LiveData<GasolineraAPI[]> getCurrentGasolineras() {
        return mDownloadedGasolineras;
    }

    /**
     * Método que permite obtener las gasolineras
     */
    public void fetchGasolineras() {
        Log.d(LOG_TAG, "La carga de las gasolineras a comenzado");
        // Get gata from network and pass it to LiveData
        AppExecutors.getInstance().networkIO().execute(new GasolinerasNetworkLoaderRunnable(gasoline -> mDownloadedGasolineras.postValue(gasoline.toArray(new GasolineraAPI[0]))));
    }

}
