package es.unex.fulltank.datos.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.datos.modelo.CombustibleAPI;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TipoCombustibleNetworkLoaderRunnable implements Runnable {

    private final OnTipoCombustibleLoadedListener mOnTipoCombustibleLoadedListener;

    public TipoCombustibleNetworkLoaderRunnable(OnTipoCombustibleLoadedListener onTipoCombustibleLoadedListener) {
        mOnTipoCombustibleLoadedListener = onTipoCombustibleLoadedListener;
    }


    @Override
    public void run() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GasolinerasGobAPI service = retrofit.create(GasolinerasGobAPI.class);
        Call<List<CombustibleAPI>> call = service.obtenerTiposCombustible();
        try {
            Response<List<CombustibleAPI>> response = call.execute();
            List<CombustibleAPI> respuesta = response.body() == null ? new ArrayList<>() : response.body();
            AppExecutors.getInstance().mainThread().execute(() -> mOnTipoCombustibleLoadedListener.onTipoCombustibleLoaded(respuesta));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
