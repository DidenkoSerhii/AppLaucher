package org.CurrencyExchangeRateBot;

public class UserModel {

    private String bank;
        private String currency;
        private String time;
        private String number;

    public UserModel() {
        this.currency = "USD";
        this.bank = "Приват";
        this.time = "Вимкнути сповіщення";
        this.number = "2";
    }
}