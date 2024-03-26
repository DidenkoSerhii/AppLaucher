package org.CurrencyExchangeRateBot;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class UserModel {

    private final Set<String> selectedCurrencies;
    private String bank;
    private String time;
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


    public boolean isCurrencySelected(String currency) {
        return selectedCurrencies.contains(currency);
    }
}