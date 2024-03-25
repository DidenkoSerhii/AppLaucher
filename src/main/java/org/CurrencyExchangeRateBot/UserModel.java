package org.CurrencyExchangeRateBot;

public class UserModel {

    private String currency;
    private String bank;
    private String time;
    private String number;

    public String getBank() {
        return bank;
    }
    public void setBank(String bank) {
        this.bank = bank;
    }

    public UserModel() {
        this.currency = "USD";
        this.bank = "Приватбанк";
        this.time = "Вимкнути сповіщення";
        this.number = "2";
    }
}