package es.unex.fulltank.datos;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.ArrayList;
import java.util.List;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.datos.modelo.Usuario;
import es.unex.fulltank.datos.network.GasolineraNetworkDataSource;
import es.unex.fulltank.datos.roomdb.GasolineraDao;
import es.unex.fulltank.datos.roomdb.UsuarioDao;


/**
 * Handles data operations in Sunshine. Acts as a mediator between {@link GasolineraNetworkDataSource}
 * and {@link GasolineraDao}
 */
public class UsuarioRepository {
    private static final String LOG_TAG = UsuarioRepository.class.getSimpleName();

    // For Singleton instantiation
    private static UsuarioRepository sInstance;
    private final UsuarioDao mUsuarioDao;
    private final AppExecutors mExecutors = AppExecutors.getInstance();
    private final MutableLiveData<List<String>> loginFilterLiveData = new MutableLiveData<>();


    private UsuarioRepository(UsuarioDao usuarioDao) {
        mUsuarioDao = usuarioDao;

        mExecutors.diskIO().execute(() -> {
            if (mUsuarioDao.getByUsuario("admin") == null) {
                mUsuarioDao.insert(new Usuario("admin", "admin", "admin"));
            }
        });


    }


    public synchronized static UsuarioRepository getInstance(UsuarioDao usuarioDao) {
        Log.d(LOG_TAG, "Obteniendo UsuarioRepository");
        if (sInstance == null) {
            sInstance = new UsuarioRepository(usuarioDao);
            Log.d(LOG_TAG, "Nueva instancia de UsuarioRepository");
        }
        return sInstance;
    }


    public void setLogin(String usuario, String contra) {
        List<String> login = new ArrayList<>();
        login.add(usuario);
        login.add(contra);
        loginFilterLiveData.setValue(login);
    }

    //Devuelve todas las gasolineras
    public LiveData<Usuario> getCurrentUsuario() {
        return Transformations.switchMap(loginFilterLiveData, login -> mUsuarioDao.getByLogin(login.get(0), login.get(1)));
    }


    public void registrar(Usuario usr) {
        mExecutors.diskIO().execute(() -> mUsuarioDao.insert(usr));
    }


}