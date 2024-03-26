package org.CurrencyExchangeRateBot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;
import java.util.List;

import static org.CurrencyExchangeRateBot.BotConstant.BOT_NAME;
import static org.CurrencyExchangeRateBot.BotConstant.BOT_TOKEN;


public class TelegramBot extends TelegramLongPollingBot {

    private final ProcessHandler processHandler;

    public TelegramBot() {

        this.processHandler = new ProcessHandler(this);
        List<BotCommand> botCommandList = BotCommandList.getBotCommandList();

        try {
            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }
    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @SneakyThrows
    public void onUpdateReceived(Update update) {
        long chatId;

        String userName;
        String receivedMessage;

        if (update.hasMessage() && update.getMessage().hasText()) {
            chatId = update.getMessage().getChatId();
            userName = update.getMessage().getFrom().getFirstName();
            receivedMessage = update.getMessage().getText();

            try {
                processHandler.message(receivedMessage, userName, chatId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        if (update.hasCallbackQuery()) {
            chatId = update.getCallbackQuery().getMessage().getChatId();

            receivedMessage = update.getCallbackQuery().getData();

            try {
                processHandler.callbackQuery(receivedMessage, chatId, chatId);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void executeMessage(SendMessage message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            Log.Error(e);
        }
    }
}