package es.unex.fulltank;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;


import es.unex.fulltank.R;
import es.unex.fulltank.apiRest.interfaces.GasolineraAPI;
import es.unex.fulltank.apiRest.modelos.Gasolinera;
import es.unex.fulltank.apiRest.modelos.RespuestaAPI;
import es.unex.fulltank.databinding.ActivityMainBinding;
import es.unex.fulltank.bd.roomdb.PruebasBD;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    private static final String TAG = "GASAPI";

    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 28;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 29;
    private ActivityMainBinding binding;

    private AppBarConfiguration mAppBarConfiguration;
    private Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater()); //Permite pasar de xml a una vista
        setContentView(binding.getRoot()); // Establace la vista

        retrofit = new Retrofit.Builder()
                .baseUrl("https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos();
        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_favoritos, R.id.nav_filtrarGasolineras, R.id.nav_ubicaciones, R.id.nav_vehiculos,
                R.id.nav_historicoRepostaje, R.id.nav_settings)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
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
/*
    @Override
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
*/
private void obtenerDatos() {
    GasolineraAPI service = retrofit.create(GasolineraAPI.class);
    Call<RespuestaAPI> respuesta = service.obtenerGasolinerasFiltroMunicipio("698");//Método al que llamar


    respuesta.enqueue(new Callback<RespuestaAPI>() {
        @Override
        public void onResponse(Call<RespuestaAPI> call, Response<RespuestaAPI> response) {
            if(response.isSuccessful()){
                RespuestaAPI respuesta1 = response.body();
                Log.i(TAG,"Fecha: "+respuesta1.getFecha());
                Log.i(TAG,"Nota: "+respuesta1.getNota());
                Log.i(TAG,"Resultado consulta: "+respuesta1.getResultadoConsulta());

                ArrayList<Gasolinera> lgasolineras = respuesta1.getListaGasolineras();
                Log.i(TAG,"VECTOR: "+ lgasolineras.isEmpty()+" "+ lgasolineras.size());
                for(int i= 0; i < lgasolineras.size();i++){
                    Gasolinera g = lgasolineras.get(i);
                    Log.i(TAG,g.getCP());
                    Log.i(TAG,g.getDireccion());
                    Log.i(TAG,g.getLatitud());
                    Log.i(TAG,g.getLongitud());
                    Log.i(TAG,g.getLocalidad());
                    Log.i(TAG,g.getPrecioBiodiesel());
                    Log.i(TAG,g.getPrecioBioetanol());
                    Log.i(TAG,g.getPrecioGasesLicuadosDelPetroleo());
                    Log.i(TAG,g.getPrecioGasoleoA());
                    Log.i(TAG,g.getPrecioGasoleoB());
                    Log.i(TAG,g.getPrecioGasolina98E10());
                    Log.i(TAG,g.getRecioGasolina98E5());
                    Log.i(TAG,g.getPrecioGasolina95E5());
                    Log.i(TAG,g.getPrecioGasolina95E5Premium());
                    Log.i(TAG,g.getPrecioGasolina95E10());
                    Log.i(TAG,g.getPrecioHidrogeno());
                    Log.i(TAG,g.getPrecioGasoleoPremium());
                    Log.i(TAG,"------------------------------");
                }


            }else{
                Log.e(TAG," onResponse: " + response.errorBody());
            }
        }

        @Override
        public void onFailure(Call<RespuestaAPI> call, Throwable t) {

        }
    });

}
}