package org.CurrencyExchangeRateBot;

import java.io.IOException;

public class ProcessHandler {

    private final BotCommands botCommands;

    public ProcessHandler(TelegramBot telegramBot) {
        this.botCommands = new BotCommands(telegramBot);
    }

    public void message(String messageText, String username, long chatId) throws IOException {
        switch (messageText) {
            case "/start" -> botCommands.start(chatId);

            case "/setting" -> botCommands.settingsMessage(chatId);

            case "/info" -> botCommands.getInfo(chatId);
        }


        Log.Info(username, messageText);
    }

    public void callbackQuery(String callbackData, long chatIdBackQuery, long chatId) throws IOException {
        switch (callbackData) {

            case " НАЛАШТУВАННЯ", " НАЗАД" -> botCommands.settingsMessage(chatIdBackQuery);
            case " БАНК" -> botCommands.banksMessage(chatIdBackQuery);

            case "МОНОБАНК" -> botCommands.monobankMessage(chatIdBackQuery);
            case "НБУ" -> botCommands.nbuMessage(chatIdBackQuery);
            case "ПРИВАТБАНК" -> botCommands.privatbankMessage(chatIdBackQuery);
            case " ВАЛЮТА" -> botCommands.currencyMessage(chatId);
            case " ОТРИМАТИ ІНФО" -> botCommands.getInfo(chatIdBackQuery);
            default -> {
                if (callbackData.equals("USD") || callbackData.equals("EUR")) {
                    UserServices.getUserSettings(chatId).toggleCurrency(callbackData);
                    botCommands.currencyMessage(chatId);
                }
            }
        }
    }
}