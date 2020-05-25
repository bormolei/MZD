package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Класс переводчик с русского на английский
 */
public class Translator {
    static String lang = "ru-en";
    static String API_CALL_TEMPLATE = "https://translate.yandex.net/api/v1.5/tr.json/translate?"
            + "key=trnsl.1.1.20200524T151240Z.081edfd806911c78.179dd2cd66560fe2870cb89d29850591abdfe000"
            + "&text=";
    static String API_KEY_TEMPLATE = "&lang=" + lang;
    private final static String USER_AGENT = "Mozilla/5.0";

    /**
     * Метод перевода из русского на английский
     * @param text город для поиска
     * @return переведенное значение
     */
    public static String downloadJsonRawData(String text) throws Exception {

        String urlString = API_CALL_TEMPLATE + text + API_KEY_TEMPLATE;
        URL urlObject = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = connection.getResponseCode();
        if (responseCode == 404) {
            throw new IllegalArgumentException();
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String s =response.toString();
        String[] str = s.split("\"");

        return str[9];
    }


}
