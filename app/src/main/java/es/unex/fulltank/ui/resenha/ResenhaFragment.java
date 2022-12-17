package es.unex.fulltank.ui.resenha;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
import es.unex.fulltank.datos.modelo.GasolineraResenha;
import es.unex.fulltank.ui.ViewModels.ResenhaViewModel;

public class ResenhaFragment extends Fragment {

    private static final String LISTA_RESENHA = "lresenha";
    private ResenhaAdapter adapter;
    private RecyclerView recycler;
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

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_resenha, container, false);

        recycler = v.findViewById(R.id.recycler_resenha_gasolinera);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));
        recycler.setAdapter(new ResenhaAdapter(lResenha, getActivity().getApplicationContext()));

        AppContainer appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        ResenhaViewModel mViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factoryResenha).get(ResenhaViewModel.class);
        mViewModel.getCurrentResenha().observe(getViewLifecycleOwner(), lResenhas -> {

            adapter = new ResenhaAdapter(lResenhas, getActivity().getApplicationContext());
            recycler.setAdapter(adapter);
            if (lResenhas != null && lResenhas.size() != 0) showGasolinerasResenhaDataView();
        });

        return v;
    }

    private void showGasolinerasResenhaDataView() {
        Log.i(getClass().getSimpleName(), "Data LOADED ====================================");
        recycler.setVisibility(View.VISIBLE);
    }
}
