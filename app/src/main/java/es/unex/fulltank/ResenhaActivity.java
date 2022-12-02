package es.unex.fulltank;


import static es.unex.fulltank.MainActivity.identificador;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import es.unex.fulltank.bd.elembd.GasolineraResenha;
import es.unex.fulltank.bd.roomdb.BD;

public class ResenhaActivity extends AppCompatActivity {

    private double latitud;
    private double longitud;
    private EditText edResenha;
    private BD instanceBD;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resenha);

        instanceBD = BD.getInstance(this);

        latitud = getIntent().getExtras().getDouble("LATITUD");
        longitud = getIntent().getExtras().getDouble("LONGITUD");
        String rotulo = getIntent().getExtras().getString("ROTULO");
        String calle = getIntent().getExtras().getString("CALLE");

        TextView tv_rotulo = (TextView) findViewById(R.id.resenhaNombre);
        TextView tv_calle = (TextView) findViewById(R.id.resenhaDireccion);

        edResenha = (EditText) findViewById(R.id.etResenha);

        tv_rotulo.setText(rotulo);
        tv_calle.setText(calle);


    }

    public void addResenha(View view) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        String resenha = edResenha.getText().toString();
        GasolineraResenha gr = new GasolineraResenha(fecha, latitud, longitud, identificador, resenha);
        AppExecutors.getInstance().diskIO().execute(() -> {
            instanceBD.getGasolineraResenhaDao().insert(gr);
        });
    }
}