package org.CurrencyExchangeRateBot.service.utilits;

import org.CurrencyExchangeRateBot.service.utilits.ui.UserServices;

public class BanksButtonsReaction {
    public void privatbankButton(long chatId) {
        UserServices.changeBank(chatId, "Приватбанк");
    }
    public void nbuButton(long chatId) {
        UserServices.changeBank(chatId, "НБУ");
    }
    public void monobankButton(long chatId) {
        UserServices.changeBank(chatId, "Монобанк");
    }
}
