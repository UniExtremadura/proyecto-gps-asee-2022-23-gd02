package es.unex.fulltank.apiRest.interfaces;

import es.unex.fulltank.apiRest.modelos.RespuestaAPI;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GasolineraAPI {
    @GET("EstacionesTerrestres")
    public Call<RespuestaAPI> obtenerGasolineras();

    @GET("EstacionesTerrestres/FiltroCCAA/{IDCCAA}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroCCAA(@Path("IDCCAA") String IDCCAA);
    @GET("EstacionesTerrestres/FiltroCCAAProducto/{IDCCAA}/{IDProducto}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroCCAAProducto(@Path("IDCCAA") String IDCCAA, @Path("IDProducto") String IDProducto);
    @GET("EstacionesTerrestres/FiltroMunicipio/{IDMunicipio}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroMunicipio(@Path("IDMunicipio") String IDMunicipio);
    @GET("EstacionesTerrestres/FiltroMunicipioProducto/{IDMunicipio}/{IDProducto}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroMunicipioProducto(@Path("IDMunicipio") String IDMunicipio, @Path("IDProducto") String IDProducto);
    @GET("EstacionesTerrestres/FiltroProducto/{IDProducto}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroProducto(@Path("IDProducto") String IDProducto);
    @GET("EstacionesTerrestres/FiltroProvincia/{IDProvincia}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroProvincia(@Path("IDProvincia") String IDProvincia);
    @GET("EstacionesTerrestres/FiltroProvinciaProducto/{IDProvincia}/{IDProducto}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroProvinciaProducto(@Path("IDProvincia") String IDProvincia, @Path("IDProducto") String IDProducto);
}
