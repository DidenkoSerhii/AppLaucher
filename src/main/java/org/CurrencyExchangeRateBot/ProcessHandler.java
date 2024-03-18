package org.CurrencyExchangeRateBot;

public class ProcessHandler {

    private final BotCommands botCommands;

    public ProcessHandler(TelegramBot telegramBot) {
        this.botCommands = new BotCommands(telegramBot);
    }

    public void message(String messageText, String username, long chatId) {
        switch (messageText) {
            case "/start" -> botCommands.start(chatId);
            case "/info" -> botCommands.infoMessage(chatId);
            case "/setting" -> botCommands.settingsMessage(chatId);
        }


        Log.Info(username, messageText);
    }


    public void callbackQuery(String callbackData, String userName, long chatIdBackQuery, long messageId) {
        switch (callbackData) {
            case " ОТРИМАТИ ІНФО" -> botCommands.infoMessage(chatIdBackQuery);
            case " НАЛАШТУВАННЯ", " НАЗАД" -> botCommands.settingsMessage(chatIdBackQuery);
        }
    }
}