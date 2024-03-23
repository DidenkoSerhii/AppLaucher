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

    public void callbackQuery(String callbackData, long chatIdBackQuery) throws IOException {
        switch (callbackData) {

            case " НАЛАШТУВАННЯ", " НАЗАД" -> botCommands.settingsMessage(chatIdBackQuery);

            case " ОТРИМАТИ ІНФО" -> botCommands.getInfo(chatIdBackQuery);

            case " КІЛЬКІСТЬ ЗНАКІВ ПІСЛЯ КОМИ" -> MessageBuilder.sendMessage(chatIdBackQuery, "Оберіть кількість знаків після коми", Buttons.changeQuantityOfNumbers());

            case " 2", " 3", " 4" -> botCommands.changeQuantityOfNumbers(callbackData, chatIdBackQuery);
        }
    }
}
