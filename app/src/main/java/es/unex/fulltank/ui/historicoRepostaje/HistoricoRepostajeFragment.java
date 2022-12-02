package es.unex.fulltank.ui.historicoRepostaje;

import static es.unex.fulltank.MainActivity.identificador;

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
import es.unex.fulltank.SimpleDividerItemDecoration;
import es.unex.fulltank.bd.elembd.HistorialRepostaje;
import es.unex.fulltank.bd.roomdb.BD;
import es.unex.fulltank.databinding.FragmentHistoricoRepostajeBinding;

public class HistoricoRepostajeFragment extends Fragment {

    private FragmentHistoricoRepostajeBinding binding;
    private RecyclerView recycler;
    private ArrayList<HistorialRepostaje> lHistorial;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHistoricoRepostajeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        lHistorial = new ArrayList<HistorialRepostaje>();

        recycler = binding.historial;
        recycler.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
        recycler.addItemDecoration(new SimpleDividerItemDecoration(getActivity().getApplicationContext()));

        cargarHistorial();

        return root;
    }

    private void cargarHistorial() {
        lHistorial.clear();
        BD instanceBD = BD.getInstance(getActivity().getApplicationContext());
        AppExecutors.getInstance().diskIO().execute(() -> {
            lHistorial = (ArrayList<HistorialRepostaje>) instanceBD.getHistorialRepostajeDao().getById(identificador);
            AppExecutors.getInstance().mainThread().execute(() -> {
                HistoricoAdapter adapter = new HistoricoAdapter(lHistorial, getActivity().getApplicationContext());
                recycler.setAdapter(adapter);
            });
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        cargarHistorial();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}