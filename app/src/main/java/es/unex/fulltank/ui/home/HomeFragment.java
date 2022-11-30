package es.unex.fulltank.ui.home;


import static java.lang.Double.parseDouble;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

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
    private static final String TAG = "GASAPI";
    private GoogleMap mMap;
    private FragmentHomeBinding binding;
    private LocationManager ubicacion;
    private Retrofit retrofit;
    private SupportMapFragment mapFragment;
    public static double latitudActual;
    public static double longitudActual;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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

        mapFragment = (SupportMapFragment) getChildFragmentManager()
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
            locationPermissionRequest.launch(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            });
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

            if (ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity().getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            mMap.setMyLocationEnabled(true);
            cargarGasolineras();
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
    ActivityResultLauncher<String[]> locationPermissionRequest =
            registerForActivityResult(new ActivityResultContracts
                            .RequestMultiplePermissions(), result -> {
                        Boolean fineLocationGranted = result.getOrDefault(
                                Manifest.permission.ACCESS_FINE_LOCATION, false);
                        Boolean coarseLocationGranted = result.getOrDefault(
                                Manifest.permission.ACCESS_COARSE_LOCATION, false);
                        if (fineLocationGranted != null && fineLocationGranted) {

                            ubicacion = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
                            ubicacion.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1, 10, locationListenerGPS);
                        } else {
                            //Permisos denegados
                            latitudActual = 39.4789003; //latitud por defecto
                            longitudActual = -6.3445624; // longitud por defecto
                            LatLng posicion = new LatLng(latitudActual, longitudActual);
                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posicion, 17)); //Zoom a la ubicación.
                            cargarGasolineras();
                        }
                    }
            );

    /**
     * Para cada gasolinera de la lista, se anade su marcador correspondiente en el mapa.
     */
    private void anadirMarcadores(ArrayList<Gasolinera> gasolineras) {
        mMap.clear();
        for (Gasolinera g : gasolineras) {
            LatLng posGasolinera = new LatLng(parseDouble(g.getLatitud().replace(',', '.')),
                    parseDouble(g.getLongitud().replace(',', '.')));
            mMap.addMarker(new MarkerOptions()
                    .position(posGasolinera)
                    .title(g.getRotulo()));
        }
    }

    /**
     * Filtra las gasolineras en un area cercana a la localizacion actual
     */
    private ArrayList<Gasolinera> filtrarGasolineras(ArrayList<Gasolinera> lgasolineras) {
        double latitudSuperior = latitudActual + 0.5;
        double latitudInferior = latitudActual - 0.5;
        double longitudSuperior = longitudActual + 0.5;
        double longitudInferior = longitudActual - 0.5;
        ArrayList<Gasolinera> gasolinerasFiltradas = new ArrayList<>();
        for (int i = 0; i < lgasolineras.size(); i++) {
            Gasolinera g = lgasolineras.get(i);
            double latitudObtenida = parseDouble(g.getLatitud().replace(',', '.'));
            double longitudObtenida = parseDouble(g.getLongitud().replace(',', '.'));
            if (latitudObtenida > latitudInferior && latitudObtenida < latitudSuperior && longitudObtenida > longitudInferior && longitudObtenida < longitudSuperior) {
                gasolinerasFiltradas.add(g);
            }
        }
        return gasolinerasFiltradas;
    }

    private void cargarGasolineras() {
        GasolineraAPI service = retrofit.create(GasolineraAPI.class);
        Call<RespuestaAPI> respuesta = service.obtenerGasolineras(); //Método de API al que llamar
        respuesta.enqueue(new Callback<RespuestaAPI>() {
            @Override
            public void onResponse(Call<RespuestaAPI> call, Response<RespuestaAPI> response) {
                if (response.isSuccessful()) {
                    RespuestaAPI respuesta1 = response.body();
                    Log.i(TAG, "Fecha: " + respuesta1.getFecha());
                    Log.i(TAG, "Nota: " + respuesta1.getNota());
                    Log.i(TAG, "Resultado consulta: " + respuesta1.getResultadoConsulta());
                    ArrayList<Gasolinera> lgasolineras = respuesta1.getListaGasolineras();
                    Log.i(TAG, "VECTOR: " + lgasolineras.isEmpty() + " " + lgasolineras.size());
                    ArrayList<Gasolinera> gasolinerasFiltradas = filtrarGasolineras(lgasolineras);
                    CargaGasolinerasBD.meterGasolinerasEnBD(gasolinerasFiltradas, getActivity().getApplicationContext());
                    anadirMarcadores(gasolinerasFiltradas);
                } else {
                    Log.e(TAG, " onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<RespuestaAPI> call, Throwable t) {
            }
        });
    }
}