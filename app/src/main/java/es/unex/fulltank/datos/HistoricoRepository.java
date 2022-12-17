package es.unex.fulltank.datos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.datos.modelo.HistorialRepostaje;
import es.unex.fulltank.datos.network.GasolineraNetworkDataSource;
import es.unex.fulltank.datos.roomdb.GasolineraDao;
import es.unex.fulltank.datos.roomdb.HistorialRepostajeDao;


/**
 * Handles data operations in Sunshine. Acts as a mediator between {@link GasolineraNetworkDataSource}
 * and {@link GasolineraDao}
 */
public class HistoricoRepository {
    private static final String LOG_TAG = HistoricoRepository.class.getSimpleName();
    private final MutableLiveData<Integer> iduFilterLiveData = new MutableLiveData<>();
    // For Singleton instantiation
    private static HistoricoRepository sInstance;
    private final HistorialRepostajeDao mHistorialRepostajeDao;

    private final AppExecutors mExecutors = AppExecutors.getInstance();


    private HistoricoRepository(HistorialRepostajeDao historialRepostajeDao) {
        mHistorialRepostajeDao = historialRepostajeDao;

    }

    public synchronized static HistoricoRepository getInstance(HistorialRepostajeDao historialRepostajeDao) {
        Log.d(LOG_TAG, "Obteniendo HistoricoRepository");
        if (sInstance == null) {
            sInstance = new HistoricoRepository(historialRepostajeDao);
            Log.d(LOG_TAG, "Nueva instancia de HistoricoRepository");
        }
        return sInstance;
    }

    public void setIdu(final int idu) {
        iduFilterLiveData.setValue(idu);
    }

    public LiveData<List<HistorialRepostaje>> getCurrentHistorial() {
        return Transformations.switchMap(iduFilterLiveData, id -> mHistorialRepostajeDao.getById(id));
    }

    public void accionRepostar(double latitud, double longitud, int identificador) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);

        HistorialRepostaje hr = new HistorialRepostaje(fecha, latitud, longitud, identificador, 50.2, 26);

        mExecutors.diskIO().execute(() -> {
            mHistorialRepostajeDao.insert(hr);
        });
    }
}