package org.CurrencyExchangeRateBot;

import java.util.HashSet;
import java.util.Set;

public class UserModel {

        Set<String> selectedCurrencies;
        final String currency;
        String bank;
        final String time;
        final String number;

    public UserModel() {
        this.selectedCurrencies = new HashSet<>();
        this.currency = "USD";
        this.bank = "Приват";
        this.time = "Вимкнути сповіщення";
        this.number = "2";
    }

    public void toggleCurrency(String currency) {
        if (!selectedCurrencies.remove(currency)) {
            selectedCurrencies.add(currency);
        }
    }

    public boolean isCurrencySelected(String currency) {
        return selectedCurrencies.contains(currency);
    }
}