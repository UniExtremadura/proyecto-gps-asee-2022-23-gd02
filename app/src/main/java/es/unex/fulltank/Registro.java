package es.unex.fulltank;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import es.unex.fulltank.bd.elembd.Usuario;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.bd.roomdb.UsuarioDao;

public class Registro extends AppCompatActivity {
    private EditText mUsername;
    private EditText mCorreo;
    private EditText mContra1;
    private EditText mContra2;
    private int identificador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        mUsername = (EditText) findViewById(R.id.username);
        mCorreo = (EditText) findViewById(R.id.email);
        mContra1 = (EditText) findViewById(R.id.password);
        mContra2 = (EditText) findViewById(R.id.repassword);


    }

    public void registro(View view) {
        if (!mContra1.getText().toString().equals(mContra2.getText().toString())) {
            Toast.makeText(Registro.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else {
            BD instanceBD = BD.getInstance(Registro.this);
            String user = mUsername.getText().toString();
            String correo = mCorreo.getText().toString();
            String contra = mContra1.getText().toString();
            Usuario u = new Usuario(user, correo, contra);

            UsuarioDao uDao = BD.getInstance(Registro.this).getUsuarioDao();

            AppExecutors.getInstance().diskIO().execute(() -> {

                if (uDao.getByUsuario(user) == null) {
                    instanceBD.getUsuarioDao().insert(u);
                    Usuario usuario = uDao.getByUsuario(user);
                    identificador = usuario.getUid();

                    AppExecutors.getInstance().mainThread().execute(() -> {
                        Toast.makeText(Registro.this, "Te has registrado correctamente @" + user, Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(Registro.this, MainActivity.class);
                        i.putExtra("IDENTIFICADOR", identificador);
                        startActivity(i);
                    });
                }

            });


        }
    }
}