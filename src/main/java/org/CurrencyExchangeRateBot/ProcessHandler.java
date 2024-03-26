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

            case "/info" -> botCommands.printCourse(chatId);
        }


        Log.Info(username, messageText);
    }

    public void callbackQuery(String callbackData, long chatIdBackQuery) throws IOException {
        switch (callbackData) {

            case " НАЛАШТУВАННЯ", " НАЗАД" -> botCommands.settingsMessage(chatIdBackQuery);
            case " БАНК" -> botCommands.banksMessage(chatIdBackQuery);

            case "МОНОБАНК" -> botCommands.monobankMessage(chatIdBackQuery);
            case "НБУ" -> botCommands.nbuMessage(chatIdBackQuery);
            case "ПРИВАТБАНК" -> botCommands.privatbankMessage(chatIdBackQuery);
            case " ВАЛЮТА" -> botCommands.currencyMessage(chatIdBackQuery);
            case " ОТРИМАТИ ІНФО" -> botCommands.printCourse(chatIdBackQuery);
            case " КІЛЬКІСТЬ ЗНАКІВ ПІСЛЯ КОМИ" -> MessageBuilder.sendMessage(chatIdBackQuery, "Оберіть кількість знаків після коми", Buttons.quantityOfNumbers(chatIdBackQuery));
            case "2", "3", "4" -> botCommands.changeQuantityOfNumbers(callbackData, chatIdBackQuery);
            default -> {
                if (callbackData.equals("USD") || callbackData.equals("EUR")) {
                    UserServices.getUserSettings(chatIdBackQuery).toggleCurrency(callbackData);
                    botCommands.currencyMessage(chatIdBackQuery);
                }
            }
        }
    }
}