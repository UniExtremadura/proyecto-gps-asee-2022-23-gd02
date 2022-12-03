package es.unex.fulltank.ui.resenha;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.unex.fulltank.R;
import es.unex.fulltank.SimpleDividerItemDecoration;
import es.unex.fulltank.bd.elembd.GasolineraResenha;

public class ResenhaFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String LISTA_USUARIO = "lusuario";
    private static final String LISTA_RESENHA = "lresenha";

    private RecyclerView recycler;

    private String calle;
    private ArrayList<GasolineraResenha> lResenha;

    public ResenhaFragment() {
    }

    /**
     * Usar este metodo factory para crear una nueva instancia de este fragmento.
     */
    public static ResenhaFragment newInstance(List<GasolineraResenha> lResenha) {
        ResenhaFragment fragment = new ResenhaFragment();
        Bundle args = new Bundle();
        args.putSerializable(LISTA_RESENHA, (ArrayList) lResenha);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lResenha = (ArrayList<GasolineraResenha>) getArguments().getSerializable(LISTA_RESENHA);
        }
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_resenha, container, false);

        recycler = v.findViewById(R.id.recycler_resenha_gasolinera);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));
        recycler.setAdapter(new ResenhaAdapter(lResenha, getActivity().getApplicationContext()));
        return v;
    }

}
