package org.CurrencyExchangeRateBot;


import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

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

}