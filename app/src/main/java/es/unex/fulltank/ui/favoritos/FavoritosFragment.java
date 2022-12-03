package es.unex.fulltank.ui.favoritos;


import static es.unex.fulltank.MainActivity.identificador;

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
import es.unex.fulltank.bd.elembd.GasolineraFavorita;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.databinding.FragmentGasolinerasBinding;
import es.unex.fulltank.ui.gasolineras.GasolinerasAdapter;

public class FavoritosFragment extends Fragment implements GasolinerasAdapter.RecyclerViewClickListener {

    private FragmentGasolinerasBinding binding;
    private RecyclerView recycler;
    private ArrayList<Gasolinera> lGasolineras;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGasolinerasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        lGasolineras = new ArrayList<Gasolinera>();

        recycler = binding.listaFavoritos;
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));

        cargarGasolinerasFavoritas();

        return root;
    }

    private void cargarGasolinerasFavoritas() {
        lGasolineras.clear();
        BD instanceBD = BD.getInstance(getActivity().getApplicationContext());
        AppExecutors.getInstance().diskIO().execute(() -> {
            ArrayList<GasolineraFavorita> lGf = (ArrayList<GasolineraFavorita>) instanceBD.getGasolineraFavoritaDao().getByUid(identificador);

            if (lGf != null || !lGf.isEmpty()) {
                for (GasolineraFavorita g : lGf) {
                    Gasolinera gasolinera = instanceBD.getGasolineraDao().getByCoords(g.getLatitud(), g.getLongitud());
                    lGasolineras.add(gasolinera);
                }
                AppExecutors.getInstance().mainThread().execute(() -> {
                    GasolinerasAdapter adapter = new GasolinerasAdapter(lGasolineras, this, getActivity().getApplicationContext());
                    recycler.setAdapter(adapter);
                });
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        cargarGasolinerasFavoritas();
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
}