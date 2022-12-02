package es.unex.fulltank.ui.gasolineras;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.unex.fulltank.R;
import es.unex.fulltank.SimpleDividerItemDecoration;
import es.unex.fulltank.bd.elembd.CombustibleGasolinera;
import es.unex.fulltank.bd.elembd.TipoCombustible;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InfoGasolineraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InfoGasolineraFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ROTULO = "rotulo";
    private static final String CALLE = "calle";
    private static final String MUNICIPIO = "municipio";
    private static final String LISTA_TIPO = "lTipoComb";
    private static final String LISTA_COMB = "lComb";
    private RecyclerView recycler;

    private String rotulo;
    private String calle;
    private String municipio;
    private ArrayList<TipoCombustible> lTipoComb;
    private ArrayList<CombustibleGasolinera> lComb;

    public InfoGasolineraFragment() {
        // Required empty public constructor
    }

    /**
     * Usar este metodo factory para crear una nueva instancia de este fragmento.
     */
    public static InfoGasolineraFragment newInstance(String rotulo, String calle, String municipio, List<TipoCombustible> lTipoComb, List<CombustibleGasolinera> lComb) {
        InfoGasolineraFragment fragment = new InfoGasolineraFragment();
        Bundle args = new Bundle();
        args.putString(ROTULO, rotulo);
        args.putString(CALLE, calle);
        args.putString(MUNICIPIO, municipio);
        args.putSerializable(LISTA_TIPO, (ArrayList) lTipoComb);
        args.putSerializable(LISTA_COMB, (ArrayList) lComb);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            rotulo = getArguments().getString(ROTULO);
            calle = getArguments().getString(CALLE);
            municipio = getArguments().getString(MUNICIPIO);
            lTipoComb = (ArrayList<TipoCombustible>) getArguments().getSerializable(LISTA_TIPO);
            lComb = (ArrayList<CombustibleGasolinera>) getArguments().getSerializable(LISTA_COMB);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_info_gasolinera, container, false);

        TextView rotuloTV = v.findViewById(R.id.rotulo_tv);
        TextView calleTV = v.findViewById(R.id.calle_tv);
        TextView municipioTV = v.findViewById(R.id.municipio_tv);

        mostrarCombustibles();

        rotuloTV.setText(rotulo);
        calleTV.setText(calle);
        municipioTV.setText(municipio);

        recycler = v.findViewById(R.id.recycler_detalle_gasolinera);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));
        recycler.setAdapter(new CombustiblesAdapter(lTipoComb, lComb, getActivity().getApplicationContext()));

        return v;
    }

    private void mostrarCombustibles() {
        for (int i = 0; i < lComb.size(); i++) {
            Log.d("Precio combustible: ", "" + lComb.get(i).getPrecio());
        }
    }
}