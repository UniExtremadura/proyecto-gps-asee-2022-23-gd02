package es.unex.fulltank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class GasolineraDetalleActivity extends AppCompatActivity {
    private double latitud, longitud;
    private String rotulo, calle, municipio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gasolinera_detalle);

        latitud = getIntent().getExtras().getDouble("LATITUD");
        longitud = getIntent().getExtras().getDouble("LONGITUD");
        rotulo = getIntent().getExtras().getString("ROTULO");
        calle = getIntent().getExtras().getString("CALLE");
        municipio = getIntent().getExtras().getString("MUNICIPIO");

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