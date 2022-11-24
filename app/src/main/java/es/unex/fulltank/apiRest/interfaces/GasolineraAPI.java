package es.unex.fulltank.apiRest.interfaces;

import java.util.ArrayList;

import es.unex.fulltank.apiRest.modelos.Combustibles;
import es.unex.fulltank.apiRest.modelos.RespuestaAPI;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Interfaz para realizar consultas a la API del gobierno
 *
 * @author Grupo PGD02
 * @version 1.0
 */
public interface GasolineraAPI {


    /**
     * Obtiene todas las gasolineras
     * @return Devuelve un objeto RespuestaAPI con información de las consultas y la lista de gasolineras
     */
    @GET("EstacionesTerrestres")
    public Call<RespuestaAPI> obtenerGasolineras();


    /**
     * Obtiene todas las gasolineras filtrando por comunidad autónoma
     * @param IDCCAA Identificador de la comunidad autónoma
     * @return Devuelve un objeto RespuestaAPI con información de las consultas y la lista de gasolineras
     */
    @GET("EstacionesTerrestres/FiltroCCAA/{IDCCAA}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroCCAA(@Path("IDCCAA") String IDCCAA);

    /**
     * Obtiene todas las gasolineras filtrando por comunidad autónoma y por un producto en concreto
     * @param IDCCAA Identificador de la comunidad autónoma
     * @param IDProducto Identificador del producto
     * @return Devuelve un objeto RespuestaAPI con información de las consultas y la lista de gasolineras
     */
    @GET("EstacionesTerrestres/FiltroCCAAProducto/{IDCCAA}/{IDProducto}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroCCAAProducto(@Path("IDCCAA") String IDCCAA, @Path("IDProducto") String IDProducto);

    /**
     * Obtiene todas las gasolineras filtrando por el municipio
     * @param IDMunicipio Identificador del municipio
     * @return Devuelve un objeto RespuestaAPI con información de las consultas y la lista de gasolineras
     */
    @GET("EstacionesTerrestres/FiltroMunicipio/{IDMunicipio}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroMunicipio(@Path("IDMunicipio") String IDMunicipio);

    /**
     * Obtiene todas las gasolineras filtrando por el municipio y por un producto en concreto
     * @param IDMunicipio Identificador del municipio
     * @param IDProducto Identificador del producto
     * @return Devuelve un objeto RespuestaAPI con información de las consultas y la lista de gasolineras
     */
    @GET("EstacionesTerrestres/FiltroMunicipioProducto/{IDMunicipio}/{IDProducto}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroMunicipioProducto(@Path("IDMunicipio") String IDMunicipio, @Path("IDProducto") String IDProducto);

    /**
     * Obtiene todas las gasolineras filtrando por un producto en concreto
     * @param IDProducto Identificador del producto
     * @return Devuelve un objeto RespuestaAPI con información de las consultas y la lista de gasolineras
     */
    @GET("EstacionesTerrestres/FiltroProducto/{IDProducto}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroProducto(@Path("IDProducto") String IDProducto);

    /**
     * Obtiene todas las gasolineras filtrando por la provincia
     * @param IDProvincia Identificador de la provincia
     * @return Devuelve un objeto RespuestaAPI con información de las consultas y la lista de gasolineras
     */
    @GET("EstacionesTerrestres/FiltroProvincia/{IDProvincia}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroProvincia(@Path("IDProvincia") String IDProvincia);

    /**
     * Obtiene todas las gasolineras filtrando por la provincia y por un producto en concreto
     * @param IDProvincia Identificador de la provincia
     * @param IDProducto Identificador del producto
     * @return Devuelve un objeto RespuestaAPI con información de las consultas y la lista de gasolineras
     */
    @GET("EstacionesTerrestres/FiltroProvinciaProducto/{IDProvincia}/{IDProducto}")
    public Call<RespuestaAPI> obtenerGasolinerasFiltroProvinciaProducto(@Path("IDProvincia") String IDProvincia, @Path("IDProducto") String IDProducto);

    /**
     * Obtiene todos los productos petroliferos
     * @return Devuelve un objeto RespuestaAPI con todos los productos petroliferos
     */
    @GET("Listados/ProductosPetroliferos/")
    public Call<ArrayList<Combustibles>> obtenerTiposCombustible();
}
