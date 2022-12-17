package es.unex.fulltank.datos.network;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.datos.modelo.GasolineraAPI;
import es.unex.fulltank.datos.modelo.RespuestaAPI;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GasolinerasNetworkLoaderRunnable implements Runnable {

    private final OnGasolinerasLoadedListener mOnGasolinerasLoadedListener; //Este lisener va a ser llamado cuando las gasolineras sean cargadas

    public GasolinerasNetworkLoaderRunnable(OnGasolinerasLoadedListener onGasolinerasLoadedListener) {
        mOnGasolinerasLoadedListener = onGasolinerasLoadedListener;
    }


    @Override
    public void run() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GasolinerasGobAPI service = retrofit.create(GasolinerasGobAPI.class);
        Call<RespuestaAPI> call = service.obtenerGasolineras();
        try {
            Response<RespuestaAPI> response = call.execute();
            RespuestaAPI respuesta = response.body() == null ? new RespuestaAPI() : response.body();
            List<GasolineraAPI> lGasolinerasAPI = respuesta.getListaGasolineras() == null ? new ArrayList<>() : respuesta.getListaGasolineras();

            AppExecutors.getInstance().mainThread().execute(() -> mOnGasolinerasLoadedListener.onGasolinerasLoaded(lGasolinerasAPI));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
