package es.unex.fulltank.ui;


import static es.unex.fulltank.ui.MainActivity.identificador;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import es.unex.fulltank.AppContainer;
import es.unex.fulltank.MyApplication;
import es.unex.fulltank.R;
import es.unex.fulltank.datos.modelo.GasolineraResenha;
import es.unex.fulltank.ui.ViewModels.ResenhaViewModel;
import es.unex.fulltank.ui.resenha.ResenhaFragment;

public class ResenhaActivity extends AppCompatActivity {

    private double latitud;
    private double longitud;
    private EditText edResenha;
    private ResenhaViewModel mViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resenha);
        AppContainer appContainer = ((MyApplication) this.getApplication()).appContainer;
        mViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factoryResenha).get(ResenhaViewModel.class);


        latitud = getIntent().getExtras().getDouble("LATITUD");
        longitud = getIntent().getExtras().getDouble("LONGITUD");
        String rotulo = getIntent().getExtras().getString("ROTULO");
        String calle = getIntent().getExtras().getString("CALLE");

        TextView tv_rotulo = findViewById(R.id.resenhaNombre);
        TextView tv_calle = findViewById(R.id.resenhaDireccion);

        edResenha = findViewById(R.id.etResenha);

        tv_rotulo.setText(rotulo);
        tv_calle.setText(calle);
        mViewModel.setCoords(latitud, longitud);
        List<GasolineraResenha> lResenhas = new ArrayList<>();
        ResenhaFragment fragment = ResenhaFragment.newInstance(lResenhas);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorResenhasNuevo, fragment).commit();


    }

    public void addResenha(View view) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        String fecha = dateFormat.format(date);
        String resenha = edResenha.getText().toString();
        mViewModel.crearResenha(new GasolineraResenha(fecha, latitud, longitud, identificador, resenha));


    }


}