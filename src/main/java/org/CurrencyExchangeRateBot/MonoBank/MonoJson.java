package org.CurrencyExchangeRateBot.MonoBank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MonoJson {
    @JsonProperty("currencyCodeA")
    private int currencyCode;
    private float rateSell;
    private float rateBuy;

    public int getCurrencyCode() {
        return currencyCode;
    }

    public float getRateSell() {
        return rateSell;
    }

    public float getRateBuy() {
        return rateBuy;
    }
}
