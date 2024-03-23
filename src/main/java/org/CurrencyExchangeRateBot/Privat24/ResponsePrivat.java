package org.CurrencyExchangeRateBot.Privat24;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

public class ResponsePrivat {
    public static List<PrivatJson> get(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");


        if(connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseJson = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                responseJson.append(line);
            }

            ObjectMapper mapper = new ObjectMapper();
            try {
                return mapper.readValue(String.valueOf(responseJson), new TypeReference<>() {});

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //TODO
        }
        return null;
    }
}
