package es.unex.fulltank.ui.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import es.unex.fulltank.datos.GasolineraRepository;
import es.unex.fulltank.datos.modelo.Gasolinera;
import es.unex.fulltank.ui.gasolineras.GasolinerasFragment;

/**
 * {@link ViewModel} for {@link GasolinerasFragment}
 */
public class GasolinerasViewModel extends ViewModel {
    private final GasolineraRepository mRepository;
    private final LiveData<List<Gasolinera>> mGasolineras;
    private final LiveData<List<Gasolinera>> mGasolinerasFav;
    private final LiveData<List<Gasolinera>> mGasolinerasFiltro;


    public GasolinerasViewModel(GasolineraRepository repository) {
        this.mRepository = repository;
        this.mGasolineras = mRepository.getCurrentGasolinerasFiltradasCoords();
        this.mGasolinerasFav = mRepository.getCurrentGasolinerasFiltradasFav();
        this.mGasolinerasFiltro = mRepository.getCurrentGasolinerasFiltradasCom();
    }

    public LiveData<List<Gasolinera>> getCurrentGasolinerasFiltradasCoords() {
        return mGasolineras;
    }

    public LiveData<List<Gasolinera>> getCurrentGasolinerasFavoritas() {
        return mGasolinerasFav;
    }

    public LiveData<List<Gasolinera>> getCurrentGasolinerasFiltradasComb() {
        return mGasolinerasFiltro;
    }

    public void setCoords(double latitud, double longitud){
        mRepository.setCoords(latitud,longitud);
    }

    public void setIdu(int identificador){
        mRepository.setIdu(identificador);
    }

    public void setComb(int idc, double latitud, double longitud){
        mRepository.setComb(idc,latitud,longitud);
    }

}
