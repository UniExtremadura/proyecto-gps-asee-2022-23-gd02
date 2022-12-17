package es.unex.fulltank.ui.historicoRepostaje;

import static es.unex.fulltank.ui.MainActivity.identificador;

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

import java.util.ArrayList;

import es.unex.fulltank.AppContainer;
import es.unex.fulltank.MyApplication;
import es.unex.fulltank.R;
import es.unex.fulltank.SimpleDividerItemDecoration;
import es.unex.fulltank.databinding.FragmentHistoricoRepostajeBinding;
import es.unex.fulltank.datos.modelo.HistorialRepostaje;
import es.unex.fulltank.ui.ViewModels.HistoricoViewModel;

public class HistoricoRepostajeFragment extends Fragment {

    private FragmentHistoricoRepostajeBinding binding;
    private RecyclerView recycler;
    private ArrayList<HistorialRepostaje> lHistorial;
    private HistoricoAdapter mAdapter;
    private AppContainer appContainer;
    private HistoricoViewModel mViewModel;
    private TextView tv;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appContainer = ((MyApplication) getActivity().getApplication()).appContainer;
        mViewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) appContainer.factoryHistorial).get(HistoricoViewModel.class);

        mViewModel.setIdu(identificador);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHistoricoRepostajeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        tv = root.findViewById(R.id.noRepostajes);
        tv.setText("Todavía no has repostado");
        tv.setVisibility(View.INVISIBLE);

        recycler = binding.historial;
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));

        mViewModel.getCurrentHistorial().observe(getViewLifecycleOwner(), historial -> {

            mAdapter = new HistoricoAdapter(historial, getActivity().getApplicationContext());
            recycler.setAdapter(mAdapter);
            if (historial != null && historial.size() != 0) showHistorialDataView();
            else tv.setVisibility(View.VISIBLE);
        });

        return root;
    }

    private void showHistorialDataView() {
        tv.setVisibility(View.INVISIBLE);
        Log.i(getClass().getSimpleName(), "Data LOADED ====================================");
        recycler.setVisibility(View.VISIBLE);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}