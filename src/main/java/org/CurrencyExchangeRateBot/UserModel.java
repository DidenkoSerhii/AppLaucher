package org.CurrencyExchangeRateBot;

public class UserModel {
    final String currency;
    final String bank;
    final String time;
    final String number;

    public UserModel() {
        this.currency = "EUR USD";
        this.bank = "НБУ";
        this.time = "Вимкнути сповіщення";
        this.number = "2";
    }
}
