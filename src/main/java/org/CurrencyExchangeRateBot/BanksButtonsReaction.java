package org.CurrencyExchangeRateBot;

public class BanksButtonsReaction {
    public void privatbankButton(long chatId) {
        UserServices.changeBank(chatId, "Приват");
    }
    public void nbuButton(long chatId) {
        UserServices.changeBank(chatId, "НБУ");
    }
    public void monobankButton(long chatId) {
        UserServices.changeBank(chatId, "Монобанк");
    }
}