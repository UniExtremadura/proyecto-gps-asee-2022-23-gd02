package es.unex.fulltank;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import es.unex.fulltank.apiRest.interfaces.GasolineraAPI;
import es.unex.fulltank.apiRest.modelos.Combustibles;
import es.unex.fulltank.apiRest.modelos.RespuestaAPI;
import es.unex.fulltank.bd.elembd.TipoCombustible;
import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.UsuarioDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CargaDatos {
    private final Context context;

    public CargaDatos(Context context){
        this.context = context;
    }

    /**
     * Permite cargar datos al inicio de la aplicación
     */
    public void Datos(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        BD instanceBD = BD.getInstance(context);
        GasolineraAPI service = retrofit.create(GasolineraAPI.class);
        Call<ArrayList<Combustibles>> respuesta = service.obtenerTiposCombustible();
        respuesta.enqueue(new Callback<ArrayList<Combustibles>>() {
            @Override
            public void onResponse(Call<ArrayList<Combustibles>> call, Response<ArrayList<Combustibles>> response) {
                if(response.isSuccessful()){

                    ArrayList<Combustibles> respuesta1 = response.body();

                    for(Combustibles c:respuesta1){
                        TipoCombustible tp = new TipoCombustible(Integer.parseInt(c.getIDProducto()),c.getNombreProducto());
                        AppExecutors.getInstance().diskIO().execute(() -> {
                            instanceBD.getTipoCombustibleDao().insert(tp);
                        });
                    }

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Combustibles>> call, Throwable t) {

            }
        });
        Usuario u1 = new Usuario("admin","admin@unex.es","admin");
        UsuarioDao uDao = BD.getInstance(context).getUsuarioDao();

            AppExecutors.getInstance().diskIO().execute(() -> {
                if (uDao.getByUsuario(u1.getUsuario()) == null) {
                    instanceBD.getUsuarioDao().insert(u1);
                }
            });

    }
}
