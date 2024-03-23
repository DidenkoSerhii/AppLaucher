package org.CurrencyExchangeRateBot;

public class ProcessHandler {

    private final BotCommands botCommands;

    public ProcessHandler(TelegramBot telegramBot) {
        this.botCommands = new BotCommands(telegramBot);
    }

    public void message(String messageText, String username, long chatId) {
        switch (messageText) {
            case "/start" -> botCommands.start(chatId);

            case "/setting" -> botCommands.settingsMessage(chatId);
        }


        Log.Info(username, messageText);
    }

    public void callbackQuery(String callbackData, long chatIdBackQuery) {
        switch (callbackData) {

            case " НАЛАШТУВАННЯ", " НАЗАД" -> botCommands.settingsMessage(chatIdBackQuery);
            case " БАНК" -> botCommands.banksMessage(chatIdBackQuery);

            case "МОНОБАНК" -> botCommands.monobankMessage(chatIdBackQuery);
            case "НБУ" -> botCommands.nbuMessage(chatIdBackQuery);
            case "ПРИВАТБАНК" -> botCommands.privatbankMessage(chatIdBackQuery);
        }
    }
}