package es.unex.fulltank;

import android.content.Context;

import es.unex.fulltank.datos.CombustibleGasolineraRepository;
import es.unex.fulltank.datos.GasolineraRepository;
import es.unex.fulltank.datos.GasolinerasFavoritasRepository;
import es.unex.fulltank.datos.HistoricoRepository;
import es.unex.fulltank.datos.ResenhaRepository;
import es.unex.fulltank.datos.TipoCombustibleRepository;
import es.unex.fulltank.datos.UsuarioRepository;
import es.unex.fulltank.datos.network.GasolineraNetworkDataSource;
import es.unex.fulltank.datos.network.TipoCombustibleNetworkDataSource;
import es.unex.fulltank.datos.roomdb.BD;
import es.unex.fulltank.ui.ViewModels.GasolineraDetalleViewModelFactory;
import es.unex.fulltank.ui.ViewModels.GasolinerasViewModelFactory;
import es.unex.fulltank.ui.ViewModels.HistoricoViewModelFactory;
import es.unex.fulltank.ui.ViewModels.ResenhaViewModelFactory;
import es.unex.fulltank.ui.ViewModels.TipoCombustibleViewModelFactory;
import es.unex.fulltank.ui.ViewModels.UsuarioViewModelFactory;

public class AppContainer {

    private BD bd;
    private GasolineraNetworkDataSource gasolineraNetworkDataSource;
    private TipoCombustibleNetworkDataSource tipoCombustibleNetworkDataSource;
    private GasolinerasFavoritasRepository gasolinerasFavoritasRepository;
    private GasolineraRepository gasolineraRepository;
    private HistoricoRepository historicoRepository;
    private ResenhaRepository resenhaRepository;
    private UsuarioRepository usuarioRepository;
    private TipoCombustibleRepository tipoCombustibleRepository;

    public GasolinerasViewModelFactory factoryGasolinera;
    public TipoCombustibleViewModelFactory tipoCombustibleViewModelFactory;
    public UsuarioViewModelFactory usuarioViewModelFactory;
    public HistoricoViewModelFactory factoryHistorial;
    public ResenhaViewModelFactory factoryResenha;
    public CombustibleGasolineraRepository combustibleGasolineraRepository;
    public GasolineraDetalleViewModelFactory factoryGasolineraDetalle;

    public AppContainer(Context context) {
        bd = BD.getInstance(context);

        gasolineraNetworkDataSource = GasolineraNetworkDataSource.getInstance();
        tipoCombustibleNetworkDataSource = TipoCombustibleNetworkDataSource.getInstance();

        gasolinerasFavoritasRepository = GasolinerasFavoritasRepository.getInstance(bd.getGasolineraFavoritaDao());
        usuarioRepository = UsuarioRepository.getInstance(bd.getUsuarioDao());
        tipoCombustibleRepository = TipoCombustibleRepository.getInstance(bd.getTipoCombustibleDao(), tipoCombustibleNetworkDataSource);
        historicoRepository = HistoricoRepository.getInstance(bd.getHistorialRepostajeDao());
        resenhaRepository = ResenhaRepository.getInstance(bd.getGasolineraResenhaDao());
        gasolineraRepository = GasolineraRepository.getInstance(bd.getGasolineraDao(), bd.getCombustibleGasolineraDao(), gasolineraNetworkDataSource);
        combustibleGasolineraRepository = CombustibleGasolineraRepository.getInstance(bd.getCombustibleGasolineraDao());

        tipoCombustibleViewModelFactory = new TipoCombustibleViewModelFactory(tipoCombustibleRepository);
        factoryGasolinera = new GasolinerasViewModelFactory(gasolineraRepository);
        factoryHistorial = new HistoricoViewModelFactory(historicoRepository);
        factoryResenha = new ResenhaViewModelFactory(resenhaRepository);
        factoryGasolineraDetalle = new GasolineraDetalleViewModelFactory(combustibleGasolineraRepository,gasolinerasFavoritasRepository,historicoRepository, tipoCombustibleRepository);
        usuarioViewModelFactory = new UsuarioViewModelFactory(usuarioRepository);
    }
}