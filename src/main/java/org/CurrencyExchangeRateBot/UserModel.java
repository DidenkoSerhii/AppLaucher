package org.CurrencyExchangeRateBot;

public class UserModel {
    private final String currency;
    private final String bank;
    private final String time;
    private int number;

    public UserModel() {
        this.currency = "USD";
        this.bank = "НБУ";
        this.time = "Вимкнути сповіщення";
        this.number = 2;
    }

    public String getCurrency() {
        return currency;
    }

    public String getBank() {
        return bank;
    }

    public String getTime() {
        return time;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
