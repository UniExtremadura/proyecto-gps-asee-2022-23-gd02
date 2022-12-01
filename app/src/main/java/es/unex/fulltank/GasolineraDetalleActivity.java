package es.unex.fulltank;

import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import es.unex.fulltank.bd.elembd.CombustibleGasolinera;
import es.unex.fulltank.bd.elembd.TipoCombustible;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.ui.gasolineras.InfoGasolineraFragment;


public class GasolineraDetalleActivity extends AppCompatActivity {
    private double latitud, longitud;
    private String rotulo, calle, municipio;

    private InfoGasolineraFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasolinera_detalle);

        BD instanceBD = BD.getInstance(this);

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
}