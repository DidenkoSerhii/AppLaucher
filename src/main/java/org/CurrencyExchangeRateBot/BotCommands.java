package org.CurrencyExchangeRateBot;

import org.CurrencyExchangeRateBot.MonoBank.MonoJson;
import org.CurrencyExchangeRateBot.MonoBank.ResponseMono;
import org.CurrencyExchangeRateBot.NBU.NBUJson;
import org.CurrencyExchangeRateBot.NBU.ResponseNBU;
import org.CurrencyExchangeRateBot.Privat24.PrivatJson;
import org.CurrencyExchangeRateBot.Privat24.ResponsePrivat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import static org.CurrencyExchangeRateBot.BotConstant.*;

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



    public void getInfo(long chatId) throws IOException {
        UserModel user = UserServices.getUserModel(chatId);

        //Блок для МоноБанку
        if(user.getBank().equalsIgnoreCase("Моно")){

            List<MonoJson> currencies = ResponseMono.get(MONO_API);

            if(user.getCurrency().equals("USD")){


                for (MonoJson currency : currencies) {

                    if (currency.getCurrencyCode() == 840) {
                        MessageBuilder.sendMessage(chatId, "Курс в МоноБанк: USD/UAH\nКупівля: " +
                                new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getRateBuy()) +
                                "\nПродаж: " +
                                new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getRateSell()), Buttons.start());
                    }
                }
            } else if(user.getCurrency().equals("EUR")) {

                for (MonoJson currency : currencies) {

                    if (currency.getCurrencyCode() == 978) {
                        MessageBuilder.sendMessage(chatId, "Курс в МоноБанк: EUR/UAH\nКупівля: " +
                                new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getRateBuy()) +
                                "\nПродаж: " +
                                new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getRateSell()), Buttons.start());
                    }
                }
            } else if(user.getCurrency().contains("USD") && user.getCurrency().contains("EUR")) {
                StringBuilder textForMessage = new StringBuilder();
                for (MonoJson currency : currencies) {

                    if (currency.getCurrencyCode() == 978) {
                        textForMessage.append("Курс в МоноБанк: EUR/UAH\nКупівля: ").
                                append(new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getRateBuy())).
                                append("\nПродаж: ").
                                append(new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getRateSell())).append("\n");
                    } else {
                        textForMessage.append("Курс в МоноБанк: USD/UAH\nКупівля: ").
                                append(new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getRateBuy())).
                                append("\nПродаж: ").
                                append(new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getRateSell())).append("\n");
                    }
                }
                MessageBuilder.sendMessage(chatId, String.valueOf(textForMessage), Buttons.start());
            } else {
                MessageBuilder.sendMessage(chatId, "Жодної валюти не обрано", Buttons.start());
            }
        } else if(user.getBank().equalsIgnoreCase("Приват")) { //Блок для ПриватБанку
            List<PrivatJson> currencies = ResponsePrivat.get(PRIVAT_API);

            if(user.getCurrency().equals("USD")){


                for (PrivatJson currency : currencies) {

                    if (currency.getCcy().equals("USD")) {
                        MessageBuilder.sendMessage(chatId, "Курс в ПриватБанк: USD/UAH\nКупівля: " +
                                new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getBuy()) +
                                "\nПродаж: " +
                                new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getSale()), Buttons.start());
                    }
                }
            } else if(user.getCurrency().equals("EUR")) {

                for (PrivatJson currency : currencies) {

                    if (currency.getCcy().equals("EUR")) {
                        MessageBuilder.sendMessage(chatId, "Курс в ПриватБанк: EUR/UAH\nКупівля: " +
                                new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getBuy()) +
                                "\nПродаж: " +
                                new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getSale()), Buttons.start());
                    }
                }
            } else if(user.getCurrency().contains("USD") && user.getCurrency().contains("EUR")) {
                StringBuilder textForMessage = new StringBuilder();
                for (PrivatJson currency : currencies) {

                    if (currency.getCcy().equals("USD")) {
                        textForMessage.append("Курс в ПриватБанк: USD/UAH\nКупівля: ").
                                append(new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getBuy())).
                                append("\nПродаж: ").
                                append(new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getSale())).append("\n");
                    } else {
                        textForMessage.append("Курс в ПриватБанк: EUR/UAH\nКупівля: ").append(currency.getBuy()).append("\nПродаж: ").append(currency.getSale()).append("\n");
                    }
                }
                MessageBuilder.sendMessage(chatId, String.valueOf(textForMessage), Buttons.start());
            } else {
                MessageBuilder.sendMessage(chatId, "Жодної валюти не обрано", Buttons.start());
            }
        } else if(user.getBank().equalsIgnoreCase("НБУ")) { //Блок для НБУ
            List<NBUJson> currencies = ResponseNBU.get(NBU_API);

            if(user.getCurrency().equals("USD")){


                for (NBUJson currency : currencies) {

                    if (currency.getCc().equals("USD")) {
                        MessageBuilder.sendMessage(chatId, "Курс в НБУ: USD/UAH\nКупівля: " +
                                new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getRate()), Buttons.start());
                    }
                }
            } else if(user.getCurrency().equals("EUR")) {

                for (NBUJson currency : currencies) {

                    if (currency.getCc().equals("EUR")) {
                        MessageBuilder.sendMessage(chatId, "Курс в НБУ: EUR/UAH\nКупівля: " +
                                new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getRate()), Buttons.start());
                    }
                }
            } else if(user.getCurrency().contains("USD") && user.getCurrency().contains("EUR")) {
                StringBuilder textForMessage = new StringBuilder();
                for (NBUJson currency : currencies) {

                    if (currency.getCc().equals("USD")) {
                        textForMessage.append("Курс в НБУ: USD/UAH\nКупівля: ").
                                append(new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getRate())).append("\n");
                    } else {
                        textForMessage.append("Курс в НБУ: EUR/UAH\nКупівля: ").
                                append(new DecimalFormat("#." + "0".repeat(Math.max(0, user.getNumber()))).format(currency.getRate())).append("\n");
                    }
                }
                MessageBuilder.sendMessage(chatId, String.valueOf(textForMessage), Buttons.start());
            } else {
                MessageBuilder.sendMessage(chatId, "Жодної валюти не обрано", Buttons.start());
            }
        }
    }

    public void changeQuantityOfNumbers(String callbackData, long chatId) {
        UserServices.getUserModel(chatId).setNumber(Integer.parseInt(callbackData.trim()));
        MessageBuilder.sendMessage(chatId, "===МЕНЮ===", Buttons.start());
    }
}
