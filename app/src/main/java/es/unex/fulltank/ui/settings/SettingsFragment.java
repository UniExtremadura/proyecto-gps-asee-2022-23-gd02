package es.unex.fulltank.ui.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.SupportMapFragment;

import es.unex.fulltank.MainActivity;
import es.unex.fulltank.R;
import es.unex.fulltank.databinding.FragmentFavoritosBinding;

public class SettingsFragment extends Fragment {

    private FragmentFavoritosBinding binding;

    private SupportMapFragment settingFragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_settings, container, false);

        MainActivity ma = (MainActivity) getActivity();
        SharedPreferences sp = ma.getSharedPreferences("shared", ma.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();

        final Switch s = v.findViewById(R.id.switch1);
        int tema = sp.getInt("Tema", 1);
        if (tema == 1) {
            s.setChecked(false); //Modo claro switch desactivado
        } else {
            s.setChecked(true); //Modo claro switch activado
        }
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (s.isChecked()) {
                    //Activacion modo oscuro
                    editor.putInt("Tema", 0);
                } else {
                    //Activacion modo claro
                    editor.putInt("Tema", 1);
                }
                editor.commit();
                ma.setTema();
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}