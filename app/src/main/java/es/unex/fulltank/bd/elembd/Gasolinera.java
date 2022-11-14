package es.unex.fulltank.bd.elembd;

import androidx.annotation.NonNull;
import androidx.room.Entity;


/**
 * La clase Gasolinera contiene los atributos relacionados con una gasolinera de la vida real,
 * como el id y el nombre. Por ejemplo, la gasolinera Repsol, con ID = 3476.
 * Es una clase implementada con anotaciones de Room. Se ha configurado de tal manera que cada atributo
 * de esta clase se mapea a una columna de una tabla de gasolineras de la Base de datos de este
 * proyecto, utilizando anotaciones.
 * @author Grupo PGD02
 * @version 1.0
 */
@Entity(tableName = "tabla_gasolinera", primaryKeys = {"latitud", "longitud"})
public class Gasolinera{
    public Gasolinera(String latitud, String longitud) {
        this.latitud = latitud;
        this.longitud = longitud;
    }


    @NonNull
    private String latitud;

    @NonNull
    private String longitud;

    private String CP;
    private String direccion;
    private String horario;
    private String localidad;
    private String margen;
    private String municipio;
    private String precioBiodiesel;
    private String precioBioetanol;
    private String precioGasNaturalComprimido;
    private String precioGasNaturalLicuado;
    private String precioGasesLicuadosDelPetroleo;
    private String precioGasoleoA;
    private String precioGasoleoB;
    private String precioGasoleoPremium;
    private String precioGasolina95E10;
    private String precioGasolina95E5;
    private String precioGasolina95E5Premium;
    private String precioGasolina98E10;
    private String recioGasolina98E5;
    private String precioHidrogeno;
    private String provincia;
    private String remision;
    private String rotulo;
    private String tipoVenta;
    private String porcentajeBioEtanol;
    private String porcentajeEsterMetilico;
    private String IDEESS;
    private String IDMunicipio;
    private String IDProvincia;
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

    public String getRecioGasolina98E5() {
        return recioGasolina98E5;
    }

    public void setRecioGasolina98E5(String recioGasolina98E5) {
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
