package es.unex.fulltank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import es.unex.fulltank.AppContainer;
import es.unex.fulltank.MyApplication;
import es.unex.fulltank.R;
import es.unex.fulltank.datos.modelo.Usuario;
import es.unex.fulltank.ui.ViewModels.UsuarioViewModel;

public class Registro extends AppCompatActivity {
    private EditText mUsername;
    private EditText mCorreo;
    private EditText mContra1;
    private EditText mContra2;
    private UsuarioViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mUsername = findViewById(R.id.username);
        mCorreo = findViewById(R.id.email);
        mContra1 = findViewById(R.id.password);
        mContra2 = findViewById(R.id.repassword);
        AppContainer appContainer = ((MyApplication) this.getApplication()).appContainer;
        mViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.usuarioViewModelFactory).get(UsuarioViewModel.class);


    }

    public void registro(View view) {
        if (!mContra1.getText().toString().equals(mContra2.getText().toString())) {
            Toast.makeText(Registro.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else {

            String usuario = mUsername.getText().toString();
            String correo = mCorreo.getText().toString();
            String contra = mContra1.getText().toString();

            mViewModel.registrar(new Usuario(usuario, correo, contra));
            mViewModel.setLogin(usuario, contra);
            mViewModel.getCurrentUsuario().observe(this, user -> {
                if (user != null) {
                    Intent i = new Intent(Registro.this, MainActivity.class);
                    i.putExtra("IDENTIFICADOR", user.getUid());
                    startActivity(i);
                }
            });


        }
    }
}