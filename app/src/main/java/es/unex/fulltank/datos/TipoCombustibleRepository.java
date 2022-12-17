package es.unex.fulltank.datos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.datos.modelo.CombustibleAPI;
import es.unex.fulltank.datos.modelo.TipoCombustible;
import es.unex.fulltank.datos.network.GasolineraNetworkDataSource;
import es.unex.fulltank.datos.network.TipoCombustibleNetworkDataSource;
import es.unex.fulltank.datos.roomdb.GasolineraDao;
import es.unex.fulltank.datos.roomdb.TipoCombustibleDao;


/**
 * Handles data operations in Sunshine. Acts as a mediator between {@link GasolineraNetworkDataSource}
 * and {@link GasolineraDao}
 */
public class TipoCombustibleRepository {
    private static final String LOG_TAG = TipoCombustibleRepository.class.getSimpleName();

    // For Singleton instantiation
    private static TipoCombustibleRepository sInstance;
    private final TipoCombustibleDao mTipoCombustibleDao;
    private final MutableLiveData<List<Double>> coordFilterLiveData = new MutableLiveData<>();


    private final TipoCombustibleNetworkDataSource mTipoCombustibleNetworkDataSource;
    private final AppExecutors mExecutors = AppExecutors.getInstance();


    private TipoCombustibleRepository(TipoCombustibleDao combustibleGasolineraDao, TipoCombustibleNetworkDataSource tipoCombustibleNetworkDataSource) {
        mTipoCombustibleDao = combustibleGasolineraDao;
        mTipoCombustibleNetworkDataSource = tipoCombustibleNetworkDataSource;


        // LiveData that fetches repos from network
        LiveData<CombustibleAPI[]> networkData = mTipoCombustibleNetworkDataSource.getCurrentTipoCombustible();

        networkData.observeForever(newCombustibleAPIFromNetwork -> {
            mExecutors.diskIO().execute(() -> {
                List<TipoCombustible> lTiposCombustible = combustibleAPI2tipoCombustible(newCombustibleAPIFromNetwork);


                mTipoCombustibleDao.bulkInsert(lTiposCombustible);

                Log.d(LOG_TAG, "Nuevos valores han sido insertados en la base de datos");
            });
        });
    }

    private List<TipoCombustible> combustibleAPI2tipoCombustible(CombustibleAPI[] combustibleAPIS) {
        List<TipoCombustible> lTipos = new ArrayList<>();
        for (CombustibleAPI c : combustibleAPIS) {
            lTipos.add(c.getTipo());
        }
        return lTipos;
    }

    public synchronized static TipoCombustibleRepository getInstance(TipoCombustibleDao tipoCombustibleDao, TipoCombustibleNetworkDataSource nds) {
        Log.d(LOG_TAG, "Obteniendo TipoCombustibleRepository");
        if (sInstance == null) {
            sInstance = new TipoCombustibleRepository(tipoCombustibleDao, nds);
            Log.d(LOG_TAG, "Nueva instancia de TipoCombustibleRepository");
        }
        return sInstance;
    }

    public void getTipoCombustible() {

        AppExecutors.getInstance().diskIO().execute(() -> {
            if (isFetchNeeded()) {
                Log.d(LOG_TAG, "Ha sido necesario hacer un fetch para traer los tipos de combustible");
                mTipoCombustibleNetworkDataSource.fetchTipoCombustible();
            }
        });
    }

    public void setCoords(double latitud, double longitud) {
        List<Double> coords = new ArrayList<>();
        coords.add(latitud);
        coords.add(longitud);

        coordFilterLiveData.setValue(coords);
    }

    public LiveData<List<TipoCombustible>> getCurrentTipoCombustibleFilteredByCombustibleGasolinera() {
        return Transformations.switchMap(coordFilterLiveData, coords ->
                mTipoCombustibleDao.getFilteredByCombustibleGasolinera(coords.get(0), coords.get(1)));
    }


    //si ha pasado un dia o no hay gasolineras se necesita traer dichas gasolineras.
    private boolean isFetchNeeded() {
        boolean necesario = false;
        if (mTipoCombustibleDao.getNumeroGasolineras() == 0) {
            Log.d(LOG_TAG, "Es necesario hacer un fetch debido a que no hay datos en la base de datos de TipoCombustible");
            necesario = true;
        }
        return necesario;
    }

}