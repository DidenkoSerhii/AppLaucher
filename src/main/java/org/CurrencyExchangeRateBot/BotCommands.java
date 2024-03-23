package org.CurrencyExchangeRateBot;

import org.CurrencyExchangeRateBot.MonoBank.MonoJson;
import org.CurrencyExchangeRateBot.MonoBank.ResponseMono;
import org.CurrencyExchangeRateBot.NBU.NBUJson;
import org.CurrencyExchangeRateBot.NBU.ResponseNBU;
import org.CurrencyExchangeRateBot.Privat24.PrivatJson;
import org.CurrencyExchangeRateBot.Privat24.ResponsePrivat;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

import java.io.IOException;
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
        if(user.bank.equalsIgnoreCase("Моно")){

            List<MonoJson> currencies = ResponseMono.get(MONO_API);

            if(user.currency.equals("USD")){


                for (MonoJson currency : currencies) {

                    if (currency.getCurrencyCode() == 840) {
                        MessageBuilder.sendMessage(chatId, "Курс в МоноБанк: USD/UAH\nКупівля: " + currency.getRateBuy() + "\nПродаж: " + currency.getRateSell(), Buttons.start());
                    }
                }
            } else if(user.currency.equals("EUR")) {

                for (MonoJson currency : currencies) {

                    if (currency.getCurrencyCode() == 978) {
                        MessageBuilder.sendMessage(chatId, "Курс в МоноБанк: EUR/UAH\nКупівля: " + currency.getRateBuy() + "\nПродаж: " + currency.getRateSell(), Buttons.start());
                    }
                }
            } else if(user.currency.contains("USD") && user.currency.contains("EUR")) {
                StringBuilder textForMessage = new StringBuilder();
                for (MonoJson currency : currencies) {

                    if (currency.getCurrencyCode() == 978) {
                        textForMessage.append("Курс в МоноБанк: EUR/UAH\nКупівля: ").append(currency.getRateBuy()).append("\nПродаж: ").append(currency.getRateSell()).append("\n");
                    } else {
                        textForMessage.append("Курс в МоноБанк: USD/UAH\nКупівля: ").append(currency.getRateBuy()).append("\nПродаж: ").append(currency.getRateSell()).append("\n");
                    }
                }
                MessageBuilder.sendMessage(chatId, String.valueOf(textForMessage), Buttons.start());
            }
        } else if(user.bank.equalsIgnoreCase("Приват")) { //Блок для ПриватБанку
            List<PrivatJson> currencies = ResponsePrivat.get(PRIVAT_API);

            if(user.currency.equals("USD")){


                for (PrivatJson currency : currencies) {

                    if (currency.getCcy().equals("USD")) {
                        MessageBuilder.sendMessage(chatId, "Курс в ПриватБанк: USD/UAH\nКупівля: " + currency.getBuy() + "\nПродаж: " + currency.getSale(), Buttons.start());
                    }
                }
            } else if(user.currency.equals("EUR")) {

                for (PrivatJson currency : currencies) {

                    if (currency.getCcy().equals("EUR")) {
                        MessageBuilder.sendMessage(chatId, "Курс в ПриватБанк: EUR/UAH\nКупівля: " + currency.getBuy() + "\nПродаж: " + currency.getSale(), Buttons.start());
                    }
                }
            } else if(user.currency.contains("USD") && user.currency.contains("EUR")) {
                StringBuilder textForMessage = new StringBuilder();
                for (PrivatJson currency : currencies) {

                    if (currency.getCcy().equals("USD")) {
                        textForMessage.append("Курс в ПриватБанк: USD/UAH\nКупівля: ").append(currency.getBuy()).append("\nПродаж: ").append(currency.getSale()).append("\n");
                    } else {
                        textForMessage.append("Курс в ПриватБанк: EUR/UAH\nКупівля: ").append(currency.getBuy()).append("\nПродаж: ").append(currency.getSale()).append("\n");
                    }
                }
                MessageBuilder.sendMessage(chatId, String.valueOf(textForMessage), Buttons.start());
            }
        } else if(user.bank.equalsIgnoreCase("НБУ")) { //Блок для НБУ
            List<NBUJson> currencies = ResponseNBU.get(NBU_API);

            if(user.currency.equals("USD")){


                for (NBUJson currency : currencies) {

                    if (currency.getCc().equals("USD")) {
                        MessageBuilder.sendMessage(chatId, "Курс в НБУ: USD/UAH\nКупівля: " + currency.getRate(), Buttons.start());
                    }
                }
            } else if(user.currency.equals("EUR")) {

                for (NBUJson currency : currencies) {

                    if (currency.getCc().equals("EUR")) {
                        MessageBuilder.sendMessage(chatId, "Курс в НБУ: EUR/UAH\nКупівля: " + currency.getRate(), Buttons.start());
                    }
                }
            } else if(user.currency.contains("USD") && user.currency.contains("EUR")) {
                StringBuilder textForMessage = new StringBuilder();
                for (NBUJson currency : currencies) {

                    if (currency.getCc().equals("USD")) {
                        textForMessage.append("Курс в НБУ: USD/UAH\nКупівля: ").append(currency.getRate()).append("\n");
                    } else {
                        textForMessage.append("Курс в НБУ: EUR/UAH\nКупівля: ").append(currency.getRate()).append("\n");
                    }
                }
                MessageBuilder.sendMessage(chatId, String.valueOf(textForMessage), Buttons.start());
            }
        }




    }
}
