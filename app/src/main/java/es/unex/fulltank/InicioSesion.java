package es.unex.fulltank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class InicioSesion extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 28;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 29;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        getLocalizacion();

        Button button = findViewById(R.id.iniciarSesion);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InicioSesion.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public void getLocalizacion() {
        int permiso;

        permiso= ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION); //Comprueba el permiso
        if (permiso == PackageManager.PERMISSION_DENIED) { //Comprueba si no esta otorgado
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }

        permiso = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);//SOLO NECESITA ESTE
        if (permiso == PackageManager.PERMISSION_DENIED) { //Comprueba si no esta otorgado
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.e("A28","kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
                }else{
                    Log.e("A28","PERMISO DENEGADO");
                }
                return;

            case MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Log.e("A28","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
                }else{
                    Log.e("A28","PERMISO DENEGADO B");
                }
                return;
        }
    }
}