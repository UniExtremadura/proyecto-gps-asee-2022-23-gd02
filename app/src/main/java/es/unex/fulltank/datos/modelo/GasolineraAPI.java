package es.unex.fulltank.datos.modelo;

import static java.lang.Double.parseDouble;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que define la estructura de un objeto Gasolinera
 *
 * @author Grupo PGD02
 * @version 1.0
 */
public class GasolineraAPI {

    @SerializedName("C.P.")
    @Expose
    private String CP;
    @SerializedName("Dirección")
    @Expose
    private String direccion;
    @SerializedName("Horario")
    @Expose
    private String horario;
    @SerializedName("Latitud")
    @Expose
    String latitud;
    @SerializedName("Localidad")
    @Expose
    private String localidad;
    @SerializedName("Longitud (WGS84)")
    @Expose
    private String longitud;
    @SerializedName("Margen")
    @Expose
    private String margen;
    @SerializedName("Municipio")
    @Expose
    private String municipio;
    @SerializedName("Precio Biodiesel")
    @Expose
    private String precioBiodiesel;
    @SerializedName("Precio Bioetanol")
    @Expose
    private String precioBioetanol;
    @SerializedName("Precio Gas Natural Comprimido")
    @Expose
    private String precioGasNaturalComprimido;
    @SerializedName("Precio Gas Natural Licuado")
    @Expose
    private String precioGasNaturalLicuado;
    @SerializedName("Precio Gases licuados del petróleo")
    @Expose
    private String precioGasesLicuadosDelPetroleo;
    @SerializedName("Precio Gasoleo A")
    @Expose
    private String precioGasoleoA;
    @SerializedName("Precio Gasoleo B")
    @Expose
    private String precioGasoleoB;
    @SerializedName("Precio Gasoleo Premium")
    @Expose
    private String precioGasoleoPremium;
    @SerializedName("Precio Gasolina 95 E10")
    @Expose
    private String precioGasolina95E10;
    @SerializedName("Precio Gasolina 95 E5")
    @Expose
    private String precioGasolina95E5;
    @SerializedName("Precio Gasolina 95 E5 Premium")
    @Expose
    private String precioGasolina95E5Premium;
    @SerializedName("Precio Gasolina 98 E10")
    @Expose
    private String precioGasolina98E10;
    @SerializedName("Precio Gasolina 98 E5")
    @Expose
    private String recioGasolina98E5;
    @SerializedName("Precio Hidrogeno")
    @Expose
    private String precioHidrogeno;
    @SerializedName("Provincia")
    @Expose
    private String provincia;
    @SerializedName("Remisión")
    @Expose
    private String remision;
    @SerializedName("Rótulo")
    @Expose
    private String rotulo;
    @SerializedName("Tipo Venta")
    @Expose
    private String tipoVenta;
    @SerializedName("% BioEtanol")
    @Expose
    private String porcentajeBioEtanol;
    @SerializedName("% Éster metílico")
    @Expose
    private String porcentajeEsterMetilico;
    @SerializedName("IDEESS")
    @Expose
    private String IDEESS;
    @SerializedName("IDMunicipio")
    @Expose
    private String IDMunicipio;
    @SerializedName("IDProvincia")
    @Expose
    private String IDProvincia;
    @SerializedName("IDCCAA")
    @Expose
    private String IDCCAA;

    public String getCP() {
        return CP;
    }

    public void setCP(String CP) {
        this.CP = CP;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getMargen() {
        return margen;
    }

    public void setMargen(String margen) {
        this.margen = margen;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getPrecioBiodiesel() {
        return precioBiodiesel;
    }

    public void setPrecioBiodiesel(String precioBiodiesel) {
        this.precioBiodiesel = precioBiodiesel;
    }

    public String getPrecioBioetanol() {
        return precioBioetanol;
    }

    public void setPrecioBioetanol(String precioBioetanol) {
        this.precioBioetanol = precioBioetanol;
    }

    public String getPrecioGasNaturalComprimido() {
        return precioGasNaturalComprimido;
    }

    public void setPrecioGasNaturalComprimido(String precioGasNaturalComprimido) {
        this.precioGasNaturalComprimido = precioGasNaturalComprimido;
    }

    public String getPrecioGasNaturalLicuado() {
        return precioGasNaturalLicuado;
    }

    public void setPrecioGasNaturalLicuado(String precioGasNaturalLicuado) {
        this.precioGasNaturalLicuado = precioGasNaturalLicuado;
    }

    public String getPrecioGasesLicuadosDelPetroleo() {
        return precioGasesLicuadosDelPetroleo;
    }

    public void setPrecioGasesLicuadosDelPetroleo(String precioGasesLicuadosDelPetroleo) {
        this.precioGasesLicuadosDelPetroleo = precioGasesLicuadosDelPetroleo;
    }

    public String getPrecioGasoleoA() {
        return precioGasoleoA;
    }

    public void setPrecioGasoleoA(String precioGasoleoA) {
        this.precioGasoleoA = precioGasoleoA;
    }

    public String getPrecioGasoleoB() {
        return precioGasoleoB;
    }

    public void setPrecioGasoleoB(String precioGasoleoB) {
        this.precioGasoleoB = precioGasoleoB;
    }

    public String getPrecioGasoleoPremium() {
        return precioGasoleoPremium;
    }

    public void setPrecioGasoleoPremium(String precioGasoleoPremium) {
        this.precioGasoleoPremium = precioGasoleoPremium;
    }

    public String getPrecioGasolina95E10() {
        return precioGasolina95E10;
    }

    public void setPrecioGasolina95E10(String precioGasolina95E10) {
        this.precioGasolina95E10 = precioGasolina95E10;
    }

    public String getPrecioGasolina95E5() {
        return precioGasolina95E5;
    }

    public void setPrecioGasolina95E5(String precioGasolina95E5) {
        this.precioGasolina95E5 = precioGasolina95E5;
    }

    public String getPrecioGasolina95E5Premium() {
        return precioGasolina95E5Premium;
    }

    public void setPrecioGasolina95E5Premium(String precioGasolina95E5Premium) {
        this.precioGasolina95E5Premium = precioGasolina95E5Premium;
    }

    public String getPrecioGasolina98E10() {
        return precioGasolina98E10;
    }

    public void setPrecioGasolina98E10(String precioGasolina98E10) {
        this.precioGasolina98E10 = precioGasolina98E10;
    }

    public String getPrecioGasolina98E5() {
        return recioGasolina98E5;
    }

    public void setPrecioGasolina98E5(String recioGasolina98E5) {
        this.recioGasolina98E5 = recioGasolina98E5;
    }

    public String getPrecioHidrogeno() {
        return precioHidrogeno;
    }

    public void setPrecioHidrogeno(String precioHidrogeno) {
        this.precioHidrogeno = precioHidrogeno;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getRemision() {
        return remision;
    }

    public void setRemision(String remision) {
        this.remision = remision;
    }

    public String getRotulo() {
        return rotulo;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }

    public String getPorcentajeBioEtanol() {
        return porcentajeBioEtanol;
    }

    public void setPorcentajeBioEtanol(String porcentajeBioEtanol) {
        this.porcentajeBioEtanol = porcentajeBioEtanol;
    }

    public String getPorcentajeEsterMetilico() {
        return porcentajeEsterMetilico;
    }

    public void setPorcentajeEsterMetilico(String porcentajeEsterMetilico) {
        this.porcentajeEsterMetilico = porcentajeEsterMetilico;
    }

    public String getIDEESS() {
        return IDEESS;
    }

    public void setIDEESS(String IDEESS) {
        this.IDEESS = IDEESS;
    }

    public String getIDMunicipio() {
        return IDMunicipio;
    }

    public void setIDMunicipio(String IDMunicipio) {
        this.IDMunicipio = IDMunicipio;
    }

    public String getIDProvincia() {
        return IDProvincia;
    }

    public void setIDProvincia(String IDProvincia) {
        this.IDProvincia = IDProvincia;
    }

    public String getIDCCAA() {
        return IDCCAA;
    }

    public void setIDCCAA(String IDCCAA) {
        this.IDCCAA = IDCCAA;
    }

    private static double toDouble(String str) {
        return parseDouble(str.replace(',', '.'));
    }


    public List<CombustibleGasolinera> obtenerCombustibles() {
        List<CombustibleGasolinera> lCombustibles = new ArrayList<>();
        double lat = toDouble(latitud);
        double lon = toDouble(longitud);
        if (!precioBiodiesel.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 8, toDouble(precioBiodiesel)));
        }
        if (!precioBioetanol.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 16, toDouble(precioBioetanol)));
        }
        if (!precioGasNaturalComprimido.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 18, toDouble(precioGasNaturalComprimido)));
        }
        if (!precioGasNaturalLicuado.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 19, toDouble(precioGasNaturalLicuado)));
        }
        if (!precioGasesLicuadosDelPetroleo.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 17, toDouble(precioGasesLicuadosDelPetroleo)));
        }
        if (!precioGasoleoA.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 4, toDouble(precioGasoleoA)));
        }
        if (!precioGasoleoB.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 6, toDouble(precioGasoleoB)));
        }
        if (!precioGasoleoPremium.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 5, toDouble(precioGasoleoPremium)));
        }
        if (!precioGasolina95E10.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 23, toDouble(precioGasolina95E10)));
        }
        if (!precioGasolina95E5.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 1, toDouble(precioGasolina95E5)));
        }
        if (!precioGasolina95E5Premium.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 20, toDouble(precioGasolina95E5Premium)));
        }
        if (!precioGasolina98E10.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 21, toDouble(precioGasolina98E10)));
        }
        if (!precioGasolina95E5.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 3, toDouble(precioGasolina95E5)));
        }
        if (!precioHidrogeno.isEmpty()) {
            lCombustibles.add(new CombustibleGasolinera(lat, lon, 22, toDouble(precioHidrogeno)));
        }
        return lCombustibles;
    }

    public Gasolinera obtenerGasolinera() {
        return new Gasolinera(toDouble(latitud), toDouble(longitud), Integer.parseInt(CP), direccion, horario, localidad, municipio, provincia, rotulo);
    }
}
