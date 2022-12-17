package es.unex.fulltank.datos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import es.unex.fulltank.datos.modelo.CombustibleGasolinera;
import es.unex.fulltank.datos.network.GasolineraNetworkDataSource;
import es.unex.fulltank.datos.roomdb.CombustibleGasolineraDao;
import es.unex.fulltank.datos.roomdb.GasolineraDao;


/**
 * Handles data operations in Sunshine. Acts as a mediator between {@link GasolineraNetworkDataSource}
 * and {@link GasolineraDao}
 */
public class CombustibleGasolineraRepository {
    private static final String LOG_TAG = GasolineraRepository.class.getSimpleName();

    // For Singleton instantiation
    private static CombustibleGasolineraRepository sInstance;
    private final CombustibleGasolineraDao mCombustiblesGasolinerasDao;
    private final MutableLiveData<List<Double>> coordFilterLiveData = new MutableLiveData<>();


    private CombustibleGasolineraRepository(CombustibleGasolineraDao combustibleDao) {
        mCombustiblesGasolinerasDao = combustibleDao;
    }

    public synchronized static CombustibleGasolineraRepository getInstance(CombustibleGasolineraDao combustibleDao) {
        Log.d(LOG_TAG, "Obteniendo CombustibleGasolineraRepository");
        if (sInstance == null) {
            sInstance = new CombustibleGasolineraRepository(combustibleDao);
            Log.d(LOG_TAG, "Nueva instancia de CombustibleGasolineraRepository");
        }
        return sInstance;
    }

    public void setCoords(final double latitud, final double longitud) {
        List<Double> coords = new ArrayList<>();
        coords.add(latitud);
        coords.add(longitud);

        coordFilterLiveData.setValue(coords);
    }

    //Devuelve todos los combustibles de una gasolinera
    public LiveData<List<CombustibleGasolinera>> getCurrentCombustibleGasolineraByCoords() {
        return Transformations.switchMap(coordFilterLiveData, coords -> mCombustiblesGasolinerasDao.getByCoords(coords.get(0), coords.get(1)));
    }


}