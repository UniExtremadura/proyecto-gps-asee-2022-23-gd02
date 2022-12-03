package es.unex.fulltank;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import es.unex.fulltank.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity  {

    public static int identificador;
    private ActivityMainBinding binding;

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        identificador = getIntent().getExtras().getInt("IDENTIFICADOR");

        binding = ActivityMainBinding.inflate(getLayoutInflater()); //Permite pasar de xml a una vista
        setContentView(binding.getRoot()); // Establace la vista

        setSupportActionBar(binding.appBarMain.toolbar);

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_favoritos, R.id.nav_filtrarGasolineras, R.id.nav_gasolineras,
                R.id.nav_historicoRepostaje, R.id.nav_settings)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        setTema();
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void setTema() {
        SharedPreferences sp = getSharedPreferences("shared", this.MODE_PRIVATE);
        int tema = sp.getInt("Tema", 1); //Por defecto sera modo claro

        //setDefaultNightMode
        if (tema == 0) {
            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {

            getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }


}