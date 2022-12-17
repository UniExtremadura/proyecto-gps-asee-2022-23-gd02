package es.unex.fulltank.datos;

import android.util.Log;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.datos.modelo.GasolineraFavorita;
import es.unex.fulltank.datos.network.GasolineraNetworkDataSource;
import es.unex.fulltank.datos.roomdb.GasolineraDao;
import es.unex.fulltank.datos.roomdb.GasolineraFavoritaDao;


/**
 * Handles data operations in Sunshine. Acts as a mediator between {@link GasolineraNetworkDataSource}
 * and {@link GasolineraDao}
 */
public class GasolinerasFavoritasRepository {
    private static final String LOG_TAG = GasolinerasFavoritasRepository.class.getSimpleName();

    // For Singleton instantiation
    private static GasolinerasFavoritasRepository sInstance;
    private final GasolineraFavoritaDao mGasolineraFavoritaDao;
    private final AppExecutors mExecutors = AppExecutors.getInstance();


    private GasolinerasFavoritasRepository(GasolineraFavoritaDao gasolineraFavoritaDao) {
        mGasolineraFavoritaDao = gasolineraFavoritaDao;

    }


    public synchronized static GasolinerasFavoritasRepository getInstance(GasolineraFavoritaDao gasolineraFavoritaDao) {
        Log.d(LOG_TAG, "Obteniendo GasolinerasFavoritasRepository");
        if (sInstance == null) {
            sInstance = new GasolinerasFavoritasRepository(gasolineraFavoritaDao);
            Log.d(LOG_TAG, "Nueva instancia de GasolinerasFavoritasRepository");
        }
        return sInstance;
    }


    public void accionFavorito(double latitud, double longitud, int identificador) {

        mExecutors.diskIO().execute(() -> {

            if (mGasolineraFavoritaDao.getByPrimaryKey(latitud, longitud, identificador) == null) {
                GasolineraFavorita gf = new GasolineraFavorita(latitud, longitud, identificador);
                mGasolineraFavoritaDao.insert(gf);


            } else {

                mGasolineraFavoritaDao.deleteByPrimaryKey(latitud, longitud, identificador);

            }
        });

    }

}