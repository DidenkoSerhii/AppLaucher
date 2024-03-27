package org.CurrencyExchangeRateBot.service.utilits;

import org.CurrencyExchangeRateBot.service.TelegramBot;
import org.CurrencyExchangeRateBot.service.utilits.commands.BotCommands;
import org.CurrencyExchangeRateBot.service.utilits.ui.UserServices;

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
        Log.Info(Long.toString(chatIdBackQuery), callbackData);
        switch (callbackData) {

            case " НАЛАШТУВАННЯ", " НАЗАД" -> botCommands.settingsMessage(chatIdBackQuery);
            case " БАНК" -> botCommands.banksMessage(chatIdBackQuery);

            case "МОНОБАНК" -> botCommands.monobankMessage(chatIdBackQuery);
            case "НБУ" -> botCommands.nbuMessage(chatIdBackQuery);
            case "ПРИВАТБАНК" -> botCommands.privatbankMessage(chatIdBackQuery);
            case " ВАЛЮТА" -> botCommands.currencyMessage(chatIdBackQuery);
            case " ОТРИМАТИ ІНФО" -> botCommands.printCourse(chatIdBackQuery);
            case " ЧАС СПОВІЩЕНЬ" -> MessageBuilder.sendMessage(chatIdBackQuery, "Оберіть час сповіщення про курс", Buttons.timeIntervals(chatIdBackQuery));
            case    " 9:00","10:00","11:00",
                    "12:00","13:00","14:00",
                    "15:00","16:00","17:00",
                    "18:00" -> botCommands.changeTimeOfNotifycation(callbackData, chatIdBackQuery);
            case "ВИМКНУТИ СПОВІЩЕННЯ" -> botCommands.turnOffNotifycation(chatIdBackQuery);
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