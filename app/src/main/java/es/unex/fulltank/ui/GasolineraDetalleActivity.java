package es.unex.fulltank.ui;

import static es.unex.fulltank.ui.MainActivity.identificador;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import es.unex.fulltank.AppContainer;
import es.unex.fulltank.MyApplication;
import es.unex.fulltank.R;
import es.unex.fulltank.ui.ViewModels.GasolineraDetalleViewModel;
import es.unex.fulltank.ui.gasolineras.InfoGasolineraFragment;


public class GasolineraDetalleActivity extends AppCompatActivity {
    private double latitud;
    private double longitud;
    private String rotulo;
    private String calle;
    private String municipio;
    private InfoGasolineraFragment fragment;
    private GasolineraDetalleViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasolinera_detalle);


        latitud = getIntent().getExtras().getDouble("LATITUD");
        longitud = getIntent().getExtras().getDouble("LONGITUD");
        rotulo = getIntent().getExtras().getString("ROTULO");
        calle = getIntent().getExtras().getString("CALLE");
        municipio = getIntent().getExtras().getString("MUNICIPIO");


        AppContainer appContainer = ((MyApplication) getApplication()).appContainer;
        mViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factoryGasolineraDetalle).get(GasolineraDetalleViewModel.class);
        mViewModel.getCurrentTipoCombustibleFilteredByCombustibleGasolinera(latitud, longitud).observe(this, lTipoComb -> {
            mViewModel.getCurrentCombustibleGasolineraByCoords(latitud, longitud).observe(this, lComb -> {
                fragment = InfoGasolineraFragment.newInstance(rotulo, calle, municipio, lTipoComb, lComb);
                getSupportFragmentManager().beginTransaction().add(R.id.contenedorGasolineras, fragment).commit();
            });
        });
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

        mViewModel.accionFavorito(latitud, longitud, identificador);

        Toast.makeText(GasolineraDetalleActivity.this, "Se ha añadido/eliminado la gasolinera a favoritos", Toast.LENGTH_SHORT).show();


    }

    public void repostar(View view) {

        Toast.makeText(GasolineraDetalleActivity.this, "Se ha repostado 26 litros, un total de 50,2 euros", Toast.LENGTH_SHORT).show();
        mViewModel.accionRepostar(latitud, longitud, identificador);

    }
}