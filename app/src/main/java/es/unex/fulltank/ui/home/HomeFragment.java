package es.unex.fulltank.ui.home;


import static es.unex.fulltank.ui.MainActivity.identificador;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
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
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import es.unex.fulltank.AppContainer;
import es.unex.fulltank.MyApplication;
import es.unex.fulltank.R;
import es.unex.fulltank.databinding.FragmentHomeBinding;
import es.unex.fulltank.datos.modelo.Gasolinera;
import es.unex.fulltank.ui.ViewModels.GasolinerasViewModel;

public class HomeFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private FragmentHomeBinding binding;
    private LocationManager ubicacion;
    public static double latitudActual;
    public static double longitudActual;
    private AppContainer appContainer;
    private GasolinerasViewModel mViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appContainer = ((MyApplication) getActivity().getApplication()).appContainer;

        mViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factoryGasolinera).get(GasolinerasViewModel.class);


    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
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
            locationPermissionRequest.launch(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION});
        } else {
            ubicacion = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            ubicacion.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 10, locationListenerGPS);

        }


    }

    LocationListener locationListenerGPS = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitudActual = location.getLatitude();
            longitudActual = location.getLongitude();

            mViewModel.setIdu(identificador);
            mViewModel.setCoords(latitudActual, longitudActual);
            mViewModel.getCurrentGasolinerasFiltradasCoords().observe(HomeFragment.this, gasolineras -> anadirMarcadores(gasolineras));//Devuelve un liveData
            if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            mMap.setMyLocationEnabled(false);
            mMap.setMyLocationEnabled(true);
            LatLng posicion = new LatLng(latitudActual, longitudActual);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 17)); //Zoom a la ubicación.


        }


        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @SuppressLint("MissingPermission")
    ActivityResultLauncher<String[]> locationPermissionRequest = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {
        Boolean fineLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false);
        Boolean coarseLocationGranted = result.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false);
        if (fineLocationGranted != null && fineLocationGranted) {

            ubicacion = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            ubicacion.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 10, locationListenerGPS);
        } else {
            //Permisos denegados
            latitudActual = 39.4789003; //latitud por defecto
            longitudActual = -6.3445624; // longitud por defecto

            mViewModel.setCoords(latitudActual, longitudActual);
            mViewModel.setIdu(identificador);

            LatLng posicion = new LatLng(latitudActual, longitudActual);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 17)); //Zoom a la ubicación.
        }
    });

    /**
     * Para cada gasolinera de la lista, se anade su marcador correspondiente en el mapa.
     */

    private void anadirMarcadores(List<Gasolinera> gasolineras) {
        mMap.clear();
        for (Gasolinera g : gasolineras) {
            LatLng posGasolinera = new LatLng(g.getLatitud(), g.getLongitud());
            mMap.addMarker(new MarkerOptions().position(posGasolinera).title(g.getRotulo()));
        }
    }

}