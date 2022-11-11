package es.unex.fulltank.bd.ui.filtrarGasolineras;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giis.asee.fulltank.databinding.FragmentHistoricoRepostajeBinding;

public class FiltrarGasolinerasFragment extends Fragment {

    private FragmentHistoricoRepostajeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        FiltrarGasolinerasViewModel slideshowViewModel =
                new ViewModelProvider(this).get(FiltrarGasolinerasViewModel.class);

        binding = FragmentHistoricoRepostajeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHistoricoRepostaje;
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}