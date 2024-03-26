package org.CurrencyExchangeRateBot;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyInfo {
    @JsonProperty("currencyCodeA")
    private int currencyCodeA;
    @JsonProperty("currencyCodeB")
    private int currencyCodeB;
    @JsonAlias({"cc", "ccy"})
    private String currency;
    @JsonAlias({"rate", "rateBuy"})
    private String buy;
    @JsonAlias({"rateSell", "sale"})
    private String sale;
    private static final ObjectMapper mapper = new ObjectMapper();

    public void refactorCurrencyInfo() {
        if(currency == null) {

            if(currencyCodeA == 840 && currencyCodeB == 980) {
                currency = "USD";
            } else if(currencyCodeA == 978 && currencyCodeB == 980) {
                currency = "EUR";
            } else {
                currency = "Not a currency";
            }

        } else if(sale == null) {
            sale = buy;
        }

    }

    public static List<CurrencyInfo> getInfoFromBank(String bankAPI) throws IOException {
        URL url = new URL(bankAPI);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");


        if(connection.getResponseCode() == HttpsURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseJson = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                responseJson.append(line);
            }
            return mapper.readValue(String.valueOf(responseJson), new TypeReference<>() {});

        }
        return null;
    }

    public float getBuy() {
        return Float.parseFloat(buy);
    }

    public float getSale() {
        return Float.parseFloat(sale);
    }
}
