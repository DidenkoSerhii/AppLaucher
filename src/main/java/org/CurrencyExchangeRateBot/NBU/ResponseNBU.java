package org.CurrencyExchangeRateBot.NBU;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ResponseNBU {
    public static List<NBUJson> get(String urlString) throws IOException {
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
                List<NBUJson> responseList = mapper.readValue(String.valueOf(responseJson), new TypeReference<>() {});
                List<NBUJson> USDAndEURList = new ArrayList<>();
                for (NBUJson currency : responseList) {
                    if(currency.getCc().equals("USD") || currency.getCc().equals("EUR")) {
                        USDAndEURList.add(currency);
                    }
                }
                return USDAndEURList;

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            //TODO
        }
        return null;
    }
}
