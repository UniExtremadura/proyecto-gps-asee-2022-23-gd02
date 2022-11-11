package es.unex.fulltank.bd.ui.vehiculos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import es.unex.giis.asee.fulltank.databinding.FragmentVehiculosBinding;

public class VehiculosFragment extends Fragment {

    private FragmentVehiculosBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        VehiculosViewModel vehiculosViewModel =
                new ViewModelProvider(this).get(VehiculosViewModel.class);

        binding = FragmentVehiculosBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textVehiculos;
        vehiculosViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}