package org.CurrencyExchangeRateBot;

import org.CurrencyExchangeRateBot.config.BotConstant;
import org.CurrencyExchangeRateBot.service.TelegramBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws TelegramApiException, IOException {
        BotConstant.setBotToken();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new TelegramBot());
    }
}