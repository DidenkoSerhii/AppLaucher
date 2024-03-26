package org.CurrencyExchangeRateBot;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import static org.CurrencyExchangeRateBot.BotConstant.*;

public class BotCommands {
     final SendMessage sendMessage;
     final EditMessage editMessage;
     final MessageBuilder messageBuilder;



    public BotCommands(TelegramBot telegramBot) {
        this.sendMessage = new SendMessage();
        this.editMessage = new EditMessage(telegramBot);
        this.messageBuilder = new MessageBuilder(telegramBot);
    }

    public void start(long chatId) {
        UserServices.createUserSettings(chatId);

        MessageBuilder.sendMessage(chatId, "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!", Buttons.start());
    }

    public void settingsMessage(long chatId) {
        MessageBuilder.sendMessage(chatId, "Налаштування", Buttons.setting());
    }
    public void privatbankMessage(long chatId) {
        new BanksButtonsReaction().privatbankButton(chatId);
        MessageBuilder.sendMessage(chatId, "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!", Buttons.start());
    }
    public void nbuMessage(long chatId) {
        new BanksButtonsReaction().nbuButton(chatId);
        MessageBuilder.sendMessage(chatId, "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!", Buttons.start());
    }
    public void monobankMessage(long chatId) {
        new BanksButtonsReaction().monobankButton(chatId);
        MessageBuilder.sendMessage(chatId, "Ласкаво просимо. Цей бот допоможе відслідковувати актуальні курси валют!", Buttons.start());
    }
    public void banksMessage(long chatId) {
        MessageBuilder.sendMessage(chatId, "Банк", Buttons.banks(chatId));
    }

    public void currencyMessage(long chatId) {
        MessageBuilder.sendMessage(chatId, "Виберіть валюту", Buttons.currency(chatId));
    }

    public void getInfo(long chatId) throws IOException {
        UserModel user = UserServices.getUserSettings(chatId);
        List<CurrencyInfo> currencies = null;

        if(user.getBank().contains("Монобанк")) {
            currencies = CurrencyInfo.getInfoFromBank(MONO_API);
        } else if(user.getBank().contains("Приватбанк")) {
            currencies = CurrencyInfo.getInfoFromBank(PRIVAT_API);
        } else if (user.getBank().contains("НБУ")) {
            currencies = CurrencyInfo.getInfoFromBank(NBU_API);
        }

        if (currencies == null) {
            MessageBuilder.sendMessage(chatId, "Вибачте, виникла помилка з отриманням інформації від банку", Buttons.start());
        } else {

            StringBuilder textForMessage = new StringBuilder();
            for (CurrencyInfo currency : currencies) {
                currency.refactorCurrencyInfo();
                if(user.getSelectedCurrencies().contains(currency.getCurrency())) {
                    textForMessage.append("Курс в ").append(user.getBank()).append(": ").append(currency.getCurrency()).append("/UAH\nКупівля: ").
                            append(new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getBuy())).
                            append("\nПродаж: ").
                            append(new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getSale())).append("\n");
                }
            }

            if (textForMessage.isEmpty()) {
                MessageBuilder.sendMessage(chatId, "Жодної валюти не обрано", Buttons.start());
            } else {
                MessageBuilder.sendMessage(chatId, String.valueOf(textForMessage), Buttons.start());
            }
        }
    }

}