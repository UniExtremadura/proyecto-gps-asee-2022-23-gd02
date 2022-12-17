package es.unex.fulltank.ui.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import es.unex.fulltank.datos.UsuarioRepository;
import es.unex.fulltank.datos.modelo.Usuario;
import es.unex.fulltank.ui.gasolineras.GasolinerasFragment;

/**
 * {@link ViewModel} for {@link GasolinerasFragment}
 */
public class UsuarioViewModel extends ViewModel {
    private final UsuarioRepository mRepository;



    public UsuarioViewModel(UsuarioRepository repository) {
        this.mRepository = repository;
    }

    public LiveData<Usuario> getCurrentUsuario(){
        return mRepository.getCurrentUsuario();
    }
    public void setLogin(String usuario, String contra){
        mRepository.setLogin(usuario,contra);
    }

    public void  registrar(Usuario user){
        mRepository.registrar(user);
    }


}
