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

    public String getBuy() {
        return buy;
    }

    public String getSale() {
        return sale;
    }
}
