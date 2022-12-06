package es.unex.fulltank.apiRest.modelos;

import com.google.gson.annotations.SerializedName;

/**
 * Clase que define la estructura de un objeto Gasolinera
 * @author Grupo PGD02
 * @version 1.0
 */
public class Gasolinera{

    @SerializedName("C.P.")
    private String CP;
    @SerializedName("Dirección")
    private String direccion;
    @SerializedName("Horario")
    private String horario;
    @SerializedName("Latitud")
    private String latitud;
    @SerializedName("Localidad")
    private String localidad;
    @SerializedName("Longitud (WGS84)")
    private String longitud;
    @SerializedName("Margen")
    private String margen;
    @SerializedName("Municipio")
    private String municipio;
    @SerializedName("Precio Biodiesel")
    private String precioBiodiesel;
    @SerializedName("Precio Bioetanol")
    private String precioBioetanol;
    @SerializedName("Precio Gas Natural Comprimido")
    private String precioGasNaturalComprimido;
    @SerializedName("Precio Gas Natural Licuado")
    private String precioGasNaturalLicuado;
    @SerializedName("Precio Gases licuados del petróleo")
    private String precioGasesLicuadosDelPetroleo;
    @SerializedName("Precio Gasoleo A")
    private String precioGasoleoA;
    @SerializedName("Precio Gasoleo B")
    private String precioGasoleoB;
    @SerializedName("Precio Gasoleo Premium")
    private String precioGasoleoPremium;
    @SerializedName("Precio Gasolina 95 E10")
    private String precioGasolina95E10;
    @SerializedName("Precio Gasolina 95 E5")
    private String precioGasolina95E5;
    @SerializedName("Precio Gasolina 95 E5 Premium")
    private String precioGasolina95E5Premium;
    @SerializedName("Precio Gasolina 98 E10")
    private String precioGasolina98E10;
    @SerializedName("Precio Gasolina 98 E5")
    private String recioGasolina98E5;
    @SerializedName("Precio Hidrogeno")
    private String precioHidrogeno;
    @SerializedName("Provincia")
    private String provincia;
    @SerializedName("Remisión")
    private String remision;
    @SerializedName("Rótulo")
    private String rotulo;
    @SerializedName("Tipo Venta")
    private String tipoVenta;
    @SerializedName("% BioEtanol")
    private String porcentajeBioEtanol;
    @SerializedName("% Éster metílico")
    private String porcentajeEsterMetilico;
    @SerializedName("IDEESS")
    private String IDEESS;
    @SerializedName("IDMunicipio")
    private String IDMunicipio;
    @SerializedName("IDProvincia")
    private String IDProvincia;
    @SerializedName("IDCCAA")
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
}
