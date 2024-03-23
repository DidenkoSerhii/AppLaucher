package org.CurrencyExchangeRateBot.Privat24;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PrivatJson {
    private String ccy;
    private String buy;
    private String sale;

    public String getCcy() {
        return ccy;
    }

    public float getBuy() {
        return Float.parseFloat(buy);
    }

    public float getSale() {
        return Float.parseFloat(sale);
    }
}
