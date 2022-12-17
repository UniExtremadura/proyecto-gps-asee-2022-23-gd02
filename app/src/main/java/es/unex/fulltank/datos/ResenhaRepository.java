package es.unex.fulltank.datos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.datos.modelo.GasolineraResenha;
import es.unex.fulltank.datos.network.GasolineraNetworkDataSource;
import es.unex.fulltank.datos.roomdb.GasolineraDao;
import es.unex.fulltank.datos.roomdb.GasolineraResenhaDao;


/**
 * Handles data operations in Sunshine. Acts as a mediator between {@link GasolineraNetworkDataSource}
 * and {@link GasolineraDao}
 */
public class ResenhaRepository {
    private static final String LOG_TAG = ResenhaRepository.class.getSimpleName();
    private final MutableLiveData<List<Double>> coordFilterLiveData = new MutableLiveData<>();
    // For Singleton instantiation
    private static ResenhaRepository sInstance;
    private final GasolineraResenhaDao mGasolineraResenhaDao;

    private final AppExecutors mExecutors = AppExecutors.getInstance();


    private ResenhaRepository(GasolineraResenhaDao gasolineraResenhaDao) {
        mGasolineraResenhaDao = gasolineraResenhaDao;

    }

    public synchronized static ResenhaRepository getInstance(GasolineraResenhaDao gasolineraResenhaDao) {
        Log.d(LOG_TAG, "Obteniendo ResenhaRepository");
        if (sInstance == null) {
            sInstance = new ResenhaRepository(gasolineraResenhaDao);
            Log.d(LOG_TAG, "Nueva instancia de ResenhaRepository");
        }
        return sInstance;
    }

    public void setCoords(final double latitud, final double longitud) {
        List<Double> ld = new ArrayList<>();
        ld.add(latitud);
        ld.add(longitud);
        coordFilterLiveData.setValue(ld);
    }

    public LiveData<List<GasolineraResenha>> getCurrentResenha() {
        return Transformations.switchMap(coordFilterLiveData, coords -> mGasolineraResenhaDao.getByCoords(coords.get(0), coords.get(1)));
    }

    public void crearResenha(GasolineraResenha resenha) {
        mExecutors.diskIO().execute(() -> mGasolineraResenhaDao.insert(resenha));

    }

}