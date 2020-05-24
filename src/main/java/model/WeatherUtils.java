package model;

import java.util.HashMap;
import java.util.Map;

public class WeatherUtils {
    public final static Map<String, String> weatherIconsCodes = new HashMap<>();

    static {
        weatherIconsCodes.put("Ясно", "☀");
        weatherIconsCodes.put("Дождь", "☔");
        weatherIconsCodes.put("Снег", "❄");
        weatherIconsCodes.put("Облачно", "☁");
    }
}
