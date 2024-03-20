package org.CurrencyExchangeRateBot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class MessageBuilder {

    private static SendMessage sendMessage;
    private static TelegramBot telegramBot;

    public MessageBuilder(TelegramBot telegramBot) {
        sendMessage = new SendMessage();
        MessageBuilder.telegramBot = telegramBot;
    }

    public static void sendMessage(long chatId, String answer, InlineKeyboardMarkup button) {
        sendMessage.setChatId(chatId);
        sendMessage.setText(answer);

        sendMessage.setReplyMarkup(button);

        telegramBot.executeMessage(sendMessage);
    }
}