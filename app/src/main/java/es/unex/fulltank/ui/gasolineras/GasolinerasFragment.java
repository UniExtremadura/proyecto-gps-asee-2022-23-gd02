package es.unex.fulltank.ui.gasolineras;

import static es.unex.fulltank.ui.home.HomeFragment.latitudActual;
import static es.unex.fulltank.ui.home.HomeFragment.longitudActual;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.GasolineraDetalleActivity;
import es.unex.fulltank.SimpleDividerItemDecoration;
import es.unex.fulltank.bd.elembd.Gasolinera;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.databinding.FragmentGasolinerasBinding;


public class GasolinerasFragment extends Fragment implements GasolinerasAdapter.RecyclerViewClickListener {

    private FragmentGasolinerasBinding binding;
    private ArrayList<Gasolinera> lGasolineras;
    private RecyclerView recycler;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGasolinerasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        recycler = binding.listaFavoritos;
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));

        BD instanceBD = BD.getInstance(getActivity().getApplicationContext());
        AppExecutors.getInstance().diskIO().execute(() -> {
            ArrayList<Gasolinera> lGasolinerasAux = (ArrayList<Gasolinera>) instanceBD.getGasolineraDao().getAll();
            lGasolineras = filtrarGasolineras(lGasolinerasAux);
            AppExecutors.getInstance().mainThread().execute(() -> {
                GasolinerasAdapter adapter = new GasolinerasAdapter(lGasolineras, this, getActivity().getApplicationContext());
                recycler.setAdapter(adapter);
            });
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
            double latitudObtenida = g.getLatitud();
            double longitudObtenida = g.getLongitud();
            if (latitudObtenida > latitudInferior && latitudObtenida < latitudSuperior && longitudObtenida > longitudInferior && longitudObtenida < longitudSuperior) {
                gasolinerasFiltradas.add(g);
            }
        }
        return gasolinerasFiltradas;
    }
}