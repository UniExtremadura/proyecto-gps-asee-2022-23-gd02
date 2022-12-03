package es.unex.fulltank;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import es.unex.fulltank.bd.elembd.GasolineraResenha;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.ui.resenha.ResenhaFragment;

public class ResenhaActivity extends AppCompatActivity {

    private double latitud;
    private double longitud;
    private ResenhaFragment fragment;

    private BD instanceBD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resenha);

        latitud = getIntent().getExtras().getDouble("LATITUD");
        longitud = getIntent().getExtras().getDouble("LONGITUD");
        String rotulo = getIntent().getExtras().getString("ROTULO");
        String calle = getIntent().getExtras().getString("CALLE");

        TextView tv_rotulo = (TextView) findViewById(R.id.resenhaNombre);
        TextView tv_calle = (TextView) findViewById(R.id.resenhaDireccion);

        tv_rotulo.setText(rotulo);
        tv_calle.setText(calle);

        cargarResenhaFragment();

    }

    private void cargarResenhaFragment() {
        instanceBD = BD.getInstance(this);
        AppExecutors.getInstance().diskIO().execute(() -> {
            List<GasolineraResenha> lResenha = instanceBD.getGasolineraResenhaDao().getByCoords(latitud, longitud);
            AppExecutors.getInstance().mainThread().execute(() -> {
                fragment = ResenhaFragment.newInstance(lResenha);
                getSupportFragmentManager().beginTransaction().replace(R.id.contenedorResenhasNuevo, fragment).commit();
            });
        });
    }

}