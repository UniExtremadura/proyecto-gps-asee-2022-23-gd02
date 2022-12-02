package es.unex.fulltank.ui.filtrarGasolineras;

import java.util.HashMap;
import java.util.Map;

public enum EnumCombustible {
    GASOLINA_95_E5("Gasolina 95 E5", 1),
    GASOLINA_98_E5("Gasolina 98 E5", 3),
    GASOLEO_A("Gasoleo A habitual", 4),
    GASOLEO_PREMIUM("Gasoleo Premium", 5),
    GASOLEO_B("Gasoleo B", 6),
    GASOLEO_C("Gasoleo C", 7),
    BIODIESEL("Biodiesel", 8),
    BIOETANOL("Bioetanol", 16),
    GASES_LICUADOS("Gases licuados del petroleo", 17),
    GAS_NATURAL_COMPRIMIDO("Gases natural comprimido", 18),
    GAS_NATURAL_LICUADO("Gases natural licuado", 19),
    GASOLINA_95_E5_PREMIUM("Gasolina 95 E5 Premium", 20),
    GASOLINA_98_E10("Gasolina 98 E10", 21),
    HIDROGENO("Hidrogeno", 22),
    GASOLINA_95_E10("Gasolina 95 E10", 23);

    private static final Map<Integer, EnumCombustible> MAP = new HashMap<>();
    private String nombreCombutible;
    private int id;

    private EnumCombustible(String nombreCombustible, int id) {
        this.nombreCombutible = nombreCombustible;
        this.id = id;
    }

    public String getNombreCombutible() {
        return nombreCombutible;
    }

    public int getId() {
        return id;
    }

    public static EnumCombustible getEnum(int id) {
        return MAP.get(id);
    }

    public static int getIdCombustible(String nombreCombutible) {
        int id = 0;
        switch (nombreCombutible) {
            case "Gasolina 95 E5":
                id = 1;
                break;
            case "Gasolina 98 E5":
                id = 3;
                break;
            case "Gasoleo A habitual":
                id = 4;
                break;
            case "Gasoleo Premium":
                id = 5;
                break;
            case "Gasoleo B":
                id = 6;
                break;
            case "Gasoleo C":
                id = 7;
                break;
            case "Biodiesel":
                id = 8;
                break;
            case "Bioetanol":
                id = 16;
                break;
            case "Gases licuados del petroleo":
                id = 17;
                break;
            case "Gases natural comprimid":
                id = 18;
                break;
            case "Gases natural licuado":
                id = 19;
                break;
            case "Gasolina 95 E5 Premium":
                id = 20;
                break;
            case "Gasolina 98 E10":
                id = 21;
                break;
            case "Hidrogeno":
                id = 22;
                break;
            case "Gasolina 95 E10":
                id = 23;
                break;
        }
        return id;
    }

}
