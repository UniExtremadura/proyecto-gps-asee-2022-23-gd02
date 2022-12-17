package es.unex.fulltank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import es.unex.fulltank.AppContainer;
import es.unex.fulltank.MyApplication;
import es.unex.fulltank.R;
import es.unex.fulltank.ui.ViewModels.TipoCombustibleViewModel;
import es.unex.fulltank.ui.ViewModels.UsuarioViewModel;


public class InicioSesion extends AppCompatActivity {
    private TextView usuario;
    private TextView contra;
    private UsuarioViewModel mViewModelUser;
    private TipoCombustibleViewModel mViewModelTipoComb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        AppContainer appContainer = ((MyApplication) this.getApplication()).appContainer;
        mViewModelUser = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.usuarioViewModelFactory).get(UsuarioViewModel.class);
        mViewModelTipoComb = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.tipoCombustibleViewModelFactory).get(TipoCombustibleViewModel.class);

        mViewModelTipoComb.getTipoCombustible();


        usuario = findViewById(R.id.username);
        contra = findViewById(R.id.password);


    }

    public void registrarse(View view) {
        Intent i = new Intent(InicioSesion.this, Registro.class);
        startActivity(i);
    }

    public void iniciarSesion(View view) {
        mViewModelUser.setLogin(usuario.getText().toString(), contra.getText().toString());
        mViewModelUser.getCurrentUsuario().observe(this, user -> {
            if (user != null) {
                Intent i = new Intent(InicioSesion.this, MainActivity.class);
                i.putExtra("IDENTIFICADOR", user.getUid());
                startActivity(i);
            }
        });

    }

    public void saltar(View view) {
        mViewModelUser.setLogin("admin", "admin");
        mViewModelUser.getCurrentUsuario().observe(this, user -> {
            if (user != null) {
                Intent i = new Intent(InicioSesion.this, MainActivity.class);
                i.putExtra("IDENTIFICADOR", user.getUid());
                startActivity(i);
            }
        });
    }
}