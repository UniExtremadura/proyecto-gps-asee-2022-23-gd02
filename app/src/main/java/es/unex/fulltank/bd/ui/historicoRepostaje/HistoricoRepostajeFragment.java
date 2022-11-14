package es.unex.fulltank.bd.ui.historicoRepostaje;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import es.unex.fulltank.databinding.FragmentFavoritosBinding;

public class HistoricoRepostajeFragment extends Fragment {

    private FragmentFavoritosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HistoricoRepostajeViewModel historicoRepostajeViewModel =
                new ViewModelProvider(this).get(HistoricoRepostajeViewModel.class);

        binding = FragmentFavoritosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFavoritos;
        historicoRepostajeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}