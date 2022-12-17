package es.unex.fulltank.ui.filtrarGasolineras;

import static es.unex.fulltank.ui.home.HomeFragment.latitudActual;
import static es.unex.fulltank.ui.home.HomeFragment.longitudActual;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.unex.fulltank.AppContainer;
import es.unex.fulltank.MyApplication;
import es.unex.fulltank.R;
import es.unex.fulltank.SimpleDividerItemDecoration;
import es.unex.fulltank.databinding.FragmentFiltrarGasolinerasBinding;
import es.unex.fulltank.datos.GasolineraRepository;
import es.unex.fulltank.datos.modelo.Gasolinera;
import es.unex.fulltank.ui.GasolineraDetalleActivity;
import es.unex.fulltank.ui.ViewModels.GasolinerasViewModel;
import es.unex.fulltank.ui.gasolineras.GasolinerasAdapter;

public class FiltrarGasolinerasFragment extends Fragment implements GasolinerasAdapter.RecyclerViewClickListener {

    private FragmentFiltrarGasolinerasBinding binding;
    private Spinner spinner;
    private int opcion;
    private List<Gasolinera> lGasolinerasInfo = new ArrayList<>();
    private Button btnFiltrar;
    private GasolinerasAdapter adapterFiltro;
    private GasolinerasAdapter adapter;
    private RecyclerView recycler;
    private GasolineraRepository mRepository;
    private TextView tv1;
    private AppContainer appContainer;
    private GasolinerasViewModel mViewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFiltrarGasolinerasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recycler = binding.listaDeFiltro;


        tv1 = root.findViewById(R.id.tVNoCombustible);
        tv1.setText("No hay gasolineras con este tipo de combustible");
        tv1.setVisibility(View.INVISIBLE);

        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));

        spinner = (Spinner) root.findViewById(R.id.spinnerCombustible);
        String[] combustible = {EnumCombustible.GASOLINA_95_E5.getNombreCombutible(), EnumCombustible.GASOLINA_98_E5.getNombreCombutible(), EnumCombustible.GASOLEO_A.getNombreCombutible(), EnumCombustible.GASOLEO_PREMIUM.getNombreCombutible(), EnumCombustible.GASOLEO_B.getNombreCombutible(), EnumCombustible.GASOLEO_C.getNombreCombutible(), EnumCombustible.BIODIESEL.getNombreCombutible(), EnumCombustible.BIOETANOL.getNombreCombutible(), EnumCombustible.GASES_LICUADOS.getNombreCombutible(), EnumCombustible.GAS_NATURAL_COMPRIMIDO.getNombreCombutible(), EnumCombustible.GAS_NATURAL_LICUADO.getNombreCombutible(), EnumCombustible.GASOLINA_95_E5_PREMIUM.getNombreCombutible(), EnumCombustible.GASOLINA_98_E10.getNombreCombutible(), EnumCombustible.HIDROGENO.getNombreCombutible(), EnumCombustible.GASOLINA_95_E10.getNombreCombutible()};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, combustible);
        spinner.setAdapter(adapter);

        btnFiltrar = root.findViewById(R.id.buttonFiltrar);

        appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        mViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factoryGasolinera).get(GasolinerasViewModel.class);

        btnFiltrar.setOnClickListener(view -> {
            String c = spinner.getSelectedItem().toString();
            mViewModel.setComb(EnumCombustible.getIdCombustible(c), latitudActual, longitudActual);
            mViewModel.getCurrentGasolinerasFiltradasComb().observe(getViewLifecycleOwner(), gasolineras -> {
                lGasolinerasInfo = gasolineras;
                adapterFiltro = new GasolinerasAdapter(gasolineras, FiltrarGasolinerasFragment.this, getActivity().getApplicationContext());
                recycler.setAdapter(adapterFiltro);
                if (lGasolinerasInfo != null && lGasolinerasInfo.size() != 0) {
                    showGasolinerasFiltroDataView();
                }else{
                    tv1.setVisibility(View.VISIBLE);
                }
            });
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void recyclerViewListClicked(View v, int position) {
        Intent intent = new Intent(getActivity().getApplicationContext(), GasolineraDetalleActivity.class);
        Gasolinera g = lGasolinerasInfo.get(position);
        intent.putExtra("ROTULO", g.getRotulo());
        intent.putExtra("CALLE", g.getDireccion());
        intent.putExtra("MUNICIPIO", g.getMunicipio());
        intent.putExtra("LATITUD", g.getLatitud());
        intent.putExtra("LONGITUD", g.getLongitud());
        startActivity(intent);
    }


    private void showGasolinerasFiltroDataView() {
        tv1.setVisibility(View.INVISIBLE);
        Log.i(getClass().getSimpleName(), "Data LOADED ====================================");
        recycler.setVisibility(View.VISIBLE);
    }
}