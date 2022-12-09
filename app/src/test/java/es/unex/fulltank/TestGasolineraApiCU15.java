package es.unex.fulltank;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import es.unex.fulltank.apiRest.interfaces.GasolineraAPI;
import es.unex.fulltank.apiRest.modelos.Gasolinera;
import es.unex.fulltank.apiRest.modelos.RespuestaAPI;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestGasolineraApiCU15 {

    private String cambiarJson(String json) {
        json = json.replace("nota", "Nota");
        json = json.replace("resultadoConsulta", "ResultadoConsulta");
        json = json.replace("listaGasolineras", "ListaEESSPrecio");
        json = json.replace("fecha", "Fecha");

        json = json.replace("CP", "C.P.");
        json = json.replace("direccion", "Dirección");
        json = json.replace("horario", "Horario");
        json = json.replace("latitud", "Latitud");
        json = json.replace("localidad", "Localidad");
        json = json.replace("longitud", "Longitud (WGS84)");
        json = json.replace("margen", "Margen");
        json = json.replace("municipio", "Municipio");
        json = json.replace("precioBiodiesel", "Precio Biodiesel");
        json = json.replace("precioBioetanol", "Precio Bioetanol");
        json = json.replace("precioGasNaturalComprimido", "Precio Gas Natural Comprimido");
        json = json.replace("precioGasNaturalLicuado", "Precio Gas Natural Licuado");
        json = json.replace("precioGasesLicuadosDelPetroleo", "Precio Gases licuados del petróleo");
        json = json.replace("precioGasoleoA", "Precio Gasoleo A");
        json = json.replace("precioGasoleoB", "Precio Gasoleo B");
        json = json.replace("precioGasoleoPremium", "Precio Gasoleo Premium");
        json = json.replace("precioGasolina95E10", "Precio Gasolina 95 E10");
        json = json.replace("precioGasolina95E5", "Precio Gasolina 95 E5");
        json = json.replace("precioGasolina98E10", "Precio Gasolina 98 E10");
        json = json.replace("precioGasolina98E5", "Precio Gasolina 98 E5");
        json = json.replace("precioHidrogeno", "Precio Hidrogeno");
        json = json.replace("provincia", "Provincia");
        json = json.replace("remision", "Remisión");
        json = json.replace("rotulo", "Rótulo");
        json = json.replace("tipoVenta", "Tipo Venta");
        json = json.replace("porcentajeBioEtanol", "% BioEtanol");
        json = json.replace("porcentajeEsterMetilico", "% Éster metílico");
        json = json.replace("IDEESS", "IDEESS");
        json = json.replace("IDMunicipio", "IDMunicipio");
        json = json.replace("IDProvincia", "IDProvincia");
        json = json.replace("IDCCAA", "IDCCAA");
        return json;
    }

    @Test
    public void testGetGasolineras() throws IOException {

        //Step1: we create Gasolineras list
        ArrayList<Gasolinera> lGasolineras = new ArrayList<>();
        Gasolinera g0 = new Gasolinera();
        Gasolinera g1 = new Gasolinera();
        g0.setLatitud("1,1");
        g0.setLongitud("1,1");
        g1.setLatitud("2,2");
        g1.setLongitud("2,2");

        lGasolineras.add(g0);
        lGasolineras.add(g1);

        RespuestaAPI respAux = new RespuestaAPI();
        respAux.setFecha("20-11-2003");
        respAux.setNota("probando");
        respAux.setResultadoConsulta("buenResultado");
        respAux.setListaGasolineras(lGasolineras);
        assertNotNull(lGasolineras);
        assertEquals(2, lGasolineras.size());

        //Step2: Define a mapper Object to JSON
        ObjectMapper objectMapper = new ObjectMapper();

        //Step3: we create a mock server, independent of the real server
        MockWebServer mockWebServer = new MockWebServer();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Log.d("TESTINGG", objectMapper.writeValueAsString(respAux));

        //Step4: we create a standard response to any request (in this case, only getCenters request will be performed)
        MockResponse mockedResponse = new MockResponse();
        mockedResponse.setResponseCode(200);

        String json = objectMapper.writeValueAsString(respAux);

        mockedResponse.setBody(cambiarJson(json));
        mockWebServer.enqueue(mockedResponse);

        //Step5: we link the mock server with our retrofit api
        GasolineraAPI service = retrofit.create(GasolineraAPI.class);

        //Step6: we create the call to get Gasolineras
        Call<RespuestaAPI> call = service.obtenerGasolineras();
        // and we execute the call
        Response<RespuestaAPI> response = call.execute();

        //Step7: let's check that the call is executed
        assertNotNull(response);
        assertTrue(response.isSuccessful());
        assertEquals(200, response.code());

        //Step8bis: check the body content
        RespuestaAPI respuesta = response.body();
        assertNotNull(respuesta);
        assertEquals("probando", respuesta.getNota());
        assertEquals("buenResultado", respuesta.getResultadoConsulta());
        assertEquals("20-11-2003", respuesta.getFecha());

        ArrayList<Gasolinera> listaRespuesta = respuesta.getListaGasolineras();
        assertNotNull(listaRespuesta);
        assertFalse(listaRespuesta.isEmpty());
        assertTrue(listaRespuesta.size() == 2);
        //IMPORTANT: We need to have implemented "equals" in Center class to perform this assert
        assertEquals(g0.getLatitud(), listaRespuesta.get(0).getLatitud());
        assertEquals(g0.getLatitud(), listaRespuesta.get(0).getLongitud());
        assertEquals(g1.getLatitud(), listaRespuesta.get(1).getLatitud());
        assertEquals(g1.getLatitud(), listaRespuesta.get(1).getLongitud());

        //Step9: Finish web server
        mockWebServer.shutdown();
    }
}
