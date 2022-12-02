package es.unex.fulltank;

import static es.unex.fulltank.MainActivity.identificador;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.unex.fulltank.bd.elembd.GasolineraFavorita;
import es.unex.fulltank.bd.roomdb.BD;


public class GasolineraDetalleActivity extends AppCompatActivity {
    private BD instanceBD;
    private double latitud, longitud;
    private String rotulo, calle, municipio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasolinera_detalle);

        instanceBD = BD.getInstance(this);

        latitud = getIntent().getExtras().getDouble("LATITUD");
        longitud = getIntent().getExtras().getDouble("LONGITUD");
        rotulo = getIntent().getExtras().getString("ROTULO");
        calle = getIntent().getExtras().getString("CALLE");
        municipio = getIntent().getExtras().getString("MUNICIPIO");
    }

    public void favoritos(View view) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            if (instanceBD.getGasolineraFavoritaDao().getByPrimaryKey(latitud, longitud, identificador) == null) {
                GasolineraFavorita gf = new GasolineraFavorita(latitud, longitud, identificador);
                instanceBD.getGasolineraFavoritaDao().insert(gf);
                AppExecutors.getInstance().mainThread().execute(() -> {
                    Toast.makeText(GasolineraDetalleActivity.this, "Se ha añadido la gasolinera a favoritos", Toast.LENGTH_SHORT).show();
                });
            } else {
                instanceBD.getGasolineraFavoritaDao().deleteByPrimaryKey(latitud, longitud, identificador);
                AppExecutors.getInstance().mainThread().execute(() -> {
                    Toast.makeText(GasolineraDetalleActivity.this, "Se ha eliminado la gasolinera de favoritos", Toast.LENGTH_SHORT).show();
                });
            }
        });
    }
}