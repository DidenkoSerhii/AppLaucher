package org.CurrencyExchangeRateBot;

import java.util.HashSet;
import java.util.Set;

public class UserModel {

        private final Set<String> selectedCurrencies;
        private String bank;
        final String time;
        private int number;

    public UserModel() {
        this.selectedCurrencies = new HashSet<>();
        selectedCurrencies.add("USD");
        this.bank = "Монобанк";
        this.time = "Вимкнути сповіщення";
        this.number = 2;
    }

    public void toggleCurrency(String currency) {
        if (!selectedCurrencies.remove(currency)) {
            selectedCurrencies.add(currency);
        }
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getBank() {
        return bank;
    }

    public int getNumber() {
        return number;
    }

    public Set<String> getSelectedCurrencies() {
        return selectedCurrencies;
    }

    public boolean isCurrencySelected(String currency) {
        return selectedCurrencies.contains(currency);
    }
}