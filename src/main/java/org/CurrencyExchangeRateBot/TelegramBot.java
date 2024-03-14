package org.CurrencyExchangeRateBot;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

public class TelegramBot extends TelegramLongPollingBot {
    @Override
    public String getBotToken() {
        return "6763227245:AAFuiHg27Wj4HsGRqBl5wkc8E6gcWnzSr8g";
    }

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return "Currency_Changes_bot";
    }
}
