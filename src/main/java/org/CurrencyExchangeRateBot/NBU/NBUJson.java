package org.CurrencyExchangeRateBot.NBU;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NBUJson {
    private String cc;
    private float rate;

    public String getCc() {
        return cc;
    }

    public float getRate() {
        return rate;
    }
}
