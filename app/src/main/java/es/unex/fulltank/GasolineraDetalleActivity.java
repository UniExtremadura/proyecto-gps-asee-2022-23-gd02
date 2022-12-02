package es.unex.fulltank;

import static es.unex.fulltank.MainActivity.identificador;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.unex.fulltank.bd.elembd.HistorialRepostaje;
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