package org.CurrencyExchangeRateBot.service.utils;

import org.CurrencyExchangeRateBot.service.utils.ui.UserServices;

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
