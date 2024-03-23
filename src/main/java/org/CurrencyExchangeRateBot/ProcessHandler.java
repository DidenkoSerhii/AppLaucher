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

    public void callbackQuery(String callbackData, long chatIdBackQuery) {
        switch (callbackData) {

            case " НАЛАШТУВАННЯ", " НАЗАД" -> botCommands.settingsMessage(chatIdBackQuery);
        }
    }
}
