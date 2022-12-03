package es.unex.fulltank;

import static es.unex.fulltank.MainActivity.identificador;

import android.content.Intent;
import android.content.Intent;
import static es.unex.fulltank.MainActivity.identificador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.view.View;
import android.view.View;
import android.view.View;
import android.widget.Toast;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.unex.fulltank.bd.elembd.HistorialRepostaje;
import es.unex.fulltank.bd.roomdb.BD;

import java.util.ArrayList;
import java.util.List;

import es.unex.fulltank.bd.elembd.CombustibleGasolinera;
import es.unex.fulltank.bd.elembd.TipoCombustible;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.ui.gasolineras.InfoGasolineraFragment;

import es.unex.fulltank.bd.elembd.GasolineraFavorita;
import es.unex.fulltank.bd.roomdb.BD;


public class GasolineraDetalleActivity extends AppCompatActivity {
    private double latitud, longitud;
    private String rotulo, calle, municipio;

    private InfoGasolineraFragment fragment;
    private BD instanceBD;


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

        AppExecutors.getInstance().diskIO().execute(() -> {
            List<TipoCombustible> lTipoComb = instanceBD.getTipoCombustibleDao().getAll();
            List<CombustibleGasolinera> lComb = instanceBD.getCombustibleGasolineraDao().getByCoords(latitud, longitud);
            AppExecutors.getInstance().mainThread().execute(() -> {
                List<TipoCombustible> lTipoCombFiltrada = filtrarTipoCombustibles(lComb, lTipoComb);
                fragment = InfoGasolineraFragment.newInstance(rotulo, calle, municipio, lTipoCombFiltrada, lComb);
                getSupportFragmentManager().beginTransaction().add(R.id.contenedorGasolineras, fragment).commit();
            });
        });
    }

    private List<TipoCombustible> filtrarTipoCombustibles(List<CombustibleGasolinera> lComb, List<TipoCombustible> lTipoComb) {
        List<TipoCombustible> lTipoCombFiltrada = new ArrayList<>();
        for (TipoCombustible tc : lTipoComb) {
            for (CombustibleGasolinera cg : lComb) {
                if (cg.getCid() == tc.getCid()) {
                    lTipoCombFiltrada.add(tc);
                }
            }
        }
        return lTipoCombFiltrada;
    }
    public void irResenha(View view) {
        Intent intent = new Intent(GasolineraDetalleActivity.this, ResenhaActivity.class);
        intent.putExtra("LATITUD", latitud);
        intent.putExtra("LONGITUD", longitud);
        intent.putExtra("ROTULO", rotulo);
        intent.putExtra("CALLE", calle);
        startActivity(intent);
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

    public void repostar(View view) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);

        HistorialRepostaje hr = new HistorialRepostaje(fecha, latitud, longitud, identificador, 50.2, 26);

        AppExecutors.getInstance().diskIO().execute(() -> {
            instanceBD.getHistorialRepostajeDao().insert(hr);
        });

        Toast.makeText(GasolineraDetalleActivity.this, "Se ha repostado 26 litros, un total de 50,2 euros", Toast.LENGTH_SHORT).show();
    }
}