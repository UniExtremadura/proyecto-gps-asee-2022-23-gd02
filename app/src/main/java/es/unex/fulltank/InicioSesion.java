package es.unex.fulltank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.roomdb.BD;


public class InicioSesion extends AppCompatActivity {
    private TextView usuario, contra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        CargaDatos c = new CargaDatos(this);
        c.Datos();

        usuario = (TextView) findViewById(R.id.username);
        contra = (TextView) findViewById(R.id.password);

    }

    public void registrarse(View view) {
        Intent i = new Intent(InicioSesion.this, Registro.class);
        startActivity(i);
    }

    public void iniciarSesion(View view) {
        BD instanceBD = BD.getInstance(this);

        AppExecutors.getInstance().diskIO().execute(() -> {

            Usuario u = (Usuario) instanceBD.getUsuarioDao().getByLogin(usuario.getText().toString(), contra.getText().toString());
            AppExecutors.getInstance().mainThread().execute(() -> {
                if (u != null) {
                    Intent i = new Intent(InicioSesion.this, MainActivity.class);
                    i.putExtra("IDENTIFICADOR", u.getUid());
                    startActivity(i);
                } else {
                }
                //incorrect
            });
        });

    }
}