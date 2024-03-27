package org.CurrencyExchangeRateBot.service.utils.commands;


import org.CurrencyExchangeRateBot.banks.CurrencyInfo;
import org.CurrencyExchangeRateBot.service.*;
import org.CurrencyExchangeRateBot.service.utils.*;
import org.CurrencyExchangeRateBot.service.utils.ui.UserModel;
import org.CurrencyExchangeRateBot.service.utils.ui.UserServices;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import static org.CurrencyExchangeRateBot.config.BotConstant.*;

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
        MessageBuilder.sendMessage(chatId, "======МЕНЮ======", Buttons.start());
    }
    public void nbuMessage(long chatId) {
        new BanksButtonsReaction().nbuButton(chatId);
        MessageBuilder.sendMessage(chatId, "======МЕНЮ======", Buttons.start());
    }
    public void monobankMessage(long chatId) {
        new BanksButtonsReaction().monobankButton(chatId);
        MessageBuilder.sendMessage(chatId, "======МЕНЮ======", Buttons.start());
    }
    public void banksMessage(long chatId) {
        MessageBuilder.sendMessage(chatId, "Банк", Buttons.banks(chatId));
    }

    public void currencyMessage(long chatId) {
        MessageBuilder.sendMessage(chatId, "Виберіть валюту", Buttons.currency(chatId));
        MessageBuilder.sendMessage(chatId, "======МЕНЮ======", Buttons.start());
    }

    public void printCourse(long chatId) throws IOException {
        UserModel user = UserServices.getUserSettings(chatId);
        List<CurrencyInfo> currencies = getCurrencies(user);

        if (currencies == null) {
            MessageBuilder.sendMessage(chatId, "Вибачте, виникла помилка з отриманням інформації від банку", Buttons.start());
        } else {
            String textForMessage = getCourse(currencies, user);

            if (textForMessage.isEmpty()) {
                MessageBuilder.sendMessage(chatId, "Жодної валюти не обрано", Buttons.start());
            } else {
                MessageBuilder.sendMessage(chatId, textForMessage, Buttons.start());
            }
        }
    }

    public void changeQuantityOfNumbers(String callbackData, long chatId) {
        UserServices.getUserSettings(chatId).setNumber(Integer.parseInt(callbackData.trim()));
        MessageBuilder.sendMessage(chatId, "======МЕНЮ======", Buttons.start());
    }

    public List<CurrencyInfo> getCurrencies(UserModel user) throws IOException {
        List<CurrencyInfo> currencies = new ArrayList<>();
        if(user.getBank().contains("Монобанк")) {
            currencies = CurrencyInfo.getInfoFromBank(MONO_API);
        } else if(user.getBank().contains("Приватбанк")) {
            currencies = CurrencyInfo.getInfoFromBank(PRIVAT_API);
        } else if (user.getBank().contains("НБУ")) {
            currencies = CurrencyInfo.getInfoFromBank(NBU_API);
        }
        return currencies;
    }

    public String getCourse(List<CurrencyInfo> currencies, UserModel user) {
        StringBuilder course = new StringBuilder();
        for (CurrencyInfo currency : currencies) {
            currency.refactorCurrencyInfo();
            if(user.getSelectedCurrencies().contains(currency.getCurrency())) {
                course.append("Курс в ").append(user.getBank()).append(": ").append(currency.getCurrency()).append("/UAH\nКупівля: ").
                        append(new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getBuy())).
                        append("\nПродаж: ").
                        append(new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getSale())).append("\n");
            }
        }
        return String.valueOf(course);
    }
    public void changeTimeOfNotifycation(String callbackData, long chatId) {
        UserServices.getUserSettings(chatId).setTime(callbackData);
        MessageBuilder.sendMessage(chatId, "Сповіщення встанавлено на "+callbackData, Buttons.start());
        TimerForMessage.startTimer(callbackData, chatId, this);
    }

    public void turnOffNotifycation(long chatId) {
        UserServices.getUserSettings(chatId).setTime("Вимкнути сповіщення");
        MessageBuilder.sendMessage(chatId, "Сповіщення вимкнено", Buttons.start());
        TimerForMessage.stopTimer();
    }

}