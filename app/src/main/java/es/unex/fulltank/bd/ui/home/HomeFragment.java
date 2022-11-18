package es.unex.fulltank.bd.ui.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

import es.unex.fulltank.MainActivity;
import es.unex.fulltank.R;
import es.unex.fulltank.apiRest.interfaces.GasolineraAPI;
import es.unex.fulltank.apiRest.modelos.Gasolinera;
import es.unex.fulltank.apiRest.modelos.RespuestaAPI;
import es.unex.fulltank.databinding.FragmentHomeBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements OnMapReadyCallback {
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 28;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 29;
    private static final String TAG = "GASAPI";
    private GoogleMap mMap;
    private FragmentHomeBinding binding;
    private LocationManager ubicacion;
    private Retrofit retrofit;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLocalizacion();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        obtenerDatos();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //final TextView textView = binding.textHome;
        // homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng posicion = new LatLng(39.4789003, -6.3445571);
        mMap.addMarker(new MarkerOptions()
                .position(posicion)
                .title("Unex"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 17));


        //
        //Habilita los controles de zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);



        //Posicion precisa
      //  mMap.setMyLocationEnabled(true);

    }

    private void getLocalizacion() {
        int permiso;
        permiso = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION); //Comprueba el permiso
        if (permiso == PackageManager.PERMISSION_DENIED) { //Comprueba si no esta otorgado
            // ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        permiso = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION);//SOLO NECESITA ESTE
        if (permiso == PackageManager.PERMISSION_DENIED) { //Comprueba si no esta otorgado
            //ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
        }
    }

    private ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted ->
    {
        if (isGranted) {

            if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            Log.e("A28","ACEPTADOOO");
        }else{
            Log.e("A28","DENEGADOOOOOOOOO");
        }

    });

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