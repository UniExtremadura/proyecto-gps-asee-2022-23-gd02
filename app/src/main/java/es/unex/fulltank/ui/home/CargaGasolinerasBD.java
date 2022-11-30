package es.unex.fulltank.ui.home;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import android.content.Context;

import java.util.ArrayList;

import es.unex.fulltank.AppExecutors;
import es.unex.fulltank.apiRest.modelos.Gasolinera;
import es.unex.fulltank.bd.elembd.CombustibleGasolinera;
import es.unex.fulltank.bd.roomdb.BD;

public class CargaGasolinerasBD {
    /**
     * Para cada gasolinera de la lista de las gasolineras de la API:
     * Coge una instancia de la clase Gasolinera de la Api, la transforma a la clase Gasolinera
     * de la BD y la introduce en la BD. También se introducen los datos de los combustibles asociados
     * a esa gasolinera.
     */
    public static void meterGasolinerasEnBD(ArrayList<Gasolinera> gasolinerasApi, Context context) {
        BD instanceBD = BD.getInstance(context);
        for (Gasolinera gApi : gasolinerasApi) {
            double latitud = parseDouble(gApi.getLatitud().replace(',', '.'));
            double longitud = parseDouble(gApi.getLongitud().replace(',', '.'));
            es.unex.fulltank.bd.elembd.Gasolinera gBD = new es.unex.fulltank.bd.elembd.Gasolinera(
                    latitud,
                    longitud,
                    parseInt(gApi.getCP()),
                    gApi.getDireccion(),
                    gApi.getHorario(),
                    gApi.getLocalidad(),
                    gApi.getMunicipio(),
                    gApi.getProvincia(),
                    gApi.getRotulo());

            AppExecutors.getInstance().diskIO().execute(() -> {
                instanceBD.getGasolineraDao().insert(gBD);
                insertarCombustibleEnBD(instanceBD, latitud, longitud, gApi);
            });
        }
    }

    /**
     * Los datos de los combustibles se insertan en la BD
     */
    private static void insertarCombustibleEnBD(BD instanceBD, double latitud, double longitud, Gasolinera gApi) {
        if (!gApi.getPrecioBiodiesel().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 8, toDouble(gApi.getPrecioBiodiesel()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioBioetanol().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 16, toDouble(gApi.getPrecioBioetanol()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioGasNaturalComprimido().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 18, toDouble(gApi.getPrecioGasNaturalComprimido()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioGasNaturalLicuado().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 19, toDouble(gApi.getPrecioGasNaturalLicuado()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioGasesLicuadosDelPetroleo().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 17, toDouble(gApi.getPrecioGasesLicuadosDelPetroleo()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioGasoleoA().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 4, toDouble(gApi.getPrecioGasoleoA()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioGasoleoB().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 6, toDouble(gApi.getPrecioGasoleoB()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioGasoleoPremium().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 5, toDouble(gApi.getPrecioGasoleoPremium()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioGasolina95E10().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 23, toDouble(gApi.getPrecioGasolina95E10()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioGasolina95E5().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 1, toDouble(gApi.getPrecioGasolina95E5()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioGasolina95E5Premium().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 20, toDouble(gApi.getPrecioGasolina95E5Premium()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioGasolina98E10().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 21, toDouble(gApi.getPrecioGasolina98E10()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioGasolina98E5().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 3, toDouble(gApi.getPrecioGasolina98E5()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
        if (!gApi.getPrecioHidrogeno().isEmpty()) {
            CombustibleGasolinera tp = new CombustibleGasolinera(latitud, longitud, 22, toDouble(gApi.getPrecioHidrogeno()));
            instanceBD.getCombustibleGasolineraDao().insert(tp);
        }
    }

    private static double toDouble(String str) {
        return parseDouble(str.replace(',', '.'));
    }
}
