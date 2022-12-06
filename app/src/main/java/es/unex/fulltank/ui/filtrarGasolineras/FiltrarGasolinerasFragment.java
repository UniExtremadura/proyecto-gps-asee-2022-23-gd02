package es.unex.fulltank.ui.filtrarGasolineras;

import android.content.Intent;
import android.os.Bundle;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.GasolineraDetalleActivity;
import es.unex.fulltank.R;
import es.unex.fulltank.SimpleDividerItemDecoration;
import es.unex.fulltank.bd.elembd.CombustibleGasolinera;
import es.unex.fulltank.bd.elembd.Gasolinera;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.databinding.FragmentFiltrarGasolinerasBinding;
import es.unex.fulltank.ui.gasolineras.GasolinerasAdapter;

public class FiltrarGasolinerasFragment extends Fragment implements GasolinerasAdapter.RecyclerViewClickListener {

    private FragmentFiltrarGasolinerasBinding binding;
    private Spinner spinner;
    private int opcion;
    private ArrayList<Gasolinera> lGasolinerasInfo = new ArrayList<>();
    Button btnFiltrar;
    GasolinerasAdapter adapter;
    private RecyclerView recycler;
    TextView tv1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFiltrarGasolinerasBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        recycler = binding.listaDeFiltro;

        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));

        spinner = (Spinner) root.findViewById(R.id.spinnerCombustible);
        String[] combustible = {
                EnumCombustible.GASOLINA_95_E5.getNombreCombutible(),
                EnumCombustible.GASOLINA_98_E5.getNombreCombutible(),
                EnumCombustible.GASOLEO_A.getNombreCombutible(),
                EnumCombustible.GASOLEO_PREMIUM.getNombreCombutible(),
                EnumCombustible.GASOLEO_B.getNombreCombutible(),
                EnumCombustible.GASOLEO_C.getNombreCombutible(),
                EnumCombustible.BIODIESEL.getNombreCombutible(),
                EnumCombustible.BIOETANOL.getNombreCombutible(),
                EnumCombustible.GASES_LICUADOS.getNombreCombutible(),
                EnumCombustible.GAS_NATURAL_COMPRIMIDO.getNombreCombutible(),
                EnumCombustible.GAS_NATURAL_LICUADO.getNombreCombutible(),
                EnumCombustible.GASOLINA_95_E5_PREMIUM.getNombreCombutible(),
                EnumCombustible.GASOLINA_98_E10.getNombreCombutible(),
                EnumCombustible.HIDROGENO.getNombreCombutible(),
                EnumCombustible.GASOLINA_95_E10.getNombreCombutible()};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, combustible);
        spinner.setAdapter(adapter);

        btnFiltrar = root.findViewById(R.id.buttonFiltrar);
        btnFiltrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String c = spinner.getSelectedItem().toString();
                opcion = EnumCombustible.getIdCombustible(c);

                AppExecutors.getInstance().mainThread().execute(() -> {
                    if (lGasolinerasInfo.size() > 0) {
                        lGasolinerasInfo.clear();
                    }


                    AppExecutors.getInstance().diskIO().execute(() -> {
                        filtrarDatosCombustibles();
                        tv1 = root.findViewById(R.id.tVNoCombustible);

                        mostrarGasolineras(lGasolinerasInfo, root);
                        if (!lGasolinerasInfo.isEmpty()) {
                            tv1.setText(" ");
                        } else {
                            tv1.setText("No hay ninguna gasolinera cercana con este tipo de combustible");
                        }
                    });

                });
            }
        });
        return root;
    }

    private void filtrarDatosCombustibles() {
        BD instanceBD = BD.getInstance(getActivity().getApplicationContext());
        ArrayList<CombustibleGasolinera> lGasolineras = (ArrayList<CombustibleGasolinera>) instanceBD.getCombustibleGasolineraDao().getAll();
        for (int i = 0; i < lGasolineras.size(); i++) {
            if (lGasolineras.get(i).getCid() == opcion) {
                Gasolinera gasolinera = instanceBD.getGasolineraDao().getByCoords(lGasolineras.get(i).getLatitud()
                        , lGasolineras.get(i).getLongitud());
                lGasolinerasInfo.add(gasolinera);
            }
        }
    }

    private void mostrarGasolineras(ArrayList<Gasolinera> lGasolinerasInfo, View view) {
        AppExecutors.getInstance().diskIO().execute(() -> {
            AppExecutors.getInstance().mainThread().execute(() -> {
                adapter = new GasolinerasAdapter(lGasolinerasInfo, this, getActivity().getApplicationContext());
                recycler.setAdapter(adapter);
            });

        });
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
}