package es.unex.fulltank.datos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.datos.modelo.CombustibleGasolinera;
import es.unex.fulltank.datos.modelo.Gasolinera;
import es.unex.fulltank.datos.modelo.GasolineraAPI;
import es.unex.fulltank.datos.network.GasolineraNetworkDataSource;
import es.unex.fulltank.datos.roomdb.CombustibleGasolineraDao;
import es.unex.fulltank.datos.roomdb.GasolineraDao;


/**
 * Handles data operations in Sunshine. Acts as a mediator between {@link GasolineraNetworkDataSource}
 * and {@link GasolineraDao}
 */
public class GasolineraRepository {
    private static final String LOG_TAG = GasolineraRepository.class.getSimpleName();

    // For Singleton instantiation
    private static GasolineraRepository sInstance;
    private final GasolineraDao mGasolineraDao;
    private final CombustibleGasolineraDao mCombustiblesGasolinerasDao;
    private final GasolineraNetworkDataSource mGasolineraNetworkDataSource;
    private final AppExecutors mExecutors = AppExecutors.getInstance();
    private final MutableLiveData<List<Double>> coordFilterLiveData = new MutableLiveData<>();
    private final MutableLiveData<Integer> iduFilterLiveData = new MutableLiveData<>();
    private final LiveData<List<Gasolinera>> mLDgasolineras;
    private final LiveData<List<CombustibleGasolinera>> mLDcombustibleGasolinera;
    private final MutableLiveData<List<String>> cidFilterLiveData = new MutableLiveData<>();


    private GasolineraRepository(GasolineraDao gasolineraDao, CombustibleGasolineraDao combustibleGasolineraDao, GasolineraNetworkDataSource gasolineraNetworkDataSource) {


        mGasolineraDao = gasolineraDao;
        mCombustiblesGasolinerasDao = combustibleGasolineraDao;
        mGasolineraNetworkDataSource = gasolineraNetworkDataSource;


        // LiveData that fetches repos from network
        LiveData<GasolineraAPI[]> networkData = mGasolineraNetworkDataSource.getCurrentGasolineras();

        networkData.observeForever(newGasolinerasAPIFromNetwork -> {
            mExecutors.diskIO().execute(() -> {
                List<Gasolinera> lGasolineras = gasolineraAPI2gasolinera(newGasolinerasAPIFromNetwork);


                mGasolineraDao.bulkInsert(lGasolineras);

                List<CombustibleGasolinera> lCombustiblesGasolineras = gasolineraAPI2combustible(newGasolinerasAPIFromNetwork);

                mCombustiblesGasolinerasDao.bulkInsert(lCombustiblesGasolineras);

                Log.d(LOG_TAG, "Nuevos valores han sido insertados en la base de datos");
            });
        });

        mLDgasolineras = mGasolineraDao.getAll();
        mLDcombustibleGasolinera = mCombustiblesGasolinerasDao.getAll();
    }

    private List<Gasolinera> gasolineraAPI2gasolinera(GasolineraAPI[] gasolinerasApi) {
        Log.d(LOG_TAG, "Se estan transformando los objetos de gasolineraAPI a gasolinera");

        List<Gasolinera> lGasolineras = new ArrayList<>();
        for (GasolineraAPI gApi : gasolinerasApi) {
            lGasolineras.add(gApi.obtenerGasolinera());
        }
        return lGasolineras;
    }


    private List<CombustibleGasolinera> gasolineraAPI2combustible(GasolineraAPI[] gasolinerasApi) {
        Log.d(LOG_TAG, "Se estan transformando los objetos de gasolineraAPI a combustibleGasolinera");
        List<CombustibleGasolinera> lCombustible = new ArrayList<>();
        for (GasolineraAPI gApi : gasolinerasApi) {
            List<CombustibleGasolinera> lCG = gApi.obtenerCombustibles();
            if (lCG != null)
                lCombustible.addAll(lCG);
        }
        return lCombustible;
    }

    public synchronized static GasolineraRepository getInstance(GasolineraDao gasolineraDao, CombustibleGasolineraDao combustibleGasolineraDao, GasolineraNetworkDataSource nds) {
        Log.d(LOG_TAG, "Obteniendo las gasolineras");
        if (sInstance == null) {
            sInstance = new GasolineraRepository(gasolineraDao, combustibleGasolineraDao, nds);
            Log.d(LOG_TAG, "Nueva instancia de GasolineraRepository");
        }
        return sInstance;
    }

    public void setIdu(final int idu) {
        iduFilterLiveData.setValue(idu);
    }


    public void setComb(final int cid, final double latitud, final double longitud) {
        List<String> lt = new ArrayList<>();
        lt.add(String.valueOf(cid));
        lt.add(String.valueOf(latitud));
        lt.add(String.valueOf(longitud));

        cidFilterLiveData.setValue(lt);
        AppExecutors.getInstance().diskIO().execute(() -> {
            if (isFetchNeeded()) {
                Log.d(LOG_TAG, "Ha sido necesario hacer un fetch");
                doFetchGasolineras();
            }
        });
    }

    public void setCoords(final double latitud, final double longitud) {
        List<Double> ld = new ArrayList<>();
        ld.add(latitud);
        ld.add(longitud);

        coordFilterLiveData.setValue(ld);

        AppExecutors.getInstance().diskIO().execute(() -> {
            if (isFetchNeeded()) {
                Log.d(LOG_TAG, "Ha sido necesario hacer un fetch");
                doFetchGasolineras();
            }
        });
    }

    public void doFetchGasolineras() {
        Log.d(LOG_TAG, "Obteniendo todas las gasolineras");
        AppExecutors.getInstance().diskIO().execute(mGasolineraNetworkDataSource::fetchGasolineras);
    }

    /**
     * Database related operations
     **/

    //Devuelve todas las gasolineras
    public LiveData<List<Gasolinera>> getCurrentGasolinera() {
        return mLDgasolineras;//Devuelve todas las gasolineras
    }


    public LiveData<List<Gasolinera>> getCurrentGasolinerasFiltradasFav() {
        return Transformations.switchMap(iduFilterLiveData, mGasolineraDao::getGasolinerasFavoritas);
    }

    public LiveData<List<Gasolinera>> getCurrentGasolinerasFiltradasCoords() {
        return Transformations.switchMap(coordFilterLiveData, coords -> mGasolineraDao.getFilterGasolinerasByCoords(coords.get(0), coords.get(1)));
    }

    public LiveData<List<Gasolinera>> getCurrentGasolinerasFiltradasCom() {
        return Transformations.switchMap(cidFilterLiveData, filt -> mGasolineraDao.getGasolinerasCombustible(Integer.parseInt(filt.get(0)), Double.parseDouble(filt.get(1)), Double.parseDouble(filt.get(2))));
    }

    //si ha pasado un dia o no hay gasolineras se necesita traer dichas gasolineras.
    private boolean isFetchNeeded() {
        boolean necesario = false;
        if (mGasolineraDao.getNumeroGasolineras() == 0) {
            Log.d(LOG_TAG, "Es necesario hacer un fetch debido a que no hay datos en la base de datos");
            necesario = true;
        }
        return necesario;
    }


}