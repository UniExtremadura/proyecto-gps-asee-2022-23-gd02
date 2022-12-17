package es.unex.fulltank.ui.favoritos;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.unex.fulltank.AppContainer;
import es.unex.fulltank.MyApplication;
import es.unex.fulltank.R;
import es.unex.fulltank.SimpleDividerItemDecoration;
import es.unex.fulltank.databinding.FragmentGasolinerasBinding;
import es.unex.fulltank.datos.modelo.Gasolinera;
import es.unex.fulltank.ui.GasolineraDetalleActivity;
import es.unex.fulltank.ui.ViewModels.GasolinerasViewModel;
import es.unex.fulltank.ui.gasolineras.GasolinerasAdapter;

public class FavoritosFragment extends Fragment implements GasolinerasAdapter.RecyclerViewClickListener {

    private FragmentGasolinerasBinding binding;
    private RecyclerView recycler;
    private GasolinerasAdapter adapter;
    private List<Gasolinera> lGasolineras;
    private TextView tv;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGasolinerasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tv = root.findViewById(R.id.noFavoritos);
        tv.setText("Todavía no has añadido ninguna gasolinera a favoritos");
        tv.setVisibility(View.INVISIBLE);

        recycler = binding.listaFavoritos;
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));

        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;

        GasolinerasViewModel mViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factoryGasolinera).get(GasolinerasViewModel.class);
        mViewModel.getCurrentGasolinerasFavoritas().observe(getViewLifecycleOwner(), gasolineras -> {
            lGasolineras = gasolineras;
            adapter = new GasolinerasAdapter(gasolineras, this, getActivity().getApplicationContext());
            recycler.setAdapter(adapter);
            if (lGasolineras != null && lGasolineras.size() != 0)
                showGasolinerasFavoritasDataView();
            else
                tv.setVisibility(View.VISIBLE);
        });

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    /**
     * OnClick, hay que conseguir la gasolinera asociada a la posicion clicada, para
     * mostrar su informacion en un nuevo fragmento.
     */
    @Override
    public void recyclerViewListClicked(View v, int position) {
        Intent intent = new Intent(getActivity().getApplicationContext(), GasolineraDetalleActivity.class);
        Gasolinera g = lGasolineras.get(position);
        intent.putExtra("ROTULO", g.getRotulo());
        intent.putExtra("CALLE", g.getDireccion());
        intent.putExtra("MUNICIPIO", g.getMunicipio());
        intent.putExtra("LATITUD", g.getLatitud());
        intent.putExtra("LONGITUD", g.getLongitud());
        startActivity(intent);
    }


    private void showGasolinerasFavoritasDataView() {
        tv.setVisibility(View.INVISIBLE);
        Log.i(getClass().getSimpleName(), "Data LOADED ====================================");
        recycler.setVisibility(View.VISIBLE);
    }
}