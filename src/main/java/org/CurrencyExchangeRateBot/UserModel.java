package org.CurrencyExchangeRateBot;

public class UserModel {

    final String currency;
        String bank;
        final String time;
        final String number;

    public UserModel() {
        this.currency = "USD";
        this.bank = "Приватбанк";
        this.time = "Вимкнути сповіщення";
        this.number = "2";
    }
}