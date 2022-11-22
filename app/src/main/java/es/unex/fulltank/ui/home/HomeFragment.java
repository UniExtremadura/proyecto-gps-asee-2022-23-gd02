package es.unex.fulltank.ui.home;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import es.unex.fulltank.R;
import es.unex.fulltank.databinding.FragmentHomeBinding;
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
    private double latitud;
    private double longitud;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getLocalizacion();

        ubicacion = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            latitud = 39.4789003; //latitud por defecto
            longitud = -6.3445624; // longitud por defecto
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION);
            return;
        }else{
            Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            latitud = loc.getLatitude(); //Ubicación precisa lataitud.
            longitud = loc.getLongitude(); //Ubicacion precisa longitud.
        }

        //API GASOLINERA
        retrofit = new Retrofit.Builder()
                .baseUrl("https://sedeaplicaciones.minetur.gob.es/ServiciosRESTCarburantes/PreciosCarburantes/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Habilita los controles de zoom
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //Solicita en caso de no tener permisos de ubicaciones
        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION);
        }else{
            mMap.setMyLocationEnabled(true); //Ubicación precisa google maps
        }

        LatLng posicion = new LatLng(latitud, longitud);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 17)); //Zoom a la ubicación.

    }

    //Método para solicitar los permisos de ubicación al usuario
    private void getLocalizacion() {

        if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) { //Comprueba si no esta otorgado
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION);
            requestPermissionLauncher.launch(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

    }

    @SuppressLint("MissingPermission") // Se garantizan los permisos
    private ActivityResultLauncher<String> requestPermissionLauncher = registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted ->
    {
        if (isGranted) {
            //Permisos concedidos
            if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            ubicacion = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            Location loc = ubicacion.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            latitud = loc.getLatitude();
            longitud = loc.getLongitude();
            mMap.setMyLocationEnabled(true);

            LatLng posicion = new LatLng(latitud, longitud);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 17)); //Zoom a la ubicación.

        }else{
            //Permisos denegados
        }

    });

}