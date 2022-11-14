package es.unex.fulltank.bd.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import es.unex.fulltank.databinding.FragmentFavoritosBinding;

public class SettingsFragment extends Fragment {

    private FragmentFavoritosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SettingsViewModel favoritosViewModel =
                new ViewModelProvider(this).get(SettingsViewModel.class);

        binding = FragmentFavoritosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textFavoritos;
        favoritosViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}