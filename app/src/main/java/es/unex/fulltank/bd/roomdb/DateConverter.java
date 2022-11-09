package es.unex.fulltank.bd.roomdb;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * Esta es una clase de conversión de tipos, definida para que la utilice Room.
 * @author Grupo PGD02
 * @version 1.0
 */
public class DateConverter {
    /**
     * Convierte una Date a un timestamp
     * @param date La fecha a convertir.
     * @return La fecha convertida a timestamp
     */
    @TypeConverter
    public static Long toTimestamp(Date date){
        return date == null ? null : date.getTime();
    }

    /**
     * Convierte un timestamp a una Date.
     * @param timestamp El timestamp a convertir.
     * @return El timestamp convertido a fecha
     */
    @TypeConverter
    public static Date toDate(Long timestamp){
        return timestamp == null ? null : new Date(timestamp);
    }
}
