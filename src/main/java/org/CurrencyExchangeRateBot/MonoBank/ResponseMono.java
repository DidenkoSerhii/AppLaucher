package org.CurrencyExchangeRateBot.MonoBank;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ResponseMono {
    public static List<MonoJson> get(String urlString) throws IOException {
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
            List<MonoJson> responseList = mapper.readValue(String.valueOf(responseJson), new TypeReference<>() {});
            List<MonoJson> USDAndEURList = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                USDAndEURList.add(responseList.get(i));
            }
            return USDAndEURList;

        }
        return null;
    }
}
