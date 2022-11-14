package es.unex.fulltank.bd.ui.ubicaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import es.unex.fulltank.databinding.FragmentUbicacionesBinding;

public class UbicacionesFragment extends Fragment {

    private FragmentUbicacionesBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        UbicacionesViewModel ubicacionesViewModel =
                new ViewModelProvider(this).get(UbicacionesViewModel.class);

        binding = FragmentUbicacionesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textUbicaciones;
        ubicacionesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}