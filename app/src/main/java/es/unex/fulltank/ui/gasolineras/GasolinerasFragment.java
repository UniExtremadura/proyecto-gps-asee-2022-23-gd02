package es.unex.fulltank.ui.gasolineras;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.unex.fulltank.AppContainer;
import es.unex.fulltank.MyApplication;
import es.unex.fulltank.SimpleDividerItemDecoration;
import es.unex.fulltank.databinding.FragmentGasolinerasBinding;
import es.unex.fulltank.datos.modelo.Gasolinera;
import es.unex.fulltank.ui.GasolineraDetalleActivity;
import es.unex.fulltank.ui.ViewModels.GasolinerasViewModel;


public class GasolinerasFragment extends Fragment implements GasolinerasAdapter.RecyclerViewClickListener {

    private FragmentGasolinerasBinding binding;
    private List<Gasolinera> lGasolineras;
    private RecyclerView recycler;
    private GasolinerasAdapter adapter;
    private ProgressBar mProgressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGasolinerasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        mProgressBar = binding.progressGas;

        recycler = binding.listaFavoritos;
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));


        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;

        GasolinerasViewModel mViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factoryGasolinera).get(GasolinerasViewModel.class);
        mViewModel.getCurrentGasolinerasFiltradasCoords().observe(getViewLifecycleOwner(), gasolineras -> {
            lGasolineras = gasolineras;
            adapter = new GasolinerasAdapter(gasolineras, this, getActivity().getApplicationContext());
            recycler.setAdapter(adapter);
            if (lGasolineras != null && lGasolineras.size() != 0) showGasolinerasDataView();
            else showLoading();
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

    private void showLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
        Log.i(getClass().getSimpleName(), "Loading");
        recycler.setVisibility(View.INVISIBLE);
    }

    private void showGasolinerasDataView() {
        mProgressBar.setVisibility(View.GONE);
        Log.i(getClass().getSimpleName(), "Data LOADED ====================================");
        recycler.setVisibility(View.VISIBLE);
    }

}